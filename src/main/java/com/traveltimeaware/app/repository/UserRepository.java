package com.traveltimeaware.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.traveltimeaware.app.domain.profile.User;
import com.traveltimeaware.app.domain.Mean;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
}
