package com.eProcurement.repo;

import com.eProcurement.entity.Student;
import com.eProcurement.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByDepartmentId(Long departmentId);

//    @Query(
//            value = "SELECT s.*, u.username, u.password, u.email, u.full_name, u.role, u.active, u.created_at, u.updated_at " +
//                    "FROM students s JOIN users u ON s.id = u.id " +
//            "WHERE s.student_id = :studentIdString",
//    nativeQuery = true)

//    Student findStudentByStudentIdCode(@Param("studentIdString") Long studentIdString);

    @Query("SELECT s FROM Student s WHERE s.studentId = :studentIdString")
    Student findStudentByStudentIdCode(@Param("studentIdString") String studentIdString);

}