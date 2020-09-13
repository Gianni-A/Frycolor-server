package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gianni.frycolor.entities.User;

public interface SessionDao extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT u FROM User u WHERE u.usUser = :username AND u.usPassword = :password", nativeQuery = false)
	User logIn(@Param("username") String username, @Param("password") String password);

	@Query(value = "SELECT us_id FROM users_app WHERE us_email = :emailID", nativeQuery = true)
	Integer getUserIdByEmail(@Param("emailID") String emailID);

	@Query(value = "SELECT u FROM User u WHERE u.usId = :userId", nativeQuery = false)
	User getUserCredentials(@Param("userId") int userId);
}
