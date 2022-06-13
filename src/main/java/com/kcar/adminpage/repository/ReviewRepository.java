package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public List<Review> findAll(){
        return em.createQuery("select r from Review r", Review.class)
                .getResultList();
    }

    public List<Review> findByDate(LocalDateTime startDate, LocalDateTime endDate){
        return em.createQuery("select r from Review r where r.createDate <: endDate and r.createDate >: startDate", Review.class)
                .setParameter("endDate", endDate)
                .setParameter("startDate", startDate)
                .getResultList();
    }

    public List<Review> findReviewByRecentDate(){
        return em.createQuery("SELECT r FROM Review r ORDER BY r.createDate DESC", Review.class)
                .setFirstResult(0)
                .setMaxResults(4)
                .getResultList();
    }
}
