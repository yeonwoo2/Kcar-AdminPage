package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.QCar;
import com.kcar.adminpage.domain.QPurchaseCost;
import com.kcar.adminpage.domain.enums.SalesStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class CarRepository {

    private final EntityManager em;

    public void save(Car car){
        em.persist(car);
    }

    public Car findOne(Long id){
        return em.find(Car.class, id);
    }

    public List<Car> findAll(){
        return em.createQuery("select c from Car c", Car.class)
                .getResultList();
    }

    public List<Car> findByName(String name){
        return em.createQuery("select c from Car c where c.name =: name", Car.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Car> findAllWithCategoryAndAssessor(){
        return em.createQuery("select c from Car c" +
                                      " left join fetch c.categories t" +
                                      " left join fetch c.assessor a", Car.class)
                .getResultList();
    }

    public List<Car> findBySalesStatus(SalesStatus salesStatus){
        return em.createQuery("select c from Car c where c.salesStatus =: salesStatus", Car.class)
                .setParameter("salesStatus", salesStatus)
                .getResultList();
    }

    public List<Car> findOrderByDate(){
        return em.createQuery("SELECT c FROM Car c ORDER BY c.registrationDate DESC", Car.class)
                .setFirstResult(0)
                .setMaxResults(4)
                .getResultList();
    }

    public void delete(Car car) {
        em.remove(car);
    }

    //동적쿼리 생성
    public List<Car> findBySearchCondition(CarSearchCondition condition){

        BooleanBuilder builder = new BooleanBuilder();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCar car = QCar.car;

        if (hasText(condition.getCarName())) {
            builder.and(car.name.contains(condition.getCarName())); // 차량 이름 조건
        }

        if (condition.getSaleStatus() != null) {
            builder.and(car.salesStatus.eq(condition.getSaleStatus())); //판매상태 조건
        }

        if (hasText(condition.getCategory())) {
            builder.and(car.categories.name.eq(condition.getCategory())); //카테고리이름 조건
        }

        if (condition.getStartPrice() != null && condition.getEndPrice() != null) {
            builder.and(car.purchaseCost.carPrice.in(condition.getStartPrice(), condition.getEndPrice())); //차량 가격 조건
        }

        if (condition.getStartDate() != null && condition.getEndDate() != null) {
            builder.and(car.registrationDate.in(condition.getStartDate(), condition.getEndDate())); //날짜 조건
        }


        return queryFactory
                .selectFrom(car)
                .leftJoin(car.purchaseCost)
                .fetchJoin()
                .leftJoin(car.categories)
                .fetchJoin()
                .leftJoin(car.assessor)
                .fetchJoin()
                .where(builder)
                .fetch();

    }



}
