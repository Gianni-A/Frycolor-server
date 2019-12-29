package com.gianni.frycolor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;


public interface FriendsDao extends JpaRepository<UserFriends, Integer> {
	
	@Query(value = "INSERT INTO user_friends (frd_us_id, frd_us_id_uf) VALUES (:frdUsId, :frdUsIdUf) RETURNING frd_id", 
		   nativeQuery = true)
	int addNewFriend(@Param("frdUsId") int userInfId,
							 @Param("frdUsIdUf") int userFrdId);
	
	
	@Query(value = "SELECT frd_us_id_uf FROM user_friends WHERE frd_us_id = :userId", nativeQuery = true)
	List<Integer> getIdListFriends(@Param("userId") int userId);
	
	
	@Query(value = "SELECT usInf FROM User us INNER JOIN UserInformation usInf ON us.usInfId = usInf.usInfId WHERE us.usId = :userIdFrd", nativeQuery = false)
	UserInformation getInfFriend(@Param("userIdFrd") int userIdFrd);
}
