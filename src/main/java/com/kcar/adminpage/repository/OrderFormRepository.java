package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.OrderCar;
import com.kcar.adminpage.domain.OrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderFormRepository {

    private final EntityManager em;

    public void save(OrderForm orderForm){
        em.persist(orderForm);
    }

    public OrderForm findOne(Long id){
        return em.find(OrderForm.class, id);
    }
}
