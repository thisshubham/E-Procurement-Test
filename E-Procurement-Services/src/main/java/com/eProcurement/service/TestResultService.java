package com.eProcurement.service;

import com.eProcurement.dto.TestResultDto;
import com.eProcurement.entity.Teacher;
import com.eProcurement.entity.TestResult;
import com.eProcurement.repo.TeacherRepo;
import com.eProcurement.repo.TestResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestResultService {
    @Autowired
    private TestResultRepo testResultRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    public TestResult gradeResult(Long resultId, double marks, String remarks, Long teacherId) {
        TestResult result = testResultRepo.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Test Result not found"));

        result.setMarksObtained(marks);
        result.setPercentage((marks / result.getTotalMarks()) * 100);

        result.setStatus(TestResult.TestStatus.GRADED);

        if (teacherId != null) {
            Teacher teacher = teacherRepo.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            result.setGradedBy(teacher);
        }

        if (remarks != null) {
            result.setTeacherRemarks(remarks);
        }

        return testResultRepo.save(result);
    }

    public List<TestResultDto> getMyResults() {
        List<TestResultDto> testResultDtos = new ArrayList<>();
        return  testResultDtos;
    }
}
