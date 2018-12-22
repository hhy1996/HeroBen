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
 * Servlet implementation class FinishTask
 */
@WebServlet("/FinishTask")
public class FinishTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FinishTaskServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserBean userbean = (UserBean) request.getSession().getAttribute("userbean");
		if (userbean == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		String taskid=request.getParameter("taskid");
		if(taskid!=null)
		{
			TaskBean taskBean=null;
			try {
				taskBean = DaoFactory.getITaskDaoInstance().findTaskById(taskid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(taskBean!=null)
				ServiceFactory.getTaskServiceInstance().finTask(userbean, taskBean);
		}
		response.sendRedirect("UserGetTask.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
