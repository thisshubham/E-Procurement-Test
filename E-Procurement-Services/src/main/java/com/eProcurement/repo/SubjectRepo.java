package com.eProcurement.repo;

import com.eProcurement.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo  extends JpaRepository<Subject, Long> {
    List<Subject> findSubjectsByDepartment_Id(Long departmentId);


}
