package com.eProcurement.dto;

import com.eProcurement.entity.Department;
import com.eProcurement.entity.Student;
import com.eProcurement.entity.TestResult;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
public class TeacherDto {

    private Department department;

    private String specialization;

    private String employeeId;

    private List<Student> students = new ArrayList<>();

    private List<TestResult> gradedResults = new ArrayList<>();
}
