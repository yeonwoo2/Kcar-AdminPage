package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.InspectionRecord;
import com.kcar.adminpage.domain.InsuranceHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InsuranceHistoryRepository {

    private final EntityManager em;

    public void save(InsuranceHistory insuranceHistory){
        em.persist(insuranceHistory);
    }

    public InsuranceHistory findOne(Long id){
        return em.find(InsuranceHistory.class, id);
    }

    public void delete(Long id) {
        InsuranceHistory insuranceHistory = em.find(InsuranceHistory.class, id);
        em.remove(insuranceHistory);
    }

    //벌크연산
    public void deleteByIdIn(List<Long> cars) {
        em.createQuery("delete from InsuranceHistory i where i.id in :cars")
                .setParameter("cars", cars)
                .executeUpdate();
    }
}
