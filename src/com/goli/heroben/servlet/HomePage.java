package com.goli.heroben.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.service.ITaskService;
import com.goli.heroben.service.IUserService;
import com.goli.heroben.vo.TaskBean;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ITaskService taskservice=ServiceFactory.getTaskServiceInstance();
		IUserService userservice=ServiceFactory.getUserServiceInstance();
		List<String> typelist=taskservice.getTaskType();
		List<String> highscorePeople=userservice.getSixHighScoreUser();
		List<TaskBean> recommendTask=taskservice.getTwelveUnRevTasks();
		request.setAttribute("typelist", typelist);
		request.setAttribute("highBen", highscorePeople);
		request.setAttribute("recommend", recommendTask);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
