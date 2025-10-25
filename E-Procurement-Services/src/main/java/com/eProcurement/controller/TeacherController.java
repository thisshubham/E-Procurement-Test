package com.eProcurement.controller;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.QuestionDto;
import com.eProcurement.dto.SubjectDto;
import com.eProcurement.dto.TestResultDto;
import com.eProcurement.service.QuestionService;
import com.eProcurement.service.SubjectService;
import com.eProcurement.service.TestResultService;
import com.eProcurement.utility.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Commonconstants.BASE_URL + Commonconstants.TEACHER)
//@RequiredArgsConstructor
//@PreAuthorize("hasRole('TEACHER')")
public class TeacherController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private TestResultService testResultService;

    @PostMapping(Commonconstants.SUBJECT)
    public ResponseDto createSubject(@RequestBody SubjectDto dto) {
        return subjectService.createSubject(dto);
    }

    @GetMapping(Commonconstants.SUBJECT_DEPARTMENTID)
    public ResponseEntity<?> getSubjects(@PathVariable Long departmentId) {
        return subjectService.getSubjectsByDepartment(departmentId);
    }

    @PostMapping(Commonconstants.SUBJECT_SUBJECTID_QUESTION)
    public ResponseEntity<QuestionDto> addQuestion(@PathVariable Long subjectId, @RequestBody QuestionDto dto) {
        return ResponseEntity.ok(questionService.addQuestion(subjectId, dto));
    }

    @GetMapping(Commonconstants.SUBJECT_SUBJECTID_QUESTION)
    public ResponseEntity<List<QuestionDto>> getQuestions(@PathVariable Long subjectId) {
        return ResponseEntity.ok(questionService.getQuestionsBySubject(subjectId));
    }

    @PutMapping(Commonconstants.RESULT_RESULTID_GRADDE)
    public ResponseEntity<TestResultDto> gradeResult(@PathVariable Long resultId, @RequestParam int marks) {
        return ResponseEntity.ok(testResultService.gradeResult
                (resultId, marks));
    }
}

