package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goli.heroben.dao.ITaskDao;
import com.goli.heroben.dao.IUserDao;
import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

/**
 * Servlet implementation class Taskshowser
 */
@WebServlet("/TaskDetail")
public class TaskDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TaskDetail() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("taskid");
		String action = request.getParameter("action");
		ITaskDao taskdao;
		IUserDao userdao;
		TaskBean taskBean = null;
		UserBean userBean = null;
		try {
			taskdao = DaoFactory.getITaskDaoInstance();
			userdao = DaoFactory.getIUserDaoInstance();
			taskBean = taskdao.findTaskById(id);
			userBean = userdao.findUserByTel(taskBean.getPubid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("taskinfo", taskBean);
		request.setAttribute("pubinfo", userBean);
		if (action != null && action.equals("receive")) {
			UserBean user = (UserBean) request.getSession().getAttribute("userbean");
			if (user == null) {
				response.sendRedirect("login.jsp");
				return;
			}
			try {
				taskBean = DaoFactory.getITaskDaoInstance().findTaskById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (ServiceFactory.getTaskServiceInstance().receiveTask(user, taskBean)) {
				PrintWriter writer = response.getWriter();
				writer.println("<script>");
				writer.println("alert('任务接收成功^_^');");
				writer.println("window.location='TaskDetail?taskid=" + taskBean.getId() + "'");
				writer.println("</script>");
				return;
			}else{
				PrintWriter writer = response.getWriter();
				writer.println("<script>");
				writer.println("alert('任务接收失败QAQ');");
				writer.println("window.location='TaskDetail?taskid=" + taskBean.getId() + "'");
				writer.println("</script>");
				return;
			}
		} else
			request.getRequestDispatcher("taskshow.jsp").forward(request, response);

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
