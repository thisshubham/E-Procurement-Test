package com.eProcurement.service;

import com.eProcurement.dto.TestResultDto;
import com.eProcurement.entity.Student;
import com.eProcurement.entity.Teacher;
import com.eProcurement.entity.TestResult;
import com.eProcurement.repo.StudentRepo;
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

    @Autowired
    private StudentRepo studentRepo;

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
    public List<TestResultDto> getMyResults(Long id) {

        List<TestResult> results = testResultRepo.findAllBySubject_Id(id);
        return results.stream().map(this::mapToDto).collect(Collectors.toList());
    }



    public List<TestResultDto> getStudentResultsByDepartment(Long studentId, Long  teacherId) {
        Teacher teacher = teacherRepo.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!teacher.getDepartment().getId().equals(student.getDepartment().getId())) {
            throw new RuntimeException("Access denied: Student not in your department");
        }

        List<TestResult> results = testResultRepo.findTestResultByStudent_Id(studentId);

        return results.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TestResultDto convertToDto(TestResult result) {
        TestResultDto dto = new TestResultDto();
        dto.setId(result.getId());
        dto.setSubjectName(result.getSubject().getName());
        dto.setMarksObtained(result.getMarksObtained());
        dto.setTotalMarks(result.getTotalMarks());
        dto.setPercentage(result.getPercentage());
        dto.setStatus(result.getStatus().name());
        dto.setTeacherRemarks(result.getTeacherRemarks());
        dto.setSubmittedAt(result.getSubmittedAt());
        return dto;
    }

    public List<TestResultDto> getStudentScoreBoard(Long studentId) {
        Student student = studentRepo.findStudentByStudentIdCode(studentId);
        List<TestResult> results = testResultRepo.findTestResultByStudent_Id(student.getId());
        return results.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}

