package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Question;
import com.kcar.adminpage.domain.enums.AnswerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    private final EntityManager em;

    public List<Question> findByDateAndAnswerType(AnswerType answerType, LocalDateTime startDate, LocalDateTime endDate){
        return em.createQuery("select q from Question q where q.answerType =: answerType and q.createDate <: endDate and q.createDate >: startDate ORDER BY q.createDate DESC", Question.class)
                .setParameter("answerType", answerType)
                .setParameter("endDate", endDate)
                .setParameter("startDate", startDate)
                .getResultList();
    }
}
