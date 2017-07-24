package com.cyb.web.utils; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class Convert {
	
	private static String timesTampToDate(long timestamp) {
		String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
				.format(new java.util.Date(timestamp * 1000));
		return date;
	}

	public static void main(String[] args) {
		try {
		 
			System.out.println(timesTampToDate(1421130780L));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Parse date format from oldPattern to newPattern
	public static String stringPattern(String date, String oldPattern,
			String newPattern) {
		if (null == date || null == oldPattern || null == newPattern) {
			return "";
		}
		SimpleDateFormat sdfOld = new SimpleDateFormat(oldPattern);
		SimpleDateFormat sdfNew = new SimpleDateFormat(newPattern);
		Date d = null;
		try {
			d = sdfOld.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdfNew.format(d);
	}

}