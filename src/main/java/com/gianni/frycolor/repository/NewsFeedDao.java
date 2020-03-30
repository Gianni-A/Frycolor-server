package com.gianni.frycolor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.NewsFeed;

@Repository
public interface NewsFeedDao extends JpaRepository<NewsFeed, Integer> {
	
	@Query(value = "SELECT count(1) FROM news WHERE nw_id = :nwId AND nw_status = 1", nativeQuery = true)
	int isPostActive(@Param("nwId") int nwId);
	
	@Query(value = "SELECT postList FROM NewsFeed postList WHERE postList.usId = :userId", nativeQuery = false)
	List<NewsFeed> getNewsFeedList(@Param("userId") int userId);

}
