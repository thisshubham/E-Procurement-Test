package com.eProcurement.dto;

import lombok.Data;

@Data
public class SubjectDto {
    private String name;
    private String code;
    private String description;
    private Integer totalMarks;
    private Integer passingMarks;
    private Boolean active;
    private Long departmentId;
}
