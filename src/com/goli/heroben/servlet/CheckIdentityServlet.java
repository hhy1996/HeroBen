package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUpload;
import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.vo.UserBean;

@WebServlet("/CheckIdentity")
public class CheckIdentityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckIdentityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmartUpload smart = new SmartUpload();
		UserBean user = (UserBean) request.getSession().getAttribute("userbean");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		smart.initialize(this.getServletConfig(), request, response);

		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");

			smart.upload();
			String id = smart.getRequest().getParameter("zzid");
			user.setNuaaid(id);
			request.getSession().removeAttribute("userbean");
			request.getSession().setAttribute("userbean", user);
			org.lxh.smart.File upFile = smart.getFiles().getFile(0);
			String ext = upFile.getFileExt();
			if (!ext.equals("jpg") && !ext.equals("png") && !ext.equals("JPG") && !ext.equals("PNG")) {
				PrintWriter writer = response.getWriter();
				writer.println("<script>");
				writer.println("alert('图片格式有误！');");
				writer.println("window.location='check.jsp'");
				writer.println("</script>");
				return;
			}
			if (upFile.getSize() > 2 * 1024 * 1024) {
				PrintWriter writer = response.getWriter();
				writer.println("<script>");
				writer.println("alert('图片大小必须小于2M！');");
				writer.println("window.location='check.jsp'");
				writer.println("</script>");
				return;
			}
			String fileName = user.getTelephone() + ext;
			String path = "checkinf/" + fileName;
			upFile.saveAs(path);
			upFile.saveAs(path);

			DaoFactory.getIUserDaoInstance().doUpdateNuaaid(user.getTelephone(), id);
			user.setDpsrc(fileName);

			request.getSession().removeAttribute("userbean");
			request.getSession().setAttribute("userbean", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('我们会尽快对您进行验证！');");
		writer.println("window.location='UserInfo.jsp'");
		writer.println("</script>");
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
