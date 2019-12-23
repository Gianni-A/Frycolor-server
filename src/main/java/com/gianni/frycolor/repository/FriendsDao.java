package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gianni.frycolor.entities.UserFriends;

public interface FriendsDao extends JpaRepository<UserFriends, Integer> {
	
	@Query(value = "INSERT INTO user_friends (frd_us_id, frd_us_id_uf) VALUES (:frdUsId, :frdUsIdUf) RETURNING frd_id", nativeQuery = true)
	int addNewFriend(@Param("frdUsId") int userInfId,
							 @Param("frdUsIdUf") int userFrdId);

}
