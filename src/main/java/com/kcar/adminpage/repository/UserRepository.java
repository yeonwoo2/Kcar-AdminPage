package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.User;
import com.kcar.adminpage.domain.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public List<User> findByDate(LocalDateTime startDate, LocalDateTime endDate){
        return em.createQuery("select u from User u where u.createDate <: endDate and u.createDate >: startDate", User.class)
                .setParameter("endDate", endDate)
                .setParameter("startDate", startDate)
                .getResultList();
    }

    public List<User> findByAuthority(Authority authority){
        return em.createQuery("select u from User u where u.authority =: authority", User.class)
                .setParameter("authority", authority)
                .getResultList();
    }
}
