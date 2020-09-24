package com.gianni.frycolor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserFriends;


public interface FriendsDao extends JpaRepository<UserFriends, Integer> {
	
	@Query(value = "SELECT uf FROM UserFriends uf WHERE uf.frdUsId = :userId", nativeQuery = false)
	List<UserFriends> getIdListFriends(@Param("userId") User userId);
	
	@Query(value = "SELECT count(frd_id) FROM user_friends WHERE "
			+ "(frd_us_id = :userId AND frd_us_id_uf = :userIdLogged) OR "
			+ "(frd_us_id = :userIdLogged AND frd_us_id_uf = :userId)", nativeQuery = true)
	Integer isFriendWithUserLogged(@Param("userId") int userId, @Param("userIdLogged") int userIdLogged);
	
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM UserFriends WHERE frdUsId = :user AND frdUsIdUf = :friend", nativeQuery = false)
	void deleteFriend(@Param("user") User user, @Param("friend") User friend);
}
