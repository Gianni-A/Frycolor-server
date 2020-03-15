package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.UserMedia;

@Repository
public interface UserMediaDao extends JpaRepository<UserMedia, Integer> {

}
