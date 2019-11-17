package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserInformation;

public interface SignUpDao extends JpaRepository<User, Integer> {
	
	@Query("SELECT count(u.usId) FROM User u WHERE u.usUser = ?1")
	int findByName(String name);
	
	@Query("SELECT count(u.usId) FROM User u WHERE u.usEmail = :email")
	int findByEmail(@Param("email") String email);
	
	@Modifying
	@Query("UPDATE User u SET u.usStatus = 1 WHERE u.usId = :userId")
	int setStatusRegisterUser(@Param("userId") int userId);
	
	/*@Modifying
	@Transactional
	@Query(value = "INSERT INTO UserInformation uif (uif.usInfName) VALUES (:userName)", nativeQuery = true)
	int addNewUser(@Param("userName") String userName);*/
	
	@Modifying
	@Query("UPDATE User u SET u.usInfId = :userInfId WHERE u.usId = :userId")
	int setIdUserInf(@Param("userInfId") int userInfId, @Param("userId") int userId);

}
