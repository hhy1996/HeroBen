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
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

@WebServlet("/CloseTask")
public class CloseTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CloseTaskServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String refer = request.getParameter("resPath");

		HttpSession ses = request.getSession();
		String taskId = request.getParameter("taskId");
		UserBean userbean = (UserBean) request.getSession().getAttribute("userbean");
		if (userbean == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		TaskBean task = null;
		try {
			task = DaoFactory.getITaskDaoInstance().findTaskById(taskId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserBean user = (UserBean) ses.getAttribute("userbean");

		ServiceFactory.getTaskServiceInstance().closeTask(user, task);

		request.getRequestDispatcher(refer).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
