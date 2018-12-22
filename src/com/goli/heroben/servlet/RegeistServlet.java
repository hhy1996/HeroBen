package com.goli.heroben.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.service.IUserService;
import com.goli.heroben.vo.UserBean;

/**
 * Servlet implementation class RegeistServlet
 */
@WebServlet("/RegeistServlet")
public class RegeistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegeistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject data = new JSONObject();
		String rightcode = (String) request.getSession().getAttribute("telcode");
		String code = request.getParameter("checkcode");
		if (rightcode != null && rightcode.equals(code)) {
			String telephone = request.getParameter("telnum");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String sex = request.getParameter("sex");
			String pwd = request.getParameter("pwd");
			String imgsrc = (String) request.getSession().getAttribute("tmpheadsrc");
			if (imgsrc != null && imgsrc != "102" && imgsrc != "103") {
				File file = new File(getServletContext().getRealPath("/") + imgsrc);
				if (file.renameTo(new File(getServletContext().getRealPath("/") + "headimg/" + telephone + ".jpg"))) {
					imgsrc = telephone + ".jpg";
				} else {
					imgsrc = "default.jpg";
				}
			} else
				imgsrc = "default.jpg";
			UserBean user = new UserBean();
			user.setTelephone(telephone);
			user.setEmail(email);
			user.setName(username);
			user.setSex(sex);
			user.setPassword(pwd);
			user.setDpsrc(imgsrc);
			IUserService userService = ServiceFactory.getUserServiceInstance();
			int reg_code = userService.regeist(user);
			try {
				switch (reg_code) {
				case -1:
					data.put("status", "nulluser");
					break;
				case 0:

					data.put("status", "success");
					break;
				case 1:
					data.put("status", "erroremail");
					break;
				case 2:
					data.put("status", "errortel");
					break;
				case 3:
					data.put("status", "hadit");
					break;
				case 5:
					data.put("status", "errorsex");
					break;
				default:
					data.put("status", "unknow");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(data.toString());
		} else {
			try {
				data.put("status", "codeerror");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(data.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
