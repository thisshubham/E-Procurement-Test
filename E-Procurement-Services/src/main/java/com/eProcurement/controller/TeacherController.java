package com.eProcurement.controller;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.QuestionDto;
import com.eProcurement.dto.SubjectDto;
import com.eProcurement.dto.TestResultDto;
import com.eProcurement.entity.Question;
import com.eProcurement.entity.Student;
import com.eProcurement.entity.TestResult;
import com.eProcurement.service.QuestionService;
import com.eProcurement.service.StudentServ;
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

    @Autowired
    private StudentServ studentServ;


    @GetMapping(Commonconstants.SUBJECT_DEPARTMENTID)
    public ResponseEntity<?> getSubjects(@PathVariable Long departmentId) {
        return subjectService.getSubjectsByDepartment(departmentId);
    }

    @GetMapping(Commonconstants.SUBJECT_SUBJECTID_QUESTION)
    public ResponseEntity<List<Question>> getQuestions(@PathVariable("subjectId") Long subjectId) {
        return ResponseEntity.ok(questionService.getQuestionsBySubject(subjectId));
    }
    @GetMapping(Commonconstants.TEACHER_DEPARTMENTID_STUDENT)
    public ResponseEntity<List<Student>> getStudentByDepartment(@PathVariable("departmentId") Long deparmentId) {
        return ResponseEntity.ok(studentServ.getStudentsByDepartmentId(deparmentId));
    }

    @PutMapping(Commonconstants.RESULT_RESULTID_GRADDE)
    public ResponseEntity<TestResult> gradeResult(
            @PathVariable Long resultId,
            @RequestParam double marks,
            @RequestParam(required = false) String remarks,
            @RequestParam Long teacherId
    ) {
        return ResponseEntity.ok( testResultService.gradeResult(resultId, marks, remarks, teacherId));

    }
}

