package com.gianni.frycolor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gianni.frycolor.entities.NewsFeed;

public interface NewsFeedDao extends JpaRepository<NewsFeed, Integer> {
	
	@Query(value = "SELECT nws FROM NewsFeed nws WHERE nws.nw_us_id = :userId", nativeQuery = false)
	NewsFeed getNewsUser(@Param("userId") int userId);

}
