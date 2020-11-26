package com.gianni.frycolor.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.repository.NewsFeedDao;

@Component
public class NewsFeedDaoImpl {
	
	@Autowired
	private NewsFeedDao newsFeedRepository;
	
	public NewsFeed getNewsFeedById(int nwId) {
		return newsFeedRepository.getOne(nwId);
	}

}
