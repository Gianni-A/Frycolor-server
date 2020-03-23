package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.NewsFeed;

@Repository
public interface NewsFeedDao extends JpaRepository<NewsFeed, Integer> {
	
	@Query(value = "SELECT count(1) FROM news WHERE nw_id = :nwId AND nw_status = 1", nativeQuery = true)
	int isPostActive(@Param("nwId") int nwId);

}
