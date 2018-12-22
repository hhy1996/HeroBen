<%@page import="com.goli.heroben.factory.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.goli.heroben.vo.UserBean"%>
<%
	UserBean user = (UserBean) session.getAttribute("userbean");
	boolean loginflag = false;
	if (user != null) {
		loginflag = true;
	} else {
		response.sendRedirect("login.jsp");
		return;
	}
%>
<!DOCTYPE html >
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
<title>验明正身</title>

</head>
<body>
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
	<div class="ui grid">
		<div class="row"></div>
		<div class="one wide column"></div>
		<!--<img src="images/logo.png" style="width: 30%;height:30%;">-->
	</div>
	<div class="ui center aligned grid">
		<h1 class="ui icon header">
			<i class="user icon"></i>
			<div class="content">
				验明正身
				<div class="sub header">成为真正的英雄Ben</div>
			</div>
		</h1>
	</div>
	<h4 class="ui horizontal header divider">
		<i class="block layout icon"></i>
	</h4>
	<div class="ui centered grid">
		<div class="computer only row">
			<div class="column"></div>
		</div>

		<div class="six wide tablet eight wide computer column">
			<div class="ui two column centered grid">
				<div class="column">
					<div class="ui special cards">
						<div class="card">
							<div class="blurring dimmable image">
								<div class="ui dimmer">
									<div class="content">
										<div class="center">
											<div class="ui inverted button" onclick="selectimg()">放上你的证件照片</div>
										</div>
									</div>
								</div>

								<img src="images/dp/default.jpg">
							</div>
							<div id="imgerror"></div>
							<div class="content">
								<h2><%=user.getName()%></h2>
							</div>
							<div class="extra content">

								<form id="imgform" name="imgform" action="CheckIdentity"
									method="POST" enctype="multipart/form-data">
									<input type="file" name="img" id="img"
										accept="image/jpeg,image/jpg,image/png" hidden="hidden">
									<div class="ui fluid input">
										<input type="text" id="xxid" name="zzid"
											style="border-style: none" placeholder="请输入校内编号">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="coulmn"></div>
				</div>
				<div align="center">
					<button class="ui basic button" onclick="fsubmit()">点我进行验证</button>
				</div>
			</div>


		</div>
	</div>
	<div class="ui stackable four column grid">
		<div class="column"></div>
		<div class="column"></div>
		<div class="column"></div>
		<div class="column"></div>
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
<script type="text/javascript">
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
	function fsubmit() {
		var file = document.getElementById("img").value;
		var txtfile = document.getElementById("xxid").value;

		if (!txtfile && file) {
			alert("校内编号没输入哦~~");
			return false;
		}

		if (!file && txtfile) {
			alert("图片还没有上传哦~~");
			return false;
		}

		if (!file && !txtfile) {
			alert("什么都没填就提交，mdzz。");
			return false;
		}

		document.getElementById("imgform").submit();

	}
</script>
<script>
	$(document).ready(function() {
		$('.flicker-example').flicker();
	});
	function selectimg() {
		document.getElementById("img").click();
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