package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.InsuranceHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class InsuranceHistoryRepository {

    private final EntityManager em;

    public void save(InsuranceHistory insuranceHistory){
        em.persist(insuranceHistory);
    }
}
