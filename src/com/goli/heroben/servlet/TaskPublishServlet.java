package com.goli.heroben.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUpload;
import org.lxh.smart.SmartUploadException;

import com.goli.heroben.service.proxy.TaskServiceProxy;
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

/**
 * Servlet implementation class TaskPublishServlet
 */
@WebServlet("/TaskPublishServlet")
public class TaskPublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskPublishServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		SmartUpload su = new SmartUpload();// 创建SmartUpload对象
		su.initialize(this.config, request, response);// 初始化设置
		su.setAllowedFilesList("jpeg,jpg,png,JPG,PNG");// 设置允许上传的文件扩展名
		su.setMaxFileSize(3 * 1024 * 1024);// 设置单个文件允许最大长度(单位：字节)
		su.setTotalMaxFileSize(15 * 1024 * 1024);// 设置上传文件的总大小(单位：字节)
		try {
			su.upload();
		} catch (SmartUploadException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		} // 将文件数据上传
			// 注意：使用SmartUpload对象获取request这句代码要放在upload()方法后面，否则拿不到数据
		TaskServiceProxy taskservice = new TaskServiceProxy();
		UserBean user = (UserBean) request.getSession().getAttribute("userbean");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		TaskBean task = new TaskBean();

		Date time = new Date();
		String id = Long.toString(time.getTime());
		task.setId(id);
		task.setPubTime(time);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String deadtime = su.getRequest().getParameter("deadtime");
		deadtime = deadtime.replace("T", " ");

		try {
			task.setDeadTime(df.parse(deadtime));
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		String paytype = su.getRequest().getParameter("paytype");
		task.setPayType(paytype);

		String title = su.getRequest().getParameter("title");
		task.setTitle(title);

		String content = su.getRequest().getParameter("content");
		task.setContent(content);

		if (paytype.equals("online")) {
			String cost = su.getRequest().getParameter("cost");
			task.setCost(cost);
		} else {
			String cost2 = su.getRequest().getParameter("cost2");
			task.setCost(cost2);
		}

		String tasktype = su.getRequest().getParameter("tasktype");
		if (tasktype.equals("其它")) {
			String tasktypeother = su.getRequest().getParameter("othertype");
			if (tasktypeother.length() != 0) {
				task.setTaskType(tasktypeother);
				taskservice.addTaskType(user, tasktypeother);
			} else
				task.setTaskType(tasktype);
		} else {
			task.setTaskType(tasktype);
		}
		String imgsrc = new String();// imsrc用#分割
		for (int i = 0; i < su.getFiles().getCount(); i++) {
			if (su.getFiles().getFile(i).getSize() == 0) {
				continue;
			}
			String ext = su.getFiles().getFile(i).getFileExt();
			String fileName = id + "_" + i + "." + ext;
			String path = "taskimg/" + java.io.File.separator + fileName;
			imgsrc += fileName + "#";
			try {
				su.getFiles().getFile(i).saveAs(path);
			} catch (SmartUploadException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(imgsrc.equals(""))
			imgsrc="default.jpg";
		task.setImgSrc(imgsrc);
		taskservice.publishTask(user, task);
		if (paytype.equals("online")) {
			request.getSession().setAttribute(task.getId()+"cost", task.getCost());
			response.sendRedirect("choosepay.jsp?taskid="+task.getId());// 在线支付时跳转到支付界面
		} else{
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('发布任务成功~');");
			writer.println("window.location='UserPutTask.jsp'");
			writer.println("</script>");
		}// 线下支付时跳转到成功界面
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	private ServletConfig config;

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		this.config = config;
	}
}
