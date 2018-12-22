<%@page import="com.goli.heroben.factory.ServiceFactory"%>
<%@page import="com.goli.heroben.factory.DaoFactory"%>
<%@page import="com.goli.heroben.vo.UserBean"%>
<%@page import="com.goli.heroben.vo.TaskBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = true;
	if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	List<TaskBean> accpet_task = null;
	accpet_task = ServiceFactory.getTaskServiceInstance().searchTask(user, "pub");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/icon.min.css">
<link rel="stylesheet" href="css/semantic.css">
<link rel="stylesheet" href="css/icon.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/semantic.min.js"></script>
<script src="js/less.min.js"></script>
<script src="js/clipboard.min.js"></script>
<script src="js/easing.min.js"></script>
<script src="js/highlight.min.js"></script>
<script src="js/tablesort.min.js"></script>
<title>个人中心</title>
</head>
<body id="Wbody">
	<div class="ui grid">
		<div class="row"></div>
		<div class="one wide column"></div>
		<!--<img src="images/logo.png" style="width: 30%;height:30%;">-->
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
				欢迎来到HeroBen
				<div class="sub header">这里可以看到你的光辉历史</div>
			</div>
		</h1>
	</div>
	<h4 class="ui horizontal header divider">
		<i class="block layout icon"></i>
	</h4>
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
		<div class="five wide column"></div>
		<div class="seven wide column" id="WTh">
			<div class="ui tabular menu">
				<a class="item" href="UserInfo.jsp"> 个人信息 </a> <a class="item"
					href="UserGetTask.jsp"> 接受任务 </a> <a class="active item"> 发布任务
				</a>
			</div>
		</div>
		<div class="three wide column"></div>
	</div>
	<%
		if (accpet_task.size() != 0) {
	%>
	<div class="ui grid">
		<div class="four wide column"></div>
		<div class="nine wide column">

			<table class="ui celled structured table">
				<thead>
					<tr>
						<th rowspan="1">任务标题</th>
						<th rowspan="1">任务类型</th>
						<th rowspan="1">任务内容</th>
						<th rowspan="1">任务状态</th>
						<th rowspan="1">任务报酬</th>
						<th rowspan="1">修改任务</th>
						<th rowspan="1">取消任务</th>
						<th rowspan="1">确认完成</th>
					</tr>
				</thead>
				<tbody>
					<%
						int index;
							String temp = request.getParameter("page");
							if (temp == null)
								index = 0;
							else
								index = Integer.parseInt(temp);
							int size;
							if (accpet_task == null)
								size = 0;
							else
								size = accpet_task.size();
							int pagesize = 5, pagecount = size / pagesize;
							if (size % 5 == 0)
								pagecount--;
							int start = index * pagesize;
							//        		String before = null;
							//        		String next = null;
							int end = start + 5;
							if (end > size)
								end = size;
							//   before=("TaskSer?page="+(index<=0?index:(index-1)));
							//   next=("TaskSer?page="+(index>=pagecount?index:(index+1)));
							for (int i = start; i < end; i++) {
					%>

					<tr>
						<td><%=accpet_task.get(i).getTitle()%></td>
						<td><%=accpet_task.get(i).getTaskType()%></td>
						<td id="WTcontent"><%=accpet_task.get(i).getContent()%></td>
						<%
							String state = accpet_task.get(i).getState();

									switch (state) {
										case "waitingpay" :
						%><td>待支付</td>
						<%
							break;
										case "waitingrev" :
						%><td>待接受</td>
						<%
							break;
										case "waitingfin" :
						%><td>未完成</td>
						<%
							break;
										case "waitingcheck" :
						%><td>待确认</td>
						<%
							break;
										case "success" :
						%><td>已完成(评分:<%=accpet_task.get(i).getScore()%>)
						</td>
						<%
							break;
										case "close" :
						%><td>已取消</td>
						<%
							break;
									}
						%>
						<td><%=accpet_task.get(i).getCost()%></td>
						<%
							if (state.equals("waitingpay") || state.equals("waitingrev")) {
						%>
						<td><button class="ui primary button"
								onclick="window.location='TaskChange.jsp?taskid=<%=accpet_task.get(i).getId()%>'">修改</button></td>
						<%
							} else {
						%>
						<td><button class="ui grey button" disabled="disabled">修改</button></td>
						<%
							}
						%>


						<%
							if (state.equals("waitingpay") || state.equals("waitingrev")) {
						%>
						<td><button class="ui primary button"
								onclick="window.location.href='CloseTask?taskId=<%=accpet_task.get(i).getId()%>&resPath=UserPutTask.jsp'">取消</button></td>
						<%
							} else {
						%>
						<td><button class="ui grey button" disabled="disabled">取消</button></td>
						<%
							}
						%>
						<%
							if (state.equals("waitingcheck")) {
						%>
						<td><button class="ui primary button"
								onclick="showmodal(<%=accpet_task.get(i).getId() %>);">确认完成</button></td>
						<%
							} else {
						%>
						<td><button class="ui grey button" disabled="disabled">确认完成</button></td>
						<%
							}
						%>
					</tr>

					<%
						}
					%>
				</tbody>
			</table>
			<%
				if (size > 5) {
			%>
			<div class="ui borderless pagination menu" id="Wpage">
				<a class="item"
					href="<%=("UserPutTask.jsp?page=" + (index <= 0 ? index : (index - 1)))%>">
					上一页 </a>
				<%
					for (int i = 1; i <= pagecount + 1; i++) {
				%>
				<a class="item" href="UserPutTask.jsp?page=<%=i - 1%>"><%=i%></a>
				<%
					}
				%>
				<a class="item"
					href="<%=("UserPutTask.jsp?page=" + (index >= pagecount ? index : (index + 1)))%>">
					下一页 </a>
			</div>
			<%
				}
			%>
		</div>
		<div class="row"></div>
		<div class="row"></div>
	</div>
	<%
		} else {
	%>
	<div class="ui center aligned grid">
		<div class="row"></div>

		<h2 class="ui icon header">
			<i class="quote left icon"></i>
			<div class="content">
				独孤于江湖,无需他人助
				<div class="sub header">
					您可以点<a href="task.jsp">这里</a>找别人帮帮忙
				</div>
			</div>
		</h2>
		<div class="row"></div>
		<div class="row"></div>
		<div class="row"></div>
	</div>
	<%
		}
	%>
					<div class="ui modal transition hidden" style="margin-top: -78.5px; display: block !important;">
					    <div class="header">
					      确认完成任务？一旦确认将无法撤销
					    </div>
					    <div class="content">
					      <p>请进行评分(双击小星星)</p>
					      <div id="starscore" class="ui star rating" data-rating="0" data-max-rating="5" ondblClick="changeScore();"></div>
					                  得分:<label id="score">0</label>
					    </div>
					    <div class="actions">
					      <div class="ui negative button">
					                    再想想
					      </div>
					      <div class="ui positive right labeled icon button" onclick="check();">
					       	 确认
					        <i class="checkmark icon"></i>
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
	var taskid;
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
	$('.ui.rating')
	  .rating()
	;
	function showmodal(_taskid){
		$('.ui.modal')
			.modal('show')
		;
		taskid=_taskid;
	}
