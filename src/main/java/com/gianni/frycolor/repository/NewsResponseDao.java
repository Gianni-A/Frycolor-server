package com.gianni.frycolor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.NewsResponse;

@Repository
public interface NewsResponseDao extends JpaRepository<NewsResponse, Integer> {
	
	@Query(value = "SELECT count(1) FROM news_response WHERE nw_res_id = :nwResId AND nw_res_status = 1", nativeQuery = true)
	int isResponseActive(@Param("nwResId") int nwResId);
	
	
	@Query(value= "SELECT responseList FROM NewsResponse responseList WHERE responseList.nwComOriginId = :nwId AND responseList.nwResStatus = 1", nativeQuery = false)
	List<NewsResponse> getAllResponsesByNwId(@Param("nwId") int nwId);

}
