package com.eProcurement.entity;

import javax.persistence.*;

import com.eProcurement.dto.TeacherDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"department", "students", "gradedResults"})
@EqualsAndHashCode(callSuper = true, exclude = {"department", "students", "gradedResults"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Teacher extends Admin {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(length = 50)
    private String specialization;

    @Column(length = 20)
    private String employeeId;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"teacher", "department"}) // prevent looping
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "gradedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TestResult> gradedResults = new ArrayList<>();



}