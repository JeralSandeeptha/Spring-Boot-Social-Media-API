package com.jeral.socialmedia.repo;

import com.jeral.socialmedia.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
