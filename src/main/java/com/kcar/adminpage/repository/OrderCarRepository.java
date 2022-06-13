package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.OrderCar;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

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

}
