package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.ResponseReaction;

@Repository
public interface ResponseReactionDao extends JpaRepository<ResponseReaction, Integer> {
	
	@Query(value = "SELECT count(1) FROM response_reaction WHERE nw_res_id = :nwResId", nativeQuery=true)
	int getCountResReactionNews(@Param("nwResId") int nwResId);
	
	@Query(value = "SELECT rr_id FROM response_reaction WHERE nw_res_id = :nwResId AND us_id = :userId", nativeQuery = true)
	Integer getResponseReaction(@Param("nwResId") int nwResId, @Param("userId") int userId);

}
