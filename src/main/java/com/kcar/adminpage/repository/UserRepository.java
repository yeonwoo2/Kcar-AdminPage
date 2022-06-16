package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.QUser;
import com.kcar.adminpage.domain.User;
import com.kcar.adminpage.domain.enums.Authority;
import com.kcar.adminpage.controller.dto.userdto.UserConditionDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

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

    //동적쿼리 생성
    public List<User> findBySearchCondition(UserConditionDto condition){

        BooleanBuilder builder = new BooleanBuilder();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUser user = QUser.user;

        if (condition.getAuthority() != null) {
            builder.and(user.authority.eq(condition.getAuthority())); // 수취인 이름 조건
        }

        if (hasText(condition.getName())) {
            builder.and(user.name.contains(condition.getName())); //판매상태 조건
        }

        JPAQuery<User> query = queryFactory.selectFrom(user);

        return query
                .where(builder)
                .offset(0)
                .limit(condition.getPaging()) //0보다 큰 값이 반드시 들어옴
                .fetch();
    }
}
