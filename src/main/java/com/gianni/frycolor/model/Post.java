package com.gianni.frycolor.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Post {
	
	private int nwId;
	private int commentId;
	private String comment;
	private String image;
	private int contReactions;
	private List<ResponsePost> listResponses;

}
