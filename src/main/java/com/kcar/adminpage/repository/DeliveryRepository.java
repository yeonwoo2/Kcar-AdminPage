package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.*;
import com.kcar.adminpage.domain.enums.DeliveryStatus;
import com.kcar.adminpage.repository.condition.DeliverySearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class DeliveryRepository {

    private final EntityManager em;

    public void save(Delivery delivery){
        em.persist(delivery);
    }

    public Delivery findOne(Long id){
        return em.find(Delivery.class, id);
    }

//    public List<Delivery> findAll(){
//        return em.createQuery("select d from Delivery d", Delivery.class)
//                .getResultList();
//    }

    public List<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus){
        return em.createQuery("select d from Delivery d where d.deliveryStatus =: deliveryStatus", Delivery.class)
                .setParameter("deliveryStatus", deliveryStatus)
                .getResultList();
    }

    //동적쿼리 생성
    public List<Delivery> findBySearchCondition(DeliverySearchCondition condition){

        BooleanBuilder builder = new BooleanBuilder();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QDelivery delivery = QDelivery.delivery;

        if (hasText(condition.getReceiver())) {
            builder.and(delivery.receiver.eq(condition.getReceiver())); // 수취인 이름 조건
        }

        if (condition.getDeliveryStatus() != null) {
            builder.and(delivery.deliveryStatus.eq(condition.getDeliveryStatus())); //판매상태 조건
        }

        JPAQuery<Delivery> query = queryFactory.selectFrom(delivery);

        return query
                .where(builder)
                .offset(0)
                .limit(condition.getPaging()) //0보다 큰 값이 반드시 들어옴
                .fetch();
    }
}
