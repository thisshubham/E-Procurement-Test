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
import java.util.stream.Collectors;

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

//    public List<TestResultDto> getMyResults() {
//        List<TestResultDto> testResultDtos = new ArrayList<>();
//        return  testResultDtos;
//    }
    public TestResultDto mapToDto(TestResult result) {
        TestResultDto dto = new TestResultDto();
        dto.setId(result.getId());
        dto.setTotalQuestions(result.getTotalQuestions());
        dto.setAttemptedQuestions(result.getAttemptedQuestions());
        dto.setCorrectAnswers(result.getCorrectAnswers());
        dto.setWrongAnswers(result.getWrongAnswers());
        dto.setMarksObtained(result.getMarksObtained());
        dto.setTotalMarks(result.getTotalMarks());
        dto.setPercentage(result.getPercentage());
        dto.setStatus(result.getStatus().name());
        dto.setSubjectName(result.getSubject().getName());
        return dto;
    }
    public List<TestResultDto> getMyResults() {
        Long currentStudentId = 1L;

        List<TestResult> results = testResultRepo.findAllBySubject_Id(currentStudentId);
        return results.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
