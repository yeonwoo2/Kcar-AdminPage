package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.InspectionRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    //벌크연산
    public void deleteByIdIn(List<Long> cars) {
        em.createQuery("delete from InspectionRecord i where i.id in :cars")
                .setParameter("cars", cars)
                .executeUpdate();
    }
}
