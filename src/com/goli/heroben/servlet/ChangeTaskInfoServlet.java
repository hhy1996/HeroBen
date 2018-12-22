package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

@WebServlet("/ChangeTaskInfoServlet")
public class ChangeTaskInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeTaskInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String taskId = request.getParameter("taskId");
		
	/*	UserBean userbean = null;
		try {
			userbean = DaoFactory.getIUserDaoInstance().findUserByTel("123");
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}*/
	    UserBean userbean=(UserBean)request.getSession().getAttribute("userbean");
	    if(userbean==null){
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
		
		if(task.getState().equals("waitingpay")||task.getState().equals("waitingrev")){
		String taskTitle = request.getParameter("title");
		if(!task.getTitle().equals(taskTitle)) {
			ServiceFactory.getTaskServiceInstance().changeTitle(task, taskTitle);
		}
		
		String taskType = request.getParameter("tasktype");
		if(!task.getTaskType().equals(taskType)) {
			
			if(taskType.equals("其它")){
				String tasktypeother = request.getParameter("othertype");
				try {
					DaoFactory.getITaskDaoInstance().doUpdateTaskType(taskId, tasktypeother);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			ServiceFactory.getTaskServiceInstance().addTaskType(userbean, tasktypeother);		
			}
			else{
				try {
					DaoFactory.getITaskDaoInstance().doUpdateTaskType(taskId, taskType);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

		
		String taskContent = request.getParameter("content");
		if(!task.getContent().equals(taskContent)) {
			ServiceFactory.getTaskServiceInstance().changeContent(task, taskContent);
		}


		String taskDeadtime = request.getParameter("deadtime");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		taskDeadtime= taskDeadtime.replace("T", " ");
		
		
		if(!df.format(task.getDeadTime()).equals(taskDeadtime)) {
				try {
					ServiceFactory.getTaskServiceInstance().changeDeadTime(task, df.parse(taskDeadtime));
				} catch (ParseException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
	}
	
		
	if(task.getState().equals("waitingfin")){
		ServiceFactory.getTaskServiceInstance().finTask(userbean, task);
	/*	try {
			DaoFactory.getITaskDaoInstance().doUpdateState(taskId, "waitingcheck");
			DaoFactory.getITaskDaoInstance().doUpdateFinTime(task.getId(), new Date());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
	}
		
	if(task.getState().equals("waitingcheck")){
		ServiceFactory.getTaskServiceInstance().checkTask(userbean, task);
		/*try {
			DaoFactory.getITaskDaoInstance().doUpdateState(taskId, "success");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
	}
	PrintWriter writer = response.getWriter();
	writer.println("<script>");
	writer.println("alert('修改任务成功~');");
	writer.println("window.location='TaskChange.jsp?taskid="+task.getId()+"'");
	writer.println("</script>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
