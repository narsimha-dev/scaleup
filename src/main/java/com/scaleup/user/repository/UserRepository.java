package com.scaleup.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaleup.user.model.User;

 
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
 
	java.util.List<User> findByEmail(String email);
}
