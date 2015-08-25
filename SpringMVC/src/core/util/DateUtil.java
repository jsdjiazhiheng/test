package core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat dateFormatter;
	private static String FORMATTYPE_1 = "yyyy-MM-dd HH:mm:ss";
	private static String FORMATTYPE_2 = "yyyyMMddHHmmss";
	private static Date staticDate = new Date();
	private static String formateDate = "";
	
	public static String formatDate(Date date){
		dateFormatter = new SimpleDateFormat(FORMATTYPE_1);
		formateDate = dateFormatter.format(date);
		return formateDate;
	}
	
	public static String formatDate(){
		dateFormatter = new SimpleDateFormat(FORMATTYPE_2);
		formateDate = dateFormatter.format(staticDate);
		return formateDate;
	}
	
}
