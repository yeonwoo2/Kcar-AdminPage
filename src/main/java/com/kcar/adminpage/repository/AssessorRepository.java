package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Assessor;
import com.kcar.adminpage.domain.Car;
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

    public List<Assessor> findByName(String name){
        return em.createQuery("select a from Assessor a where a.name =: name", Assessor.class)
                .setParameter("name", name)
                .getResultList();
    }
}
