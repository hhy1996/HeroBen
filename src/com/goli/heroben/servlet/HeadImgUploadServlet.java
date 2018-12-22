package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.lxh.smart.SmartUpload;

/**
 * Servlet implementation class HeadImgUploadServlet
 */
@WebServlet("/HeadImgUploadServlet")
public class HeadImgUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HeadImgUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String src;
		if((src=(String)request.getSession().getAttribute("tmpheadsrc"))!=null)
		{
			
			java.io.File file=new File(getServletContext().getRealPath("/")+src);
			if(file.exists()){
				file.delete();
			}
		}
		SmartUpload smart = new SmartUpload();
		smart.initialize(this.getServletConfig(), request, response);
		try {
			String fileName = System.currentTimeMillis() + ".jpg";
			String path = "tempupload/" + fileName;
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
			out.println(data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
