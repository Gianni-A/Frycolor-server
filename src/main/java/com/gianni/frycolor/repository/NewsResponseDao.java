package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.NewsResponse;

@Repository
public interface NewsResponseDao extends JpaRepository<NewsResponse, Integer> {
	
	

}
