package com.eProcurement.service;

import com.eProcurement.dto.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    public QuestionDto addQuestion(Long subjectId, QuestionDto dto) {
        QuestionDto questionDto = new QuestionDto();
        return  questionDto;
    }

    public List<QuestionDto> getQuestionsBySubject(Long subjectId) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        return questionDtos;
    }

    public void deleteQuestion(Long id) {
    }

    public QuestionDto getQuestionById(Long id) {
        QuestionDto questionDto = new QuestionDto();
        return questionDto;
    }
}
