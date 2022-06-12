package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Car;
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

    public InspectionRecord findOne(Long id){
        return em.find(InspectionRecord.class, id);
    }

    public void delete(Long id) {
        InspectionRecord inspectionRecord = em.find(InspectionRecord.class, id);
        em.remove(inspectionRecord);
    }
}
