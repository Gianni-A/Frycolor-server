package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gianni.frycolor.entities.User;

public interface SignUpDao extends JpaRepository<User, Integer> {
	
	@Query("SELECT count(u.usId) FROM User u WHERE u.usUser = ?1")
	int findByName(String name);
	
	@Query("SELECT count(u.usId) FROM User u WHERE u.usEmail = :email")
	int findByEmail(@Param("email") String email);
	
	@Modifying
	@Query("UPDATE User u SET u.usStatus = 1, u.usTsUpdated = :date WHERE u.usId = :userId")
	int setStatusRegisterUser(@Param("userId") int userId,
							  @Param("date") String date);

}
