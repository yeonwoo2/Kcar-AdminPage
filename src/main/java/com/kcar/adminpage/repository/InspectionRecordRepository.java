package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.InspectionRecord;
import com.kcar.adminpage.domain.PurchaseCost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class InspectionRecordRepository {

    private final EntityManager em;

    public void save(InspectionRecord inspectionRecord){
        em.persist(inspectionRecord);
    }
}
