package com.goli.heroben.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.searchstg.ISearchPolicy;
import com.goli.heroben.searchstg.SearchPolicyImp;
import com.goli.heroben.service.ITaskService;
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

/**
 * Servlet implementation class TaskSer
 */
@WebServlet("/TaskSer")
public class TaskSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskSer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TaskBean> all = null;
		List<String> TaskTypeAll =null;
		List<TaskBean> Kall = new ArrayList<>();
		
		ITaskService ts = ServiceFactory.getTaskServiceInstance();
		String tasktype = request.getParameter("type");
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("searchValue");
		
		ISearchPolicy isp = new SearchPolicyImp();
        

		if(tasktype==null)tasktype="浏览所有";
		
		
        if(request.getSession().getAttribute("TaskList")==null){
			
			TaskTypeAll = ts.getTaskType(); 
			request.getSession().setAttribute("TaskType", TaskTypeAll);
		}
        UserBean userBean=(UserBean) request.getSession().getAttribute("userbean");
		if(userBean==null)
		{
			userBean=new UserBean();
			userBean.setTelephone("1");
		}
        all = ts.showAllTask(userBean);
		if(tasktype.equals("浏览所有")&&(search==null||search.equals("null")))
		{
			request.setAttribute("info", all);
			request.getRequestDispatcher("TaskList.jsp").forward(request, response);
		}else if((search!=null&&!search.equals("null"))){
			try {
				Kall = isp.search(all,search);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("info", Kall);
			request.getRequestDispatcher("TaskList.jsp").forward(request, response);
		}
		else{
			for(int i=0;i<all.size();i++)
			{
				if(all.get(i).getTaskType().equals(tasktype))
				{
					Kall.add(all.get(i));
				}
			}
			request.setAttribute("info", Kall);
			request.getRequestDispatcher("TaskList.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
