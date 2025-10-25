package com.eProcurement.service;

import com.eProcurement.dto.QuizDto;
import com.eProcurement.dto.StudentAnswerDto;
import com.eProcurement.dto.SubjectDto;
import com.eProcurement.dto.TestResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    public QuizDto startQuiz(Long subjectId) {
        QuizDto quizDto = new QuizDto();
        return quizDto;
    }

    public TestResultDto submitQuiz(Long quizId, List<StudentAnswerDto> answers) {
        TestResultDto testResultDto = new TestResultDto();
        return  testResultDto;
    }

    public List<SubjectDto> getAvailableSubjects() {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        return subjectDtos;
    }
}
