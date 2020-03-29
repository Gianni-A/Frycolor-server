package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.ResponseReaction;

@Repository
public interface ResponseReactionDao extends JpaRepository<ResponseReaction, Integer> {

}
