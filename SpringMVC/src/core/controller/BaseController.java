package core.controller;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import core.util.Json;


@Controller
@RequestMapping("/base")
public class BaseController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Json json = new Json();
	protected SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected static String line = System.getProperty("line.separator");
	protected static String separator = "---------------------------------------------------";
	protected Gson gson = new Gson();
	protected Map<String, Object> map = new HashMap<String, Object>();
	protected static String SESSION_USER = "SESSION_USER";
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	/**
	 * 用户跳转JSP页面
	 * 
	 * 此方法不考虑权限控制
	 * 
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder, @PathVariable String jspName,HttpServletRequest request){
		return "redirect:/" + folder + "/" + jspName + ".jsp";
	}
	
	@RequestMapping("/{jspName}")
	public String redirectJsp(@PathVariable String jspName,HttpServletRequest request) {
		return "redirect:/" + jspName + ".jsp";
	}
	
	@ExceptionHandler({RuntimeException.class})
	@ResponseBody
	public Json exception(RuntimeException e){
		System.err.println(e.getMessage());
		json.setMessage(e.getMessage());
		return json;
	}
	
}