</script>
<script type="text/javascript">
	function check()
	{
		var score=document.getElementById("score").innerText;
		window.location="CheckTask?taskid="+taskid+"&score="+score;
	}
	function changeScore()
	{
		var scr=$('#starscore').rating('get rating');
		//var scr=document.getElementById("starscore").data-rating;
		document.getElementById("score").innerText=scr;
	}
	function selectimg() {
		document.getElementById("headpic").click();
	}
	function uploading(fnUpload) {
		var dom = document.getElementById("headpic");
		var fileSize = dom.files[0].size;
		var filename = fnUpload.value;
		var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
		if (fileSize > 2 * 1024 * 1024) {
			document.getElementById("imgerror").innerHTML = "<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>图片文件不能大于2M</p></div>";
		} else if (mime != ".jpg" && mime != ".png") {
			document.getElementById("imgerror").innerHTML = "<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>图片格式必须为jpg或者png</p></div>";
		} else {
			$
					.ajaxFileUpload({
						url : 'ChangeHeadImg', //用于文件上传的服务器端请求地址
						secureuri : false, //是否需要安全协议，一般设置为false
						fileElementId : 'headpic', //文件上传域的ID
						dataType : 'json', //返回值类型 一般设置为json
						success : function(data, status) //服务器成功响应处理函数
						{
							if (data.src == "102")
								document.getElementById("imgerror").innerHTML = "<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>图片文件不能大于2M</p></div>";
							else if (data.src == "103")
								document.getElementById("imgerror").innerHTML = "<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>图片格式必须为jpg或者png</p></div>";
							else
								$("#headimg").attr("src", data.src);
						},
						error : function(data, status, e)//服务器响应失败处理函数
						{
							document.getElementById("imgerror").innerHTML = "<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>网络错误！</p></div>";
						}
					})
			$('.message .close').on('click', function() {
				$(this).closest('.message').transition('fade');
			});
		}
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