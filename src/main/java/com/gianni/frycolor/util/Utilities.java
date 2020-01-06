package com.gianni.frycolor.util;

import java.nio.file.FileSystems;
import java.util.Base64;


public class Utilities {
	
	public static String getPath(String path) {
		String projectDirectory = FileSystems.getDefault()
		        .getPath("")
		        .toAbsolutePath()
		        .toString();
		String setPath = projectDirectory + "\\" + path;
		return setPath;
	}
	
	public static String encodeOrDecodeBase64(String code, boolean encode) {
		String encodedOrDecodedBytes;
		byte[] decodedBytes;
		if(encode)
			encodedOrDecodedBytes = Base64.getEncoder().encodeToString(code.getBytes());
		else {
			decodedBytes = Base64.getDecoder().decode(code);
			encodedOrDecodedBytes = new String(decodedBytes);
		}
			
		return encodedOrDecodedBytes;
	}

}
