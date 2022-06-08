package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public Category findByName(String name){
        return em.createQuery("select c from Category c where c.name =: name", Category.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
