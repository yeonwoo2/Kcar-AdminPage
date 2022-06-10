package com.kcar.adminpage.repository;

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

}
