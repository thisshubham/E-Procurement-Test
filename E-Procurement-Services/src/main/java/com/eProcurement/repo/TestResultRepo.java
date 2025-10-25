package com.eProcurement.repo;

import com.eProcurement.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepo extends JpaRepository<TestResult, Long> {
    TestResult findTestResultByIdAndMarksObtained(Long resultId, int marks);
}
