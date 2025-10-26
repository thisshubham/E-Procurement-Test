package com.eProcurement.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"department", "teacher"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"department", "teacher"})
public class Student extends Admin {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonBackReference
    private Teacher teacher;

    @Column(length = 20)
    private String studentId;

    @Column(length = 20)
    private String enrollmentYear;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestResult> testResults = new ArrayList<>();
}
