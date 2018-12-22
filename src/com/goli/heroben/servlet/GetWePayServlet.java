package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.wxpay.sdk.WXPay;
import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.pay.wechat.MyConfig;
import com.goli.heroben.vo.PaymentBean;
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

@WebServlet("/WePayServlet")
public class GetWePayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetWePayServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserBean user = (UserBean) request.getSession().getAttribute("userbean");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		MyConfig config = null;
		String taskid = request.getParameter("taskid");
		String cost = (String) request.getSession().getAttribute(taskid + "cost");
		double cost2 = Double.parseDouble(cost);
		cost2 = cost2 * 100;
		int cost3 = (int) cost2;
		cost = String.valueOf(cost3);
		try {
			config = new MyConfig();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		TaskBean taskbean = null;
		try {
			taskbean = DaoFactory.getITaskDaoInstance().findTaskById(taskid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (taskbean != null) {
			if (taskbean.getPayId() == null) {
				if (config != null) {
					WXPay wxpay = new WXPay(config);
					String out_trade_no = String.valueOf(System.currentTimeMillis());
					Map<String, String> data = new HashMap<String, String>();
					data.put("body", "HeroBen侠客帮");
					data.put("out_trade_no", out_trade_no);
					data.put("device_info", "");
					data.put("fee_type", "CNY");
					data.put("total_fee", cost);
					data.put("spbill_create_ip", "58.213.91.20");
					data.put("notify_url", "http://www.t-tzy.cn/WePayNotify.jsp");
					data.put("trade_type", "NATIVE"); // 此处指定为扫码支付
					PaymentBean payBean = new PaymentBean();
					payBean.setId(out_trade_no);
					payBean.setMoney(cost2 / 100);
					payBean.setPaytime(new Date());
					payBean.setPlatformid("");
					payBean.setPlatname("wechat");
					payBean.setUserid(user.getTelephone());
					try {
						Map<String, String> resp = wxpay.unifiedOrder(data);
						String return_code = (String) resp.get("return_code");
						String result_code = (String) resp.get("result_code");
						if ("SUCCESS".equals(return_code)) {
							if ("SUCCESS".equals(result_code)) {
								String url = (String) resp.get("code_url");
								payBean.setUrl(url);
								ServiceFactory.getMoneyServiceInstance().putMoney(payBean, taskid);
								request.getSession().setAttribute(taskid + "url", url);
								response.sendRedirect("pay.jsp?taskid=" + taskid + "&type=wechat");
								return;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else{
				PaymentBean payBean=null;
				try {
					payBean=DaoFactory.getIPaymentDaoInstance().findPaymentById(taskbean.getPayId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(payBean!=null&&payBean.getUrl()!=null)
				{
					request.getSession().setAttribute(taskid + "url", payBean.getUrl());
					response.sendRedirect("pay.jsp?taskid=" + taskid + "&type=wechat");
					return;
				}
			}
		}
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('付款时出现了一些问题,正在返回个人中心');");
		writer.println("window.location='UserInfo.jsp'");
		writer.println("</script>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
