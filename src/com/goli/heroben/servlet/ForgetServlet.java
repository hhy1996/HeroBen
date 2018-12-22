package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.vo.UserBean;

@WebServlet("/Forget")
public class ForgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForgetServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String code = request.getParameter("checkcode");
		String id = request.getParameter("telnum");
		String pwd = request.getParameter("pwd");
		String surecode = (String) request.getSession().getAttribute("telcode");
		String result;
		if (surecode != null && !surecode.equals("error") && !surecode.equals("nouser") && surecode.equals(code)
				&& id != null && pwd != null) {
			UserBean user = null;
			if (id.indexOf('@') != -1)
				try {
					user = DaoFactory.getIUserDaoInstance().findUserByEmail(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else
				try {
					user = DaoFactory.getIUserDaoInstance().findUserByTel(id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (user != null)
				if (ServiceFactory.getUserServiceInstance().resetPassword(user, pwd))
					result = "success";
				else
					result = "fail";
			else
				result = "nouser";
		} else
			result = "codeError";
		JSONObject data = new JSONObject();
		try {
			data.put("status", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println(data.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
