package core.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class StringUtil {
	
	private static Gson gson = new Gson();
	private static String jsonString = "";
	private static String result = "";
	private static String line = System.getProperty("line.separator");
	
	/**
	 * 转换成 Json 并打印
	 */
	public void printToJson(Object obj,List<Object> list,Map<String, Object> map) {
		if(obj != null) {
			jsonString = gson.toJson(obj);
			result += "obj : [ " + jsonString + " ]" + line;
		}
		if(list != null) {
			jsonString = gson.toJson(list);
			result += "list : " + jsonString + line;
		}
		if(map != null) {
			jsonString = gson.toJson(map);
			result += "map : " + jsonString;
		}
		System.err.println(result);
	}
	
}
