<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.goli.heroben.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goli.heroben.vo.TaskBean"%>
<%
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = false;
	if (user != null)
		loginflag = true;
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/semantic.min.css">
<link rel="stylesheet" href="css/icon.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/semantic.min.js"></script>
<script src="js/modernizr-custom-v2.7.1.min.js" type="text/javascript"></script>
<script src="js/jquery-finger-v0.1.0.min.js" type="text/javascript"></script>
<link href="css/flickerplate.css" type="text/css" rel="stylesheet">
<script src="js/flickerplate.min.js" type="text/javascript"></script>

<title>侠客帮-HeroBen</title>
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
				如何召唤Ben
				<div class="sub header">召唤Ben来帮忙吧</div>
			</div>
		</h1>
	</div>
	<h4 class="ui horizontal header divider">
		<i class="block layout icon"></i>
	</h4>
	<div class="ui centered grid">
		<div class="row"></div>
		<div class="eight wide column">


			<div class="ui steps">
				<div class="step">
					<i class="sign in icon"></i>
					<div class="content">
						<div class="title">
							<font><font>登陆</font></font>
						</div>
						<div class="description">
							<font><font>注册并登陆</font></font>
						</div>
					</div>
				</div>
				<div class="step">
					<i class="mail icon"></i>
					<div class="content">
						<div class="title">
							<font><font>发布任务</font></font>
						</div>
						<div class="description">
							<font><font>进行Ben的召唤</font></font>
						</div>
					</div>
				</div>
				<div class="step">
					<i class="reply icon"></i>
					<div class="content">
						<div class="title">
							<font><font>Ben完成任务</font></font>
						</div>
						<div class="description">
							<font><font>进行核查</font></font>
						</div>
					</div>
				</div>
				<div class="step">
					<i class="checkmark icon"></i>
					<div class="content">
						<div class="title">
							<font><font>任务通过</font></font>
						</div>
						<div class="description">
							<font><font>成功获得Ben的帮助</font></font>
						</div>
					</div>
				</div>
			</div>


			<h4 class="ui horizontal divider header">
				<i class="sign in icon"></i><font><font></font></font>
			</h4>

			<div class="ui grid">
				<div class="ten wide column">
					<img class="ui fluid image" src="images/field.jpg">
				</div>
				<div class="six wide column">
					<h3>
						<p>1.未注册用户注册账号</p>
						<p>2.登陆</p>
					</h3>
				</div>
			</div>

			<h4 class="ui horizontal divider header">
				<i class="mail icon"></i><font><font></font></font>
			</h4>

			<div class="ui grid">
				<div class="ten wide column">
					<img class="ui fluid image" src="images/field.jpg">
				</div>
				<div class="six wide column">
					<h3>
						<p>1.填写任务详细内容</p>
						<p>2.发布任务，选择线上支付的任务进行支付</p>
					</h3>
				</div>
			</div>

			<h4 class="ui horizontal divider header">
				<i class="reply icon"></i><font><font></font></font>
			</h4>

			<div class="ui grid">
				<div class="ten wide column">
					<img class="ui fluid image" src="images/field.jpg">
				</div>
				<div class="six wide column">
					<h3>
						<p>1.任务完成后，进行核查</p>
						<p>2.没问题则确认任务通过</p>
						<p>3.之后线下交易的任务自由协定交易方式，线上交易的任务则在一定工作时间内自动将报酬转入Ben的账户</p>
					</h3>
				</div>
			</div>

			<div class="ui divider"></div>

		</div>


	</div>


	</div>





	<div class="ui center aligned grid">
		<div class="row"></div>
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
<script src="js/jquery.form.js"></script>
<script src="js/gt.js"></script>
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