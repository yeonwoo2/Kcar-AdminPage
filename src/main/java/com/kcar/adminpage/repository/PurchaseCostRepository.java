package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.PurchaseCost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PurchaseCostRepository {

    private final EntityManager em;

    public void save(PurchaseCost purchaseCost){
        em.persist(purchaseCost);
    }

    public PurchaseCost findOne(Long id){
        return em.find(PurchaseCost.class, id);
    }

    public void delete(Long id) {
        PurchaseCost purchaseCost = em.find(PurchaseCost.class, id);
        em.remove(purchaseCost);
    }

    public List<PurchaseCost> findAllWithCarAndOrderCar(){
        return em.createQuery("select p from PurchaseCost p" +
                " left join fetch p.car c", PurchaseCost.class)
                .getResultList();
    }

}
