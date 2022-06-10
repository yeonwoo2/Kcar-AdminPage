package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.OrderCar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    public List<OrderCar> findAllWithOrderFormAndCar(){
        return em.createQuery("select c from OrderCar c" +
                " left join fetch c.orderForm f" +
                " left join fetch c.car i", OrderCar.class)
                .getResultList();
    }
}
