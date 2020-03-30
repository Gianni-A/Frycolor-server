package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.NewsReaction;

@Repository
public interface NewsReactionDao extends JpaRepository<NewsReaction, Integer> {

	
}
