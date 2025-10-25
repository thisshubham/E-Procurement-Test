package com.eProcurement.dto;

import com.eProcurement.entity.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;
    private String description;
    private Boolean active = true;

    public DepartmentDto(Department department) {
        id= department.getId();
        name = department.getName();
        description= department.getDescription();
        active= department.getActive();
    }
}
