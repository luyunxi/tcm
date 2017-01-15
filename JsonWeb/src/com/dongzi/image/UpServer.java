package com.dongzi.image;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongzi.utils.Base64Coder;

import net.sf.json.JSONObject;
import net.sf.json.JSON;


public class UpServer extends HttpServlet {

	private String file;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		super.doPost(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		file=req.getParameter("file");
		if(file!=null){
			byte[] b= Base64Coder.decodeLines(file);
			String filepath=req.getSession().getServletContext().getRealPath("/files");
			System.out.println(filepath);
			File file=new File(filepath);
			if(!file.exists())
				file.mkdirs();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String str = sdf.format(date);
			FileOutputStream fos=new FileOutputStream(file.getPath()+"/personname"+str+(int)(Math.random()*100)+".png");
			System.out.println(file.getPath());
			fos.write(b);
			fos.flush();
			fos.close();
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			json.put("time", str);
			out.write(json.toString());
			out.close();
		}
	}


}
