package com.goli.heroben.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.util.Encode.MD5Util;
import com.goli.heroben.vo.UserBean;

@WebServlet("/ChangeUserInfo")
public class ChangeUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeUserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession ses = request.getSession();
		UserBean user = null;
		user = (UserBean) ses.getAttribute("userbean");
		String name = null;
		name = request.getParameter("name");
		String sex = null;
		sex = request.getParameter("sex");
		String nuaaid = null;
		nuaaid = request.getParameter("id");
		String telephone = null;
		telephone = request.getParameter("phoneNumber");
		String email = null;
		email = request.getParameter("email");
		String password = null;
		password = request.getParameter("password1");
		String otel = user.getTelephone();

		if (name != null && !name.isEmpty()) {
			user.setName(name);
			try {
				DaoFactory.getIUserDaoInstance().doUpdateName(otel, name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (sex != null && !sex.isEmpty()) {
			if (sex.equals("man"))
				user.setSex("男");
			else
				user.setSex("女");
			try {
				DaoFactory.getIUserDaoInstance().doUpdateSex(otel, user.getSex());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (nuaaid != null && !nuaaid.isEmpty()) {
			user.setNuaaid(nuaaid);
			try {
				DaoFactory.getIUserDaoInstance().doUpdateNuaaid(otel, nuaaid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (telephone != null && !telephone.isEmpty()) {
			user.setTelephone(telephone);
			try {
				DaoFactory.getIUserDaoInstance().doUpdateTelephone(otel, telephone);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (email != null && !email.isEmpty()) {
			user.setEmail(email);
			try {
				DaoFactory.getIUserDaoInstance().doUpdateEmail(otel, email);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (password != null && !password.isEmpty()) {
			user.setPassword(MD5Util.MD5(password));
			try {
				ServiceFactory.getUserServiceInstance().resetPassword(user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("UserInfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
