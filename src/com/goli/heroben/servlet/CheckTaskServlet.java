package com.goli.heroben.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

/**
 * Servlet implementation class CheckTask
 */
@WebServlet("/CheckTask")
public class CheckTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckTaskServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserBean userbean = (UserBean) request.getSession().getAttribute("userbean");
		if (userbean == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		String taskid=request.getParameter("taskid");
		String score=request.getParameter("score");
		if(taskid!=null&&score!=null)
		{
			TaskBean taskBean=null;
			try {
				taskBean = DaoFactory.getITaskDaoInstance().findTaskById(taskid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(taskBean!=null){
				taskBean.setScore(Double.parseDouble(score));
				ServiceFactory.getTaskServiceInstance().checkTask(userbean, taskBean);
				if(taskBean.getPayType().equals("online")){
					request.getSession().removeAttribute("userbean");
					request.getSession().setAttribute("userbean", userbean);
				}
			}
		}
		response.sendRedirect("UserPutTask.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
