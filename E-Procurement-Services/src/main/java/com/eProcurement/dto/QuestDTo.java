package com.eProcurement.dto;

import com.eProcurement.entity.Answer;
import com.eProcurement.entity.Question;
import com.eProcurement.entity.StudentAnswer;
import com.eProcurement.entity.Subject;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class QuestDTo {
    private Long id;
    private String questionText;
    private Subject subject;
    private Question.DifficultyLevel difficultyLevel = Question.DifficultyLevel.MEDIUM;
    private Integer marks = 1;
    private Boolean active = true;
    private List<String> option;

}
