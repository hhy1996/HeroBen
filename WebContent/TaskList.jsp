<%@page import="com.goli.heroben.vo.TaskBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.goli.heroben.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goli.heroben.vo.TaskBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = false;
	if (user != null)
		loginflag = true;
%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8"></meta>

<link rel="stylesheet" href="css/semantic.css">
<link rel="stylesheet" href="css/icon.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/semantic.min.js"></script>
<script src="js/modernizr-custom-v2.7.1.min.js" type="text/javascript"></script>
<script src="js/jquery-finger-v0.1.0.min.js" type="text/javascript"></script>
<link href="css/flickerplate.css" type="text/css" rel="stylesheet">
<script src="js/flickerplate.min.js" type="text/javascript"></script>
<title>侠客帮-HeroBen</title>
<style>
.flicker-example {
	width: 100%;
	height: 400px;
}

#catogery {
	margin-top: 240px;
	font-size: 20px;
	border-top: 1px solid grey;
	width: 140px;
	height: 345px;
}

#Wtaskcon .header {
	font-size: 25px;
	text-align: center;
}

#Wtaskcon .description {
	line-height: 27px;
	text-indent: 2em;
	margin-top: 25px;
}
</style>
</head>
<body>

	<%
		List<TaskBean> all = null;
		all = (List<TaskBean>) request.getAttribute("info");
		if (all == null) {
			response.sendRedirect("TaskSer");
			return;
		}
		List<String> TaskTypeAll = null;
		TaskTypeAll = (List<String>) session.getAttribute("TaskType");

		int index;
		String temp = request.getParameter("page");
		if (temp == null)
			index = 0;
		else
			index = Integer.parseInt(temp);
		int pagesize = 12, pagecount = all.size() / pagesize;
		if (all.size() % 12 == 0)
			pagecount--;
		int start = index * pagesize;
		String before = null;
		String next = null;
		int end = start + 12;
		if (end > all.size())
			end = all.size();
		before = ("TaskSer?page=" + (index <= 0 ? index : (index - 1)));
		next = ("TaskSer?page=" + (index >= pagecount ? index : (index + 1)));
	%>

	<div class="ui grey top fixed inverted menu">
		<a class="header item" onclick="window.location='HomePage'"> <i
			class="student icon"></i> 侠客帮 - Hero Ben
		</a> <a class="link active item" onclick="window.location='HomePage'">首页</a>
		<div class="ui dropdown item">
			个人中心 <i class="dropdown icon"></i>
			<%
				if (loginflag) {
			%>
			<div class="menu">
				<div class="item" onClick="window.location='UserInfo.jsp'">个人信息</div>
				<div class="item" onClick="window.location='UserPutTask.jsp'">发出的任务</div>
				<div class="item" onClick="window.location='UserGetTask.jsp'">接受的任务</div>
			</div>
			<%
				} else {
			%>
			<div class="menu">
				<div class="item" onClick="window.location='login.jsp'">点此登陆</div>
			</div>
			<%
				}
			%>
		</div>
		<div class="ui dropdown item">
			任务中心 <i class="dropdown icon"></i>
			<div class="menu">
				<div class="item" onclick="window.location='TaskSer?type=代取外卖'">代取外卖</div>
				<div class="item" onclick="window.location='TaskSer?type=代取快递'">代取快递</div>
				<div class="divider"></div>
				<div class="item" onclick="window.location='TaskSer?type=旧书服务'">旧书服务</div>
				<div class="divider"></div>
				<div class="item" onclick="window.location='TaskSer'">任务大厅</div>
			</div>
		</div>
		<div class="right menu">
			<div class="item">
				<%
					if (!loginflag) {
				%>
				<div class="ui primary button"
					onClick="window.location='regeist.jsp'">注册</div>
				<%
					} else {
				%>
				<div class="ui button" onClick="window.location='UserInfo.jsp'">个人中心</div>
				<%
					}
				%>
			</div>
			<div class="item">
				<%
					if (!loginflag) {
				%>
				<div class="ui button" onClick="window.location='login.jsp'">登陆</div>
				<%
					} else {
				%>
				<div class="ui primary button" onClick="window.location='Logout'">注销</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
	<div class="ui center aligned grid"
		style="background: rgba(190, 160, 122, 100) repeat;">
		<div class="row"></div>
		<div class="row">
			<div class="four wide column">
				<img class="ui fluid image" src="images/logo.png">
			</div>
			<div class="six wide middle aligned column">
				<form action="TaskSer" method="GET" id="kind">
					<div class="ui fluid action input">
						<input type="text" id="searchbox" placeholder="Search..."
							name="searchValue">
						<div class="ui grey button" onclick="test();">To Be Ben</div>
					</div>
				</form>
			</div>
			<div class="three wide left aligned middle aligned column">
				<button class="ui brown button" onclick="window.location='task.jsp'">
					<i class="edit icon"></i>发布需求
				</button>
			</div>
		</div>
	</div>
	<div class="ui center aligned grid">
		<div class="fourteen wide column">
			<div class="B2">
				<%
					if (all.size() == 0) {
				%>
				<div class="ui center aligned grid">
					<div class="row"></div>
					<div class="row"></div>
					<h1 class="ui icon header">
						<i class="quote left icon"></i>
						<div class="content">
							有时候搜索不到是一种宁缺毋滥追求
							<div class="sub header">
								您可以点<a href="TaskSer">这里</a>看看适合您的内容
							</div>
						</div>
					</h1>
					<div class="row"></div>
					<div class="row"></div>
				</div>
				<!--<div class="ui grid">
					<div class="four wide column"></div>
					<img class="ui image" src="images/hint.jpg" float:right>
				</div>-->
				<%
					}
				%>
				<div class="ui cards">
					<%
						for (int i = start; i < end; i++) {
					%>
					<div class="card">
						<div class="content" id="Wtaskcon">
							<div class="header"><%=all.get(i).getTitle()%></div>
							<div class="description">
								<%=all.get(i).getContent()%>
							</div>
						</div>
						<div class="ui bottom attached button"
							onclick="window.location='TaskDetail?taskid=<%=all.get(i).getId()%>'">
							<i class="add icon"></i> 查看任务
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
	<div class="ui right fixed vertical menu" id="catogery">
		<!--<div class="item">
			<img class="ui big image" src="images/logo.png">
		</div>-->
		<a class="item" href="TaskSer?type=浏览所有">浏览所有</a>
		<%
			int count = TaskTypeAll.size();
			if (TaskTypeAll.size() > 5)
				count = 5;
		%>
		<%
			for (int i = 0; i < count; i++) {
		%>
		<a class="item" href="TaskSer?type=<%=TaskTypeAll.get(i)%>"><%=TaskTypeAll.get(i)%></a>
		<%
			}
		%>
	</div>
	<div class="ui center aligned grid">
		<div class="eight wide column">

			<%
				String type = request.getParameter("type");
				String searchValue = request.getParameter("searchValue");
				if (type == null)
					type = "浏览所有";
			%>
			<%
				if (all.size() > 12) {
			%>
			<div class="ui borderless pagination menu" id="Wpage">
				<a class="item" href="<%=before + "&type=" + type%>"> 上一页 </a>
				<%
					for (int i = 1; i <= pagecount + 1; i++) {
				%>
				<a class="item" href="TaskSer?page=<%=i - 1%>"><%=i%></a>
				<%
					}
				%>
				<a class="item"
					href="<%=next + "&type=" + type + "&searchValue=" + searchValue%>">
					下一页 </a>
			</div>
			<%
				}
			%>






		</div>
	</div>
	<div class="ui center aligned grid">
		<div class="ui five column centerd divided grid"
			style="background-color: #3C3C3C; color: #FCFCFC">
			<div class="row">
				<div class="column"></div>
				<div class="column">
					<h3>新手指南</h3>
				</div>
				<div class="column">
					<h3>个人中心</h3>
				</div>
				<div class="column">
					<h3>关于我们</h3>
				</div>
				<div class="column"></div>
			</div>
			<div class="row">
				<div class="column"></div>
				<div class="column">
					<div class="ui link list">
						<a class="item" style="color: #FCFCFC" href="BeginnerBen.jsp">如何召唤Ben</a>
						<a class="item" style="color: #FCFCFC" href="BeginnerGain.jsp">如何获得薪酬</a>
						<a class="item" style="color: #FCFCFC" href="BeginnerRev.jsp">如何接受任务</a>
						<a class="item" style="color: #FCFCFC" href="BeginnerRuler.jsp">规则中心</a>
					</div>
				</div>
				<div class="column">
					<div class="ui link list">
						<a class="item" style="color: #FCFCFC" href="UserGetTask.jsp">我收到的任务</a>
						<a class="item" style="color: #FCFCFC" href="UserPutTask.jsp">我发出的任务</a>
						<a class="item" style="color: #FCFCFC" href="UserInfo.jsp">个人中心</a>
						<a class="item" style="color: #FCFCFC" href="TaskSer">任务中心</a>
					</div>
				</div>
				<div class="column">
					<div class="ui link list">
						<a class="item" style="color: #FCFCFC" href="AboutUsTeam.jsp">制作团队</a>
						<a class="item" style="color: #FCFCFC" href="AboutUsSate.jsp">安全声明</a>
					</div>
				</div>
				<div class="column"></div>
			</div>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('.ui.menu .ui.dropdown').dropdown({
			on : 'hover'
		});
		$('.ui.menu a.item').on('click', function() {
			$(this).addClass('active').siblings().removeClass('active');
		});
	});

	function test() {
		if (document.getElementById("searchbox").value != "")
			document.getElementById("kind").submit();
	}
</script>
<%
	if (loginflag) {
%>
<script src="js/message.js"></script>
<script>
$(function() {
	connection("<%=user.getTelephone()%>");
});
</script>
<%
	}
%>
</html>