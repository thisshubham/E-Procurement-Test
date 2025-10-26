package com.eProcurement.constants;

import org.springframework.http.HttpStatus;

public interface Commonconstants {
    public final String BASE_URL = "/api/v1/Eprocurement";
    public final String ADMIN = "/admin";
    public final String TEACHERS = "/teachers";
    public final String TEACHER = "/teacher";

    public final String STUDENT = "/student";
    public final String DEPARTMENT = "/departments";
    public final String SUBJECT = "subjects";
    public final String SUBJECT_DEPARTMENTID = "/subjects/{departmentId}";
    public final String SUBJECT_SUBJECTID_QUESTION = "/subjects/{subjectId}/questions";
    public final String SUBJECT_QUESTION = "/subjects/questions";
    public final String TEACHER_DEPARTMENTID_STUDENT = "/teacher/{departmentId}/student";
//    public final String TEACHER_TESTRESULT_STUDENT = "/teacher/{departmentId}/student";
    public final String TEACHER_TESTRESULT_STUDENT = "/{studnetId}/student";

    public final String QUESTION = "/questions";
    public final String AUTH = "/api/v1/Eprocurement";
    public final String QUIZE_SUBJECTID_START =    "/quiz/{subjectId}/start";
    public final String QUIZE_QUIZEID_SUBMIT=  "/quiz/{quizId}/submit";
    public final String RESULT_RESULTID_GRADDE = "/results/{resultId}/grade";
    public final String ID =  "/{id}";
    public static final String ERROR_TAG = "ERROR";
    public static final String ERROR = "error";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final Integer NULL_VALUE= HttpStatus.NOT_FOUND.value();
    public static final String PASS_FAIL = "PASSWORD IS FAIL";
    public static final String MESSAGE="msg";
    public static final String RESPONSECODE="responseCode";

    public static final String FETCH_TYPE = "FETCH";
    public static final String SUCCESS_STATUS = "SUCCESS";
    public static final String FAIL_STATUS = "FAILURE";
}