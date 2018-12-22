package com.goli.heroben.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.lxh.smart.SmartUpload;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.vo.UserBean;

@WebServlet("/ChangeHeadImg")
public class ChangeHeadImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeHeadImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserBean user = (UserBean) request.getSession().getAttribute("userbean");
		File file=new File("headimg/"+user.getDpsrc());
		file.delete();
		
		SmartUpload smart = new SmartUpload();
		smart.initialize(this.getServletConfig(), request, response);
		try {
			String fileName = user.getTelephone() + ".jpg";
			String path = "headimg/" + fileName;
			smart.upload();
			org.lxh.smart.File upFile=smart.getFiles().getFile(0);
			String ext = upFile.getFileExt();
			if(!ext.equals("png")&&!ext.equals("jpg")&&!ext.equals("JPG")&&!ext.equals("PNG"))
				path="102";
			else if(upFile.getSize()>2*1024*1024)
				path="103";
			else
				upFile.saveAs(path);
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			data.put("src", path);
			request.getSession().setAttribute("tmpheadsrc", path);
			DaoFactory.getIUserDaoInstance().doUpdateDpSrc(user.getTelephone(), fileName);
			user.setDpsrc(fileName);
			request.getSession().removeAttribute("userbean");
			request.getSession().setAttribute("userbean", user);
			out.println(data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
