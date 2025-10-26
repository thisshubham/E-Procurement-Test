package com.eProcurement.service;

import com.eProcurement.dto.*;
import com.eProcurement.entity.*;
import com.eProcurement.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private TestResultRepo testResultRepo;

    @Autowired
    private TestResultService testResultService;
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentAnswerRepo studentAnswerRepo;

    @Transactional

    public QuizDto startQuiz(Long subjectId, String studentId) {
        QuizDto quiz = new QuizDto();

        try {
            Student student = studentRepo.findStudentByStudentIdCode(studentId);

            Subject subject = subjectRepo.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found"));

            List<Question> questions = questionRepo.findAllBySubject_Id(subjectId);
            ArrayList<QuestDTo> questDTolist = new ArrayList<>();

            for (Question question : questions) {
                List<Answer> answers = answerRepo.findAllByQuestion_Id(question.getId());
                QuestDTo questDTo = new QuestDTo();
                List<String> optionTexts = answers.stream()
                        .map(Answer::getAnswerText)
                        .collect(Collectors.toList());
                questDTo.setActive(question.getActive());
                questDTo.setQuestionText(question.getQuestionText());
                questDTo.setDifficultyLevel(question.getDifficultyLevel());
                questDTo.setId(question.getId());
//                questDTo.setSubject(question.getSubject());
                    questDTo.setOption( optionTexts);
                    questDTolist.add(questDTo);
            }
            TestResult result = new TestResult();
            result.setSubject(subject);
            result.setTotalQuestions(questions.size());
            result.setStatus(TestResult.TestStatus.IN_PROGRESS);
            result.setStartedAt(LocalDateTime.now());
            result.setStudent(student);
            result.setTotalMarks(0.0);

            testResultRepo.save(result);

            quiz.setQuizId(result.getId());
            quiz.setSubjectId(subjectId);
            quiz.setQuestDTos(questDTolist);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return quiz;
    }

    @Transactional
    public TestResultDto submitQuiz(Long quizId, List<StudentAnswerDto> studentAnswers) {
        TestResult result = null;
        try {
            result = testResultRepo.findById(quizId)
                    .orElseThrow(() -> new RuntimeException("Test Result not found"));

            int correct = 0;
            int attempted = 0;

            for (StudentAnswerDto studentAnswer : studentAnswers) {
                Question question = questionRepo.findById(studentAnswer.getQuestionId())
                        .orElseThrow(() -> new RuntimeException("Question not found"));
                StudentAnswer studentAnswer1 = new StudentAnswer();

                Answer correctAnswer = question.getAnswers()
                        .stream()
                        .filter(Answer::getIsCorrect)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No correct answer defined for question"));

                if (studentAnswer.getSelectedOption().equalsIgnoreCase(correctAnswer.getAnswerText())) {
                    correct++;
                    studentAnswer1.setIsCorrect(true);
                }
                attempted++;
                studentAnswer1.setSelectedAnswer(correctAnswer);
                studentAnswer1.setQuestion(question);
                studentAnswer1.setTestResult(result);
                studentAnswerRepo.save(studentAnswer1);
                result.setTotalMarks(Double.valueOf(question.getSubject().getTotalMarks()));

            }

            result.setAttemptedQuestions(attempted);
            result.setCorrectAnswers(correct);
            result.setWrongAnswers(attempted - correct);

            double marksObtained = ((double) correct / result.getTotalQuestions()) * result.getTotalMarks();
            result.setMarksObtained(marksObtained);
            result.setStatus(TestResult.TestStatus.SUBMITTED);
            result.setSubmittedAt(LocalDateTime.now());
            if (result.getTotalMarks() == null || result.getTotalMarks() == 0.0) {
                result.setPercentage(0.0);
            } else {
                double calculatedPercentage = (marksObtained / result.getTotalMarks()) * 100;
                if (Double.isNaN(calculatedPercentage) || Double.isInfinite(calculatedPercentage)) {
                    result.setPercentage(0.0);
                } else {
                    result.setPercentage(calculatedPercentage);
                }
            }

            testResultRepo.save(result);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return testResultService.mapToDto(result);
    }

    public List<Subject> getAvailableSubjects() {
        return subjectRepo.findAll()
                .stream()
                .filter(Subject::getActive)
                .collect(Collectors.toList());
    }

    public List<SubjectDto> getAvailableSubjects(String studentId) {
        List<Subject> subjects = null;
        try {
            Student student = studentRepo.findStudentByStudentIdCode(studentId.toString());

            subjects = subjectRepo.findSubjectsByDepartment_Id(student.getDepartment().getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return subjects.stream()
                .map(SubjectDto::new)
                .collect(Collectors.toList());
    }
}
