package com.eProcurement.service;

import com.eProcurement.dto.QuizDto;
import com.eProcurement.dto.StudentAnswerDto;
import com.eProcurement.dto.SubjectDto;
import com.eProcurement.dto.TestResultDto;
import com.eProcurement.entity.Answer;
import com.eProcurement.entity.Question;
import com.eProcurement.entity.Subject;
import com.eProcurement.entity.TestResult;
import com.eProcurement.repo.QuestionRepo;
import com.eProcurement.repo.SubjectRepo;
import com.eProcurement.repo.TestResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private TestResultRepo  testResultRepo;

    @Autowired
    private TestResultService testResultService;

    @Transactional

    public QuizDto startQuiz(Long subjectId) {
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        List<Question> questions = questionRepo.findAllBySubject_Id(subjectId);

        TestResult result = new TestResult();
        result.setSubject(subject);
        result.setTotalQuestions(questions.size());
        result.setStatus(TestResult.TestStatus.IN_PROGRESS);
        result.setStartedAt(LocalDateTime.now());

        testResultRepo.save(result);

        QuizDto quiz = new QuizDto();
        quiz.setQuizId(result.getId());
        quiz.setSubjectId(subjectId);
        quiz.setQuestions(questions);

        return quiz;
    }

    @Transactional
    public TestResultDto submitQuiz(Long quizId, List<StudentAnswerDto> studentAnswers) {
        TestResult result = testResultRepo.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Test Result not found"));

        int correct = 0;
        int attempted = 0;

        for (StudentAnswerDto studentAnswer : studentAnswers) {
            Question question = questionRepo.findById(studentAnswer.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            Answer correctAnswer = question.getAnswers()
                    .stream()
                    .filter(Answer::getIsCorrect)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No correct answer defined for question"));

            if (studentAnswer.getSelectedOption().equalsIgnoreCase(correctAnswer.getAnswerText())) {
                correct++;
            }
            attempted++;
        }

        result.setAttemptedQuestions(attempted);
        result.setCorrectAnswers(correct);
        result.setWrongAnswers(attempted - correct);

        double marksObtained = ((double) correct / result.getTotalQuestions()) * result.getTotalMarks();
        result.setMarksObtained(marksObtained);
        result.setPercentage((marksObtained / result.getTotalMarks()) * 100);
        result.setStatus(TestResult.TestStatus.SUBMITTED);
        result.setSubmittedAt(LocalDateTime.now());

        testResultRepo.save(result);

        return testResultService.mapToDto(result);
    }

    public List<Subject> getAvailableSubjects() {
        return subjectRepo.findAll()
                .stream()
                .filter(Subject::getActive)
                .collect(Collectors.toList());    }
}
