package com.traveltimeaware.app.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.traveltimeaware.app.security.domain.User;

@Component
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
}
