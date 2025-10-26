package com.eProcurement.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizDto {
    private Long quizId;
    private Long subjectId;
//    private List<Question> questions;
    private List<QuestDTo> questDTos;

}
