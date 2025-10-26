package com.eProcurement.repo;

import com.eProcurement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
