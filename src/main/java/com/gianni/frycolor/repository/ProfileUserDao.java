package com.gianni.frycolor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gianni.frycolor.entities.UserInformation;

public interface ProfileUserDao extends JpaRepository<UserInformation, Integer> {

}
