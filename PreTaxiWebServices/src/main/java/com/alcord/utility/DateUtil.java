package com.alcord.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date parseStandardDocumentDate(String dateString) throws ParseException {
		Date parsed = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		parsed = format.parse(dateString);
		return parsed;
	}

	public static String parseStandardDocumentString(Date date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String parsed = format.format(date);
		return parsed;
	}

}
