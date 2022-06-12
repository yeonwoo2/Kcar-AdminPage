package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Delivery> findAll(){
        return em.createQuery("select d from Delivery d", Delivery.class)
                .getResultList();
    }
}
