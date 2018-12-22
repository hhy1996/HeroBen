package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.vo.TaskBean;

@WebServlet("/CheckPay")
public class CheckPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckPayServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String taskid = request.getParameter("_taskid");
		TaskBean taskBean = null;
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			taskBean = DaoFactory.getITaskDaoInstance().findTaskById(taskid);
			if (taskBean != null && "waitingrev".equals(taskBean.getState()))
				data.put("status", "success");
			else
				data.put("status", "fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(data.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
