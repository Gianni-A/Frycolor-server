package com.gianni.frycolor.util;

import java.nio.file.FileSystems;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static com.gianni.frycolor.util.Constantes.EMAIL_REGEX;


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
	
	public static String getTimestamp() {
		/*Timestamp timestamp = null;
		try {
			Timestamp datetimeTemp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.util.Date parsedTimeStamp = dateFormat.parse(String.valueOf(datetimeTemp));

			timestamp = new Timestamp(parsedTimeStamp.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return formatDateTime.format(timestamp);
	}
	
	public static String getDateTimestamp(String date) {
		SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return formatDateTime.format(timestamp);
	}
	
	public static boolean validateEmailFormat(String email) {
		Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
		
		if (email == null) {
			return false;
		}

		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

}
