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

}
