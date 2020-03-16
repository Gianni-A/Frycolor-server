package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.NewsReaction;

@Repository
public interface NewsReactionDao extends JpaRepository<NewsReaction, Integer> {

	//We need to see if it is necessary or not, if we added, then it will slow the process.
	/*@Query(value = "SELECT count(1) FROM news_reaction WHERE nw_id = :nwId AND us_id = :userId", nativeQuery = true)
	int verifyReactionToPost(@Param("nwId") int nwId,@Param("userId") int userInfId);*/
}
