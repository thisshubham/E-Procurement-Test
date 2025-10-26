package com.eProcurement.dto;

import com.eProcurement.entity.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectDto {
    private String name;
    private String code;
    private String description;
    private Integer totalMarks;
    private Integer passingMarks;
    private Boolean active;
    private Long departmentId;
    public SubjectDto(Subject subject) {
        this.name = subject.getName();
        this.code = subject.getCode();
        this.description = subject.getDescription();
        this.totalMarks = subject.getTotalMarks();
        this.passingMarks = subject.getPassingMarks();
        this.active = subject.getActive();

        // Handle the ManyToOne relationship
        if (subject.getDepartment() != null) {
            this.departmentId = subject.getDepartment().getId();
        }
    }


}
