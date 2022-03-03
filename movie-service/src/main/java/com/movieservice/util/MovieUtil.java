package com.movieservice.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class MovieUtil {
	
	public static String generateId(String name, Date date) {
		StringBuilder id = new StringBuilder(name);
		System.out.println("date"+date.toString());
		id.append("_").append(date.toString().substring(0, 4));
		return id.toString();
	}

	public static boolean isEmpty(String field) {
		
		if(null ==field || field.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
public static String formSearchCriteria(String field) {
	StringBuilder search = new StringBuilder("%");
	search.append(field).append("%");
    return search.toString();
	}

public static boolean validateDate(Date releaseDate) {
	if(releaseDate == null) {
		return false;
	}
	DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    sdf.setLenient(false);
    try {
        sdf.parse(releaseDate.toString());
    } catch (ParseException e) {
        return false;
    }
    

	return false;
}

public static boolean validYear(String releaseYear) {
	 if(isEmpty(releaseYear)) {
		 return false;
	 }
	 Pattern pattern = Pattern.compile(".*[^0-9].*");
	return pattern.matcher(releaseYear).matches();
}
}
