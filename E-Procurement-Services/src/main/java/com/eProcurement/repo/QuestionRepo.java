package com.eProcurement.repo;

import com.eProcurement.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo  extends JpaRepository<Question, Long> {
    List<Question> findAllBySubject_Id(Long subjectId);
}
