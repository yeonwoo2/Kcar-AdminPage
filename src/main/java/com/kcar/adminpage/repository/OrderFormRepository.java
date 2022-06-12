package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.OrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public void delete(Long id) {
        OrderForm orderForm = em.find(OrderForm.class, id);
        em.remove(orderForm);
    }

    public List<OrderForm> findAllWithOrderFormAndUser(){
        return em.createQuery("select o from OrderForm o" +
                " left join fetch o.orderCar c" +
                " left join fetch o.user u where c.paymentDate =: date", OrderForm.class)
                .setParameter("date", null)
                .getResultList();
    }

}
