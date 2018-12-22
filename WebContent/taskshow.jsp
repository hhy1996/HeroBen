<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.goli.heroben.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goli.heroben.vo.TaskBean"%>
<%
	request.setCharacterEncoding("utf-8");
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = false;
	if (user != null)
		loginflag = true;
	TaskBean task = (TaskBean)request.getAttribute("taskinfo");
	UserBean pubUser = (UserBean)request.getAttribute("pubinfo");
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
			<i class="tasks icon"></i>
			<div class="content">
				查看任务详情
				<div class="sub header">接受它,成为Ben</div>
			</div>
		</h1>
	</div>
	<h4 class="ui horizontal header divider">
		<i class="block layout icon"></i>
	</h4>
	<%
		if (task != null) {
			String paytype;
			if(task.getPayType().equals("online"))
				paytype="在线支付";
			else
				paytype="线下支付";
			String pubTime=task.getPubTime().toString();
			pubTime=pubTime.substring(0,pubTime.length()-2);
			String deadTime=task.getDeadTime().toString();
			deadTime=deadTime.substring(0,deadTime.length()-2);
			String[] imgList=task.getImgSrc().split("#");
	%>
	<div class="ui center aligned grid">
		<div class="row"></div>
		<div class="six wide column">
			<form class="ui form">
				<div class="field">
					<div class="ui fluid input">
						<label> 任务标题&nbsp;&nbsp;&nbsp;&nbsp; </label> <input type="text"
							value="<%=task.getTitle() %>" readonly="readonly">
					</div>
				</div>
				<div class=" two fields">
					<div class="field">
						<div class="ui fluid input">
							<label> 支付方式&nbsp;&nbsp;&nbsp;&nbsp; </label> <input type="text"
								value="<%=paytype %>" readonly="readonly">
						</div>
					</div>
					<div class="field">
						<div class="ui fluid input">
							<label> 任务酬金&nbsp;&nbsp;&nbsp;&nbsp; </label> <input type="text"
								value="<%=task.getCost() %>" readonly="readonly">
						</div>
					</div>
				</div>
				<div class="two fields">
					<div class="field" data-tooltip="点击与他交流" >
						<div class="ui fluid input">
							<label>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布者&nbsp;&nbsp;&nbsp;&nbsp; </label> 
								<input type="text" value="<%=pubUser.getName()%><%if(pubUser.getChecked()==1){%>(账户已验证)<%}else{%>(账户未验证)"<%}%>"  readonly="readonly" 
									onClick="window.open ('Chat.jsp?chatwith=<%=task.getPubid() %>','newwindow','height=500,width=600,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')">
						</div>
					</div>
					<div class="field" data-tooltip="点击与他交流">
						<div class="ui fluid input">
							<label> 联系方式&nbsp;&nbsp;&nbsp;&nbsp; </label> <input type="text"
								value="<%=pubUser.getTelephone() %>" readonly="readonly" onClick="window.open ('Chat.jsp?chatwith=<%=task.getPubid() %>','newwindow','height=500,width=600,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')">
						</div>
					</div>
				</div>
				<div class="two fields">
					<div class="field">
						<div class="ui fluid input">
							<label> 发布时间&nbsp;&nbsp;&nbsp;&nbsp; </label> <input type="text"
								value="<%=pubTime %>" readonly="readonly">
						</div>
					</div>
					<div class="field">
						<div class="ui fluid input">
							<label> 截止时间&nbsp;&nbsp;&nbsp;&nbsp; </label> <input type="text"
								value="<%=deadTime %>" readonly="readonly">
						</div>
					</div>
				</div>
				<div class="field">
					<div class="ui fluid input">
						<label> 任务类型&nbsp;&nbsp;&nbsp;&nbsp; </label> <input type="text"
							value="<%=task.getTaskType() %>" readonly="readonly">
					</div>
				</div>
				<div class="field">
					<div class="ui fluid input">
						<label>任务内容&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<textarea width="80%" rows="5" readonly="readonly"
							style="width: 89%; resize: none"><%=task.getContent() %></textarea>
					</div>
				</div>
				<%
					if(task.getState().equals("waitingrev")){
						if(loginflag){
							if(!user.getTelephone().equals(task.getPubid())){
				%>
					<div calss="field">
						<button class="ui basic button"
							onclick="window.location='TaskDetail?taskid=<%=task.getId() %>&action=receive';return false;">
							<i class="checkmark icon"></i> 接受任务
						</button>
					</div>
				<%}}else{%>
					<div calss="field">
						<button class="ui basic button"
							onclick="window.location='TaskDetail?taskid=<%=task.getId() %>&action=receive';return false;">
							<i class="checkmark icon"></i> 接受任务
						</button>
					</div>
				<%
					}
				}%>
			</form>
		</div>
		<div class="one wide center aligned column">
			<div class="ui people shape" id="img">
				<div class="sides">
					<%for(int i=0;i<imgList.length;i++){ %>
					<%if(i==0){%>
					<div class="active side">
						<%}else{%>
						<div class="side">
							<%} %>
							<div class="ui card">
								<div class="ui fluid image">
									<img src="taskimg/<%=imgList[i]%>">
								</div>
							</div>
						</div>
						<%} %>
					</div>
				</div>
				<div class="ui grid">
					<div class="row"></div>
					<div class="ui buttons">
						<button class="ui button" onclick="turnleft();">上一张</button>
						<button class="ui positive button" onclick="turnright();">下一张</button>
					</div>
				</div>
			</div>
		</div>
		<%
		} else {
	%>
		<div class="ui center aligned grid">
			<div class="row"></div>
			<div class="row"></div>
			<h1 class="ui icon header">
				<i class="quote left icon"></i>
				<div class="content">
					抱歉,我们找不到这个任务了
					<div class="sub header">
						您可以点<a href="TaskSer">这里</a>看看适合您的内容
					</div>
				</div>
			</h1>
			<div class="row"></div>
			<div class="row"></div>
		</div>

		<%
		}
	%>
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
<script type="text/javascript">
	function turnleft() {
		$('.shape').shape();

		$('.shape').shape('flip left');

	}
	function turnright() {
		$('.shape').shape();
		$('.shape').shape('flip right');

	}
</script>
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
<script type="text/javascript" src="js/regeist.js"></script>
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