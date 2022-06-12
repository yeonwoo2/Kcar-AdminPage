package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.InspectionRecord;
import com.kcar.adminpage.domain.InsuranceHistory;
import com.kcar.adminpage.domain.PurchaseCost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

}
