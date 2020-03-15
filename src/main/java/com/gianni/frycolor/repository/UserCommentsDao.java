package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.UserComments;

@Repository
public interface UserCommentsDao extends JpaRepository<UserComments, Integer> {

}
