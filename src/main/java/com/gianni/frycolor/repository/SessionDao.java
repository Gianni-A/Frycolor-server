package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.User;

@Repository
public interface SessionDao extends JpaRepository<User, Integer> {

	@Query(value = "SELECT us_id FROM users_app WHERE us_email = :emailID", nativeQuery = true)
	Integer getUserIdByEmail(@Param("emailID") String emailID);

	@Query(value = "SELECT u FROM User u WHERE u.usId = :userId", nativeQuery = false)
	User getUserCredentials(@Param("userId") int userId);
}
