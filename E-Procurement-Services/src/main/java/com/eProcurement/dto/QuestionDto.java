package com.eProcurement.dto;

import com.eProcurement.entity.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionDto {
    private String questionText;
    private Question.DifficultyLevel difficultyLevel = Question.DifficultyLevel.MEDIUM;
    private Integer marks = 1;
    private Boolean active = true;
    private Long subjectId; // 🔗 Foreign key to Subject

    private List<AnswerDto> answers;
    @Data
    public static class AnswerDto {
        private String answerText;
        private Boolean correct;
    }
}
