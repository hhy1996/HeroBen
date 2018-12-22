package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.factory.ServiceFactory;
import com.goli.heroben.vo.UserBean;
import com.goli.heroben.vo.WithdrawBean;


@WebServlet("/WithDraw")
public class WithDrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WithDrawServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserBean userbean = (UserBean) request.getSession().getAttribute("userbean");
		if (userbean == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		double money=Double.parseDouble(request.getParameter("money"));
		String userplatid=request.getParameter("userplatid");
		String platname=request.getParameter("platname");
		if(userbean.getLeftmoney()>=money){
			Date time = new Date();
			String id = Long.toString(time.getTime());
			WithdrawBean withBean=new WithdrawBean();
			withBean.setId(id);
			withBean.setMoney(money);
			withBean.setPaytime(time);
			withBean.setPlatformid("1");
			withBean.setPlatname(platname);
			withBean.setUserid(userbean.getTelephone());
			withBean.setUserplatid(userplatid);
			System.out.println(withBean);
			if(ServiceFactory.getMoneyServiceInstance().getMoney(userbean.getTelephone(), withBean))
			{
				PrintWriter writer = response.getWriter();
				writer.println("<script>");
				writer.println("alert('取款成功,我们将尽快审核并将钱汇入您的账户');");
				writer.println("window.location='UserInfo.jsp'");
				writer.println("</script>");
				return;
			}
		}
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('对不起,您的余额不足');");
		writer.println("window.location='UserInfo.jsp'");
		writer.println("</script>");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
