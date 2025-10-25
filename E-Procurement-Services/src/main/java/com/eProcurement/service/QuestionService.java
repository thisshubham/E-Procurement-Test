package com.eProcurement.service;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.QuestionDto;
import com.eProcurement.entity.Answer;
import com.eProcurement.entity.Question;
import com.eProcurement.entity.Subject;
import com.eProcurement.repo.QuestionRepo;
import com.eProcurement.repo.SubjectRepo;
import com.eProcurement.utility.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {
    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private ResponseEntity responseEntity;

    public org.springframework.http.ResponseEntity<?> addQuestion(Long subjectId, QuestionDto request) {
        HashMap<String, Object> response = new HashMap<>();
        Integer responseCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        try {
            Subject subject = subjectRepo.findById(subjectId)
                    .orElse(null);
            if (Objects.isNull(subject)) {
                response.put(Commonconstants.MESSAGE, "Subject not found");
                responseCode = HttpStatus.NO_CONTENT.value();
            }else
            {
                Question question = new Question();
                question.setQuestionText(request.getQuestionText());
                question.setDifficultyLevel(request.getDifficultyLevel());
                question.setMarks(request.getMarks());
                question.setActive(request.getActive());
                question.setSubject(subject);

                List<Answer> answerEntities = new ArrayList<>();
                if (request.getAnswers() != null && !request.getAnswers().isEmpty()) {
                    for (QuestionDto.AnswerDto ans : request.getAnswers()) {
                        Answer answer = new Answer();
                        answer.setAnswerText(ans.getAnswerText());
                        answer.setIsCorrect(ans.getCorrect());
                        answer.setQuestion(question);
                        answerEntities.add(answer);
                    }
                }

                question.setAnswers(answerEntities);
                questionRepo.save(question);
                response.put(Commonconstants.MESSAGE, "Successfully Created");
                responseCode = HttpStatus.CREATED.value();
            }

        } catch (Exception e) {
            response.put(Commonconstants.ERROR_MESSAGE, Commonconstants.FAIL_STATUS);
            responseCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        return responseEntity.apiResponse(responseCode,response);
    }

    public List<Question> getQuestionsBySubject(Long subjectId) {
        return questionRepo.findAllBySubject_Id(subjectId);
    }

    public void deleteQuestion(Long id) {
    }

    public QuestionDto getQuestionById(Long id) {
        QuestionDto questionDto = new QuestionDto();
        return questionDto;
    }
}
