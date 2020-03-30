package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.UserMedia;

@Repository
public interface UserMediaDao extends JpaRepository<UserMedia, Integer> {

	@Query(value = "SELECT usm.usMdPath FROM UserMedia usm WHERE usMdId = :usMdId", nativeQuery = false)
	String getPathImage(@Param("usMdId") int usMdId);
}
