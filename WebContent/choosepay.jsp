<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.goli.heroben.vo.*" import="com.taobao.api.internal.util.StringUtils"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goli.heroben.vo.TaskBean"%>
<%
	request.setCharacterEncoding("utf-8");
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = false;
	if (user != null)
		loginflag = true;
	String Price = "0";
	String id = request.getParameter("taskid");
	if(!StringUtils.isEmpty(id))
	{
		try {
			Price = (String)request.getSession().getAttribute(id+"cost");
		} catch (Exception e) {
			response.sendRedirect("HomePage");
			return;
		}
	}else{
		response.sendRedirect("HomePage");
		return;
	}
%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8"></meta>
<link rel="stylesheet" href="css/semantic.min.css">
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

#WPmenu {
	margin-top: 80px;
}

#Pic2 {
	
}

#WPbut {
	width: 160px;
	text-align: center;
	margin-bottom: 220px;
}
</style>

</head>
<body>
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
				<div class="item">个人信息</div>
				<div class="item">发出的任务</div>
				<div class="item">接受的任务</div>
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
				<div class="ui button" onClick="window.location='regeist.jsp'">个人中心</div>
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

	<div class="ui grid">
		<div class="four wide column"></div>
		<div class="six wide column">
			<div class="ui tabular menu" id="WPmenu">
				<a class="active item"> 支付方式 </a>
			</div>
		</div>
	</div>

	<div class="ui grid">
		<div class="four wide column"></div>
		<div class="eight wide column">

			<div class="ui grid">
				<div class="four wide column">
					<div class="item" id="Pic2">
						<img class="ui small image" src="images/wechat.jpg">
					</div>
				</div>
				<div class="four wide column">
					<div class="item" id="Pic1">
						<img class="ui small image" src="images/zhifubao.png">
					</div>
				</div>
			</div>

			<div class="ui grid">
				<div class="four wide column">
					<div class="ui vertical menu" id="WPbut">
						<a class="item" href="WePayServlet?type=微信&taskid=<%=id %>">微信支付</a>
					</div>
				</div>
				<div class="four wide column">
					<div class="ui vertical menu" id="WPbut">
						<a class="item" disabled="true">支付宝支付</a>
					</div>
				</div>
			</div>


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
	$('.special.cards .image').dimmer({
		on : 'hover'
	});
</script>
<script>
	$(document).ready(function() {
		$('.flicker-example').flicker();
	});
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