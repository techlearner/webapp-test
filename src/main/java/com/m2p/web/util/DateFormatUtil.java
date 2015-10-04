package com.m2p.web.util;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class DateFormatUtil {

	public static Date parse(String date) throws YappayException {
		if (date != null) {
			String dateFmt = "YYYYMMDD";
			DateFormat importDateFormat = new SimpleDateFormat(dateFmt);
			try {
				return importDateFormat.parse(date);
			} catch (Exception e) {
				throw new YappayException(YappayExceptionConstant.INVALID_DATE, "Invalid Date",null,null);
			}
		}
		return null;
	}
	
	public static Calendar parseDate(String date) throws YappayException {
		return dateToCalendar(parse(date));
	}


	public static DateFormat getDateFormat() throws ParseException {
		String dateFmt = "YYYYMMDD";
		return new SimpleDateFormat(dateFmt);
	}

	
	public static Calendar dateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}
}
