package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Assessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AssessorRepository {

    private final EntityManager em;

    public void save(Assessor assessor){
        em.persist(assessor);
    }

    public Assessor findOne(Long id){
        return em.find(Assessor.class, id);
    }

    public List<Assessor> findAll(){
        return em.createQuery("select a from Assessor a", Assessor.class)
                .getResultList();
    }

    public Assessor findByEmployeeNumber(String employeeNumber){ // 사번은 고유해야한다 따라서 단건 조회
        return em.createQuery("select a from Assessor a where a.employeeNumber =: employeeNumber", Assessor.class)
                .setParameter("employeeNumber", employeeNumber)
                .getSingleResult();
    }
}
