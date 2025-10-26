package com.eProcurement.controller;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.QuizDto;
import com.eProcurement.dto.StudentAnswerDto;
import com.eProcurement.dto.SubjectDto;
import com.eProcurement.dto.TestResultDto;
import com.eProcurement.entity.Subject;
import com.eProcurement.service.QuizService;
import com.eProcurement.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Commonconstants.BASE_URL + Commonconstants.STUDENT)
//@RequiredArgsConstructor
//@PreAuthorize("hasRole('STUDENT')")
public class StudentController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private TestResultService testResultService;

    @GetMapping(Commonconstants.SUBJECT)
    public ResponseEntity<List<Subject>> getAvailableSubjects() {
        return ResponseEntity.ok(quizService.getAvailableSubjects());
    }

    @PostMapping(Commonconstants.QUIZE_SUBJECTID_START)
    public ResponseEntity<QuizDto> startQuiz(@PathVariable Long subjectId) {
        return ResponseEntity.ok(quizService.startQuiz(subjectId));
    }

    @PostMapping(Commonconstants.QUIZE_QUIZEID_SUBMIT)
    public ResponseEntity<TestResultDto> submitQuiz(@PathVariable Long quizId, @RequestBody List<StudentAnswerDto> answers) {
        return ResponseEntity.ok(quizService.submitQuiz(quizId, answers));
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<List<TestResultDto>> getMyResults(@PathVariable("id") Long studentId) {
        return ResponseEntity.ok(testResultService.getMyResults(studentId));
    }
}

