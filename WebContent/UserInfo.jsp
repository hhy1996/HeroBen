<%@page import="org.apache.catalina.User"%>
<%@page import="com.goli.heroben.factory.DaoFactory"%>
<%@page import="com.goli.heroben.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = true;
	if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	} else {
		user = DaoFactory.getIUserDaoInstance().findUserByTel(user.getTelephone());
		request.getSession().removeAttribute("userbean");
		request.getSession().setAttribute("userbean", user);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/icon.min.css">
<link rel="stylesheet" href="css/semantic.min.css">
<link rel="stylesheet" href="css/icon.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/semantic.min.js"></script>
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

	<div class="ui center aligned grid">
		<div class="four wide column" id="WItop">
			<div class="ui cards">
				<div class="card">
					<form id="imgform" name="imgform" action="ChangeHeadImg"
						method="POST" enctype="multipart/form-data">
						<input type="file" name="img" id="headpic"
							onChange="uploading(this);"
							accept="image/jpeg,image/jpg,image/png" hidden="hidden">
					</form>
					<div id="imgerror"></div>
					<div class="field" data-tooltip="点击修改头像">
						<a href="javascript:selectimg();"><img id="headimg"
							class="ui small circular centered image"
							src="headimg/<%=user.getDpsrc()%>"></a>
					</div>



					<div class="content">
						<div class="header"><%=user.getName()%></div>
						<div class="description">
							上次登录时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=user.getLast_login_time()%>
						</div>
					</div>
					<div class="extra content">
						<%
							if (user.getChecked() == 1) {
						%>
						<a class="item" style="color: GREEN">你已经是真正的英雄！</a>
						<%
							} else if (user.getChecked() == 0 && user.getNuaaid() != null) {
						%>
						<a class="item" style="color: RED">我们将尽快为您验证 </a>
						<%
							} else {
						%>
						<a class="item" style="color: RED" href="check.jsp">点我验证成为英雄！
						</a>
						<%
							}
						%>


					</div>
				</div>

			</div>
		</div>
	</div>

	<div class="ui grid">
		<div class="five wide column"></div>
		<div class="seven wide column" id="WTh">
			<div class="ui tabular menu">
				<a class="active item"> 个人信息 </a> <a class="item"
					href="UserGetTask.jsp"> 接受任务 </a> <a class="item"
					href="UserPutTask.jsp"> 发布任务 </a>
			</div>
		</div>
		<div class="three wide column"></div>
	</div>

	<div class="WIL">
		<div class="ui grid">
			<div class="five wide column"></div>
			<div class="seven wide column">
				<h5 class="ui top attached header">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					姓名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<%=user.getName()%>
				</h5>
				<h5 class="ui attached header">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%=user.getSex()%>
				</h5>
				<h5 class="ui attached header">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					校内编号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%
						if (user.getNuaaid() == null) {
					%>未填写<%
						} else {
					%><%=user.getNuaaid()%>
					<%
						}
					%>
				</h5>
				<h5 class="ui attached header">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					电话号码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%=user.getTelephone()%>
				</h5>
				<h5 class="ui attached header">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					电子邮箱:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%=user.getEmail()%>
				</h5>
				<h5 class="ui attached header">
					完成任务数量:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%=user.getTaskcount()%>
				</h5>
				<h5 class="ui attached header">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					账号余额:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%=user.getLeftmoney()%>
					<a href="javascript:c();">点我取款</a>
				</h5>
				<h5 class="ui attached header">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					注册时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%=user.getRegeiste_time()%>
				</h5>

				<h5 class="ui top attached header">个人信用</h5>
				<div class="ui attached segment" id="Wtext">
					信用分数：<%=user.getAvescore()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					信用等级：
					<div class="ui star rating disabled"
						data-rating="<%=user.getAvescore()%>" data-max-rating="5"
						readonly="readonly"></div>
				</div>
				<br>
				<button class="ui primary button" onclick="a()">点此修改</button>
			</div>
			<div class="three wide column"></div>
		</div>
	</div>


	<div class="ui grid">
		<div class="three wide column"></div>
		<div class="ten wide column">
			<div class="ui grid">
				<div class="three wide column"></div>
				<div class="ten wide column">
					<div class="ui modal" id="modal1">
						<i class="close icon"></i>
						<div class="Wform" style="padding: 20px">
							<form class="ui form" action="ChangeUserInfo" method="post"
								id="formid">
								<h4 class="ui dividing header">修改信息</h4>
								<div class="two fields">
									<div class="field">
										<label>姓名</label>

										<div class="field">
											<input type="text" name="name" placeholder="姓名">
										</div>
									</div>
									<div class="field">
										<label>性别</label> <input type="text" id="txt"
											style="display: none" name="sex"> <select
											class="ui search dropdown" id="test">
											<option value="man">男</option>
											<option value="woman">女</option>
										</select>
									</div>
								</div>
								<div class=" field">
									<div class="field">
										<label>电子邮箱</label>
										<div class="field">
											<input type="text" name="email" placeholder="E-mail">
										</div>
									</div>
								</div>
								<div class="two fields">
									<div class="field">
										<label>密码</label>
										<div class="field">
											<input type="password" name="password1" id="ps1">
										</div>
									</div>
									<div class="field">
										<label>再次确认密码</label>
										<div class="field">
											<input type="password" name="password2" id="ps2">
										</div>
									</div>
								</div>
								<div class="actions">
									<div class="ui button" onclick="checkUser();">确定</div>
									<div class="ui button " onclick="b();">取消</div>
								</div>
							</form>
						</div>
					</div>
					<div class="three wide column"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="ui modal transition hidden"
		style="margin-top: -78.5px; display: block !important;" id="modal2">
		<div class="header">您的当前余额为<%=user.getLeftmoney() %></div>
		<div class="content">
			<form class="ui form" action="WithDraw" method="post" id="withdrawform">
				<div class="field">
					<p>请输入要取款的数目:</p>
					<input type="number" name="money" id="withdrawmoney">
				</div>
				<div class="seven fields">
					<div class="field">
						<label>支付平台:</label>
					</div>
					<div class="field">
						<input type="radio" name="platname" value="alipay" checked>
						<label>支付宝</label>
					</div>
					<div class="field">
						<input type="radio" name="platname" value="wechat">
						<label>微信支付</label>
					</div>
				</div>
				<div class="field">
					<p>请输入要取款的账号:</p>
					<input type="text" name="userplatid" id="userplatid">
				</div>
			</form>
		</div>
		<div class="actions">
			<div class="ui negative button">再想想</div>
			<div class="ui positive right labeled icon button" onclick="withdraw();">
				确认 <i class="checkmark icon"></i>
			</div>
		</div>
	</div>
	<div class="ui center aligned grid">
		<div class="ui five column divided grid"
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
<script src="js/ajaxfileupload.js" type="text/javascript"></script>
<script>
	function checkUser() {
		var password1 = document.getElementById("ps1").value;
		var password2 = document.getElementById("ps2").value;

		if (password1 != password2) {
			alert("2次密码不相同");
			return false;
		}
		document.getElementById("formid").submit();
	}
	window.onload = function() {
		document.getElementById("test").onchange = function() {
			document.getElementById("txt").value = this.value;
		}
	}
	function withdraw(){
		var money=document.getElementById("withdrawmoney").value;
		if(money<=0||money><%=user.getLeftmoney()%>)
		{
			alert("输入的金额有误,请重输");
			return;
		}
		document.getElementById("withdrawform").submit();
	}
	
	function a() {
		$('#modal1').modal('show');
	}
	function b() {
		$('#modal1').modal('hide');
	}
	function c(){
		$('#modal2').modal('show');
	}
	$('.ui.rating').rating({
		maxRating : 5,
		clearable : false,
	});
	$('.ui.rating').rating('disable');
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
		}
		$('.message .close').on('click', function() {
			$(this).closest('.message').transition('fade');
		});
	}
</script>
<%
	if (loginflag) {
%>
<script src="js/message.js"></script>
<script>
$(function() {
	connection("<%=user.getTelephone() %>");
	});
</script>
<%
	}
%>
</html>