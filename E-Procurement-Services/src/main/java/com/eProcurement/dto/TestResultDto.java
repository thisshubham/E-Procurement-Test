package com.eProcurement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestResultDto {
    private Long id;
    private String subjectName;
    private Integer totalQuestions;
    private Integer attemptedQuestions;
    private Integer correctAnswers;
    private Integer wrongAnswers;
    private Double marksObtained;
    private Double totalMarks;
    private Double percentage;
    private String status;
    private LocalDateTime submittedAt;
    private String teacherRemarks;
}
