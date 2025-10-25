package com.eProcurement.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private String name;

    private String description;
    private Boolean active = true;
}
