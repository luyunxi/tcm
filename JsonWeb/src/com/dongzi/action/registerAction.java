package com.dongzi.action;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.dongzi.daoimp.UserDaoImp;
import com.opensymphony.xwork2.ActionSupport;

;

public class registerAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	/**
* 
*/
	private static final long serialVersionUID = 2L;

	HttpServletRequest request;
	HttpServletResponse response;
	public String execute(){    
        return SUCCESS;    
    }

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void register() {
		try {

			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");

			Map<String, String> json = new HashMap<String, String>();

			String username = this.request.getParameter("userName");
			String password = this.request.getParameter("password");
			UserDaoImp userDaoImp = new UserDaoImp();
			int row = userDaoImp.registerUsers(username, password);
			if (row>0) {
				json.put("message", "×¢²á³É¹¦£¡");
			} else {
				json.put("message", "×¢²áÊ§°Ü£¡"+row);
			}

			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
