package com.eProcurement.repo;

import com.eProcurement.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo  extends JpaRepository<Answer, Long>  {
    List<Answer> findAllByQuestion_Id(Long id);
}
