package com.eProcurement.repo;

import com.eProcurement.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo  extends JpaRepository<Question, Long> {
}
