package com.eProcurement.repo;

import com.eProcurement.entity.Teacher;
import com.eProcurement.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}
