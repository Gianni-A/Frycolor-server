package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.NewsReaction;

@Repository
public interface NewsReactionDao extends JpaRepository<NewsReaction, Integer> {

	@Query(value = "SELECT count(1) FROM news_reaction WHERE nw_id = :nwId", nativeQuery=true)
	int getCountReactionNews(@Param("nwId") int nwId);
	
	@Query(value = "SELECT nwr_id FROM news_reaction WHERE nw_id = :nwId AND us_id = :userId", nativeQuery = true)
	Integer getUsersReaction(@Param("nwId") int nwId, @Param("userId") int userId);
}
