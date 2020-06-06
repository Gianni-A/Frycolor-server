package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.UserInformation;

@Repository
public interface ProfileUserDao extends JpaRepository<UserInformation, Integer> {
	
	@Query("SELECT uf FROM UserInformation uf WHERE uf.usInfId = :userId")
	UserInformation getUserInfoById(@Param("userId") int userId);
	
	@Query(value = "SELECT count(1) FROM users_app WHERE us_inf_id = :userInfId", nativeQuery = true)
	int existUser(@Param("userInfId") int userInfId);
	
	@Query(value = "SELECT count(1) FROM users_app WHERE us_inf_id = :userInfId AND us_status = 1", nativeQuery = true)
	int isUserActive(@Param("userInfId") int userInfId);
	
	@Query(value = "SELECT CONCAT(us_inf_name, ' ', us_inf_lastname) "
			+ "FROM user_information AS uf "
			+ "INNER JOIN users_app as u ON u.us_inf_id = uf.us_inf_id "
			+ "WHERE u.us_id = :usId", nativeQuery = true)
	String getCompleteName(@Param("usId") int usId);
	
}
