package com.eProcurement.constants;

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
    public final String QUESTION = "/questions";
    public final String AUTH = "/api/v1/Eprocurement";
    public final String QUIZE_SUBJECTID_START =    "/quiz/{subjectId}/start";
    public final String QUIZE_QUIZEID_SUBMIT=  "/quiz/{quizId}/submit";
    public final String RESULT_RESULTID_GRADDE = "/results/{resultId}/grade";
    public final String ID =  "/{id}";
}
