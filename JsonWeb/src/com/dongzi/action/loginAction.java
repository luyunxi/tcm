package com.dongzi.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import com.dongzi.daoimp.UserDaoImp;

public class loginAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	/**
* 
*/
	private static final long serialVersionUID = 1L;

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

	public void login() {
		try {

			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");

			Map<String, String> json = new HashMap<String, String>();

			String username = this.request.getParameter("userName");
			String password = this.request.getParameter("password");
			UserDaoImp userDaoImp = new UserDaoImp();
			String b = userDaoImp.login(username, password);
			if (b!="") {
				json.put("message", "true");
				json.put("Id", b);
			} else {
				json.put("message", "��¼ʧ�ܣ�");
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
