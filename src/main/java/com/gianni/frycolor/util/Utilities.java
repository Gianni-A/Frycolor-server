package com.gianni.frycolor.util;

import java.nio.file.FileSystems;

public class Utilities {
	
	public static String getPath(String path) {
		String projectDirectory = FileSystems.getDefault()
		        .getPath("")
		        .toAbsolutePath()
		        .toString();
		String setPath = projectDirectory + "\\" + path;
		return setPath;
	}

}
