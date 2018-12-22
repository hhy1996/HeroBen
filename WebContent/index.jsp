<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.goli.heroben.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goli.heroben.vo.TaskBean"%>
<%
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = false;
	if (user != null)
		loginflag = true;
	List<String> typelist = (List<String>) request.getAttribute("typelist");
	List<String> namelist = (List<String>) request.getAttribute("highBen");
	List<TaskBean> recommend = (List<TaskBean>) request.getAttribute("recommend");
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
				<div class="ui fluid action input">
					<input type="text" id="searchbox" placeholder="Search...">
					<div class="ui grey button" onclick="search();">To Be Ben</div>
				</div>
			</div>
			<div class="three wide left aligned middle aligned column">
				<button class="ui brown button" onclick="window.location='task.jsp'">
					<i class="edit icon"></i>发布需求
				</button>
			</div>
		</div>
	</div>
	<div class="ui center aligned grid"
		style="background: rgba(190, 160, 122, 100) repeat;">
		<div class="row middle aligned">
			<div class="one wide column"></div>
			<div class="ten wide column">
				<div class="flicker-example" data-block-text="false">
					<ul>
						<li data-background="images/field.jpg">
							<div class="flick-title">旧书活动</div>
							<div class="flick-sub-text">将你的旧书拿出来共享,节省资源从你我做起.</div>
						</li>
						<li data-background="images/forest.jpg">
							<div class="flick-title" >在高高的城市森林中</div>
							<div class="flick-sub-text" style="color:#000000;">我们每个人一个简单的动作,都能成为一个英雄</div>
						</li>
						<li data-background="images/frozen.jpg">
							<div class="flick-title">不论白天还是晚上</div>
							<div class="flick-sub-text">英雄Ben始终为你服务</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="three wide column">
				<div class="ui one special cards">
					<div class="card">
						<div class="blurring dimmable image">
							<div class="ui dimmer">
								<div class="content">
									<div class="center">
										<%
											if (loginflag) {
										%>
										<div class="ui inverted button"
											onClick="window.location='UserInfo.jsp'">个人中心</div>
										<%
											} else {
										%>
										<div class="ui inverted button" onclick="window.location='login.jsp'">登陆</div>
										<div class="ui primary button" onclick="window.location='regeist.jsp'">注册</div>
										<%
											}
										%>
									</div>
								</div>
							</div>
							<%if(loginflag){ %>
							<img src="headimg/<%=user.getDpsrc() %>">
							<%}else{ %>
							<img src="images/dp/dp1.png">
							<%} %>
						</div>
						<div class="content">
							<%
								if (loginflag) {
							%>
							<a class="header" href='UserInfo.jsp'><%=user.getName()%></a>
							<%
								} else {
							%>
							英雄Ben已离线
							<%
								}
							%>

							<%
								if (loginflag) {
							%>
							<div class="meta">
								已完成任务数:<%=user.getTaskcount()%>
							</div>
							<%
								}
							%>
						</div>
						<%
							if (loginflag) {
						%>
						<div class="extra content">
							<i class="wait icon"></i> 上次登陆时间:<%=user.getLast_login_time()%>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
		<div class="row"></div>
	</div>
	<div class="ui divider"></div>
	<div class="ui center aligned grid">
		<div class="row"></div>
		<div class="row middle aligned">
			<div class="four wide column">
				<div class="ui segment">
					<h2>热门搜索</h2>
					<p>
						1.帮忙代取外卖&nbsp;&nbsp;2.求下楼拿外卖&nbsp;&nbsp;3.高数I旧书... <br />
						4.菜鸟驿站有顺路嘛&nbsp;&nbsp;5.外卖求代取&nbsp;&nbsp;6.C语言求辅导
					</p>
				</div>
			</div>
			<div class="four wide column">
				<div class="ui segment">
					<h2>热门类型</h2>
					<p>
						<%
							if (typelist != null) {
								int count = typelist.size() < 6 ? typelist.size() : 6;
								for (int i = 0; i < count; i++) {
						%>
						<%=(i + 1) + "." + typelist.get(i) + "&nbsp;&nbsp;"%>
						<%
							if (i == 2) {
						%>
						<br />
						<%
							}
								}
							}
						%>
					</p>
				</div>
			</div>
			<div class="four wide column">
				<div class="ui segment">
					<h2>高分Ben</h2>
					<p>
						<%
							if (namelist != null) {
								int count = namelist.size() < 6 ? namelist.size() : 6;
								for (int i = 0; i < count; i++) {
						%>
						<%=(i + 1) + "." + namelist.get(i) + "&nbsp;&nbsp;"%>
						<%
							if (i == 2) {
						%>
						<br />
						<%
							}
								}
							}
						%>
					</p>
				</div>
			</div>
		</div>
		<div class="row"></div>
	</div>
	<div class="ui divider"></div>
	<div class="ui center aligned grid">
		<div class="row"></div>
		<div class="row">
			<div class="left floated right aligned four wide column">
				<h1>
					<i class="zoom icon"></i>&nbsp;推荐任务
				</h1>
			</div>
		</div>
	</div>
	<div class="ui center aligned grid">
		<div class="row">
			<div class="two wide column"></div>
			<div class="fourteen wide column">
				<div class="ui cards">
					<%
						if (recommend != null) {
							for (int i = 0; i < recommend.size(); i++) {
					%>
					<div class="card">
						<div class="content">
							<div class="header"><%=recommend.get(i).getTitle()%></div>
							<div class="description">
								报酬:<%=recommend.get(i).getCost()%></div>
						</div>
						<div class="ui bottom attached button"
							onclick="window.location='TaskDetail?taskid=<%=recommend.get(i).getId()%>'">
							<i class="add icon"></i>查看任务详情
						</div>
					</div>
					<%
						}
						}
					%>
				</div>
			</div>
		</div>
		<div class="row"></div>
	</div>
	<h4 class="ui horizontal divider">
		<i class="settings icon"></i> 任务流程
	</h4>
	<div class="ui center aligned grid">
		<div class="row"></div>
		<div class="nine wide column">
			<div class="ui four steps">
				<div class="step">
					<i class="newspaper icon"></i>
					<div class="content">
						<div class="title">发布任务</div>
					</div>
				</div>
				<div class="step">
					<i class="bullseye icon"></i>
					<div class="content">
						<div class="title">接受任务</div>
					</div>
				</div>
				<div class="step">
					<i class="checkmark icon"></i>
					<div class="content">
						<div class="title">任务完成</div>
					</div>
				</div>
				<div class="step">
					<i class="dollar icon"></i>
					<div class="content">
						<div class="title">支付酬金</div>
					</div>
				</div>
			</div>
		</div>
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
<script>
	$(document).ready(function() {
		$('.flicker-example').flicker();
	});
	function search() {
		var searchvalue = document.getElementById("searchbox").value;
		if (searchvalue != "")
			window.location = "TaskSer?searchValue=" + searchvalue;
		else
			window.location = "TaskSer";
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