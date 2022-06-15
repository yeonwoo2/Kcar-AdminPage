package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.OrderCar;
import com.kcar.adminpage.domain.QCar;
import com.kcar.adminpage.domain.QOrderCar;
import com.kcar.adminpage.repository.condition.CarSearchCondition;
import com.kcar.adminpage.repository.condition.OrderCarSearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class OrderCarRepository {

    private final EntityManager em;

    public void save(OrderCar orderCar){
        em.persist(orderCar);
    }

    public OrderCar findOne(Long id){
        return em.find(OrderCar.class, id);
    }

    public List<OrderCar> findByOrderStatus(String orderStatus){
        return em.createQuery("select o from OrderCar o where o.orderStatus =: orderStatus", OrderCar.class)
                .setParameter("orderStatus", orderStatus)
                .getResultList();
    }

    public List<OrderCar> findByOrderCompAndDate(String orderStatus, LocalDateTime startDate, LocalDateTime endDate){
        return em.createQuery("select o from OrderCar o where o.orderStatus =: orderStatus and o.orderDate <: endDate and o.orderDate >: startDate", OrderCar.class)
                .setParameter("orderStatus", orderStatus)
                .setParameter("endDate", endDate)
                .setParameter("startDate", startDate)
                .getResultList();
    }

    public List<OrderCar> findByOrderCompAndDateCount(String orderStatus, LocalDateTime sp, LocalDateTime ep, int i){
        LocalDateTime startDate = sp.minusDays(i);
        LocalDateTime endDate = ep.minusDays(i);
        return em.createQuery("select o from OrderCar o where o.orderStatus =: orderStatus and o.orderDate <: endDate and o.orderDate >: startDate", OrderCar.class)
                .setParameter("orderStatus", orderStatus)
                .setParameter("endDate", endDate)
                .setParameter("startDate", startDate)
                .getResultList();
    }

    public List<OrderCar> findAllWithOrderFormAndCar(){
        return em.createQuery("select c from OrderCar c" +
                " left join fetch c.orderForm f" +
                " left join fetch c.car i", OrderCar.class)
                .getResultList();
    }

    public void deleteAllWithCheckCar(List<Long> cars){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QOrderCar orderCar = QOrderCar.orderCar;

        queryFactory
                .delete(orderCar)
                .where(orderCar.car.id.in(cars))
                .execute();
    }

    //동적쿼리 생성
    public List<OrderCar> findBySearchCondition(OrderCarSearchCondition condition){

        BooleanBuilder builder = new BooleanBuilder();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QOrderCar orderCar = QOrderCar.orderCar;

        if (condition.getOrderStatus() != null) {
            builder.and(orderCar.orderStatus.eq(condition.getOrderStatus())); //주문상태 조건
        }

        if (hasText(condition.getName())) {
            builder.and(orderCar.orderForm.holderRight.eq(condition.getName())); // 명의자이름 조건
        }

        if (hasText(condition.getItemName())) {
            builder.and(orderCar.car.name.contains(condition.getItemName())); //차량이름 조건
        }

        if (condition.getPayStartDate() != null && condition.getPayEndDate() != null) {
            builder.and(orderCar.paymentDate.between(condition.getPayStartDate(), condition.getPayEndDate())); //차량 가격 조건
        }

        if (condition.getOrderStartDate() != null && condition.getOrderEndDate() != null) {
            builder.and(orderCar.orderDate.between(condition.getOrderStartDate(), condition.getOrderEndDate())); //날짜 조건
        }

        JPAQuery<OrderCar> query = queryFactory.selectFrom(orderCar);

        return query
                .leftJoin(orderCar.orderForm)
                .fetchJoin()
                .leftJoin(orderCar.car)
                .fetchJoin()
                .where(builder)
                .offset(0)
                .limit(condition.getPaging()) //0보다 큰 값이 반드시 들어옴
                .fetch();
    }
}
