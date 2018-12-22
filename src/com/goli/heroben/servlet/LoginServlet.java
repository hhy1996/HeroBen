package com.goli.heroben.servlet;

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
import com.goli.heroben.geetestsdk.GeetestLib;
import com.goli.heroben.service.IUserService;
import com.goli.heroben.vo.UserBean;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		IUserService userservice = ServiceFactory.getUserServiceInstance();
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
				GeetestConfig.isnewfailback());

		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		String id = request.getParameter("userid");
		String pwd = request.getParameter("userpwd");
		UserBean result;
		if(id==null||pwd==null)
			result=null;
		else
			result = userservice.login(id, pwd);
		if(result!=null)
			request.getSession().setAttribute("userbean", result);
		// 从session中获取gt-server状态
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		// 从session中获取userid
		String userid = (String) request.getSession().getAttribute("userid");

		int gtResult = 0;

		if (gt_server_status_code == 1) {
			// gt-server正常，向gt-server进行二次验证
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
		} else {
			// gt-server非正常情况下，进行failback模式验证
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
		}

		if (gtResult == 1) {
			// 验证成功
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				if(result!=null)
					data.put("status", "success");
				else
					data.put("status", "pwderror");
				data.put("version", gtSdk.getVersionInfo());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
		} else {
			// 验证失败
			JSONObject data = new JSONObject();
			try {
				data.put("status", "fail");
				data.put("version", gtSdk.getVersionInfo());
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
