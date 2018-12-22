<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.goli.heroben.factory.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goli.heroben.vo.*"%>
<%@ page import="com.goli.heroben.vo.TaskBean"%>
<%
	List<String> typelist = ServiceFactory.getTaskServiceInstance().getTaskType();
%>
<%
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = true;
	if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
%>
<!DOCTYPE html>
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

<script type="text/javascript">
	$(document).ready(function() {
		$("#othertypediv").hide();

		$("#tasktype").change(function() {
			if (this.value == "其它") {
				$("#othertypediv").show();
			} else {
				$("#othertypediv").hide();
			}
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#cost2").hide();
		$("#cost2").removeAttr("required");

		$("#paytype").change(function() {
			if (this.value == "offline") {
				$("#cost2").show();
				$("#cost2").attr("required", "required");
				$("#cost").hide();
				$("#cost").removeAttr("required");
			} else {
				$("#cost").show();
				$("#cost").attr("required", "required");
				$("#cost2").hide();
				$("#cost2").removeAttr("required");
			}
		});
	});
</script>




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
			<i class="mail icon"></i>
			<div class="content">
				新任务发布
				<div class="sub header">new task</div>
			</div>
		</h1>
	</div>
	<h4 class="ui horizontal header divider">
		<i class="block layout icon"></i>
	</h4>
	<div class="ui centered grid">
		<div class="row"></div>
		<div class="four wide column">
			<form class="ui form" action="TaskPublishServlet" method="POST"
				enctype="multipart/form-data">

				<div class="field">
					<label>任务标题</label> <input type="text" name="title" id="title"
						required="required">
				</div>

				<div class="two fields">
					<div class="field">
						<label>任务类型</label> <select name="tasktype" id="tasktype"
							required="required">
							<%
								for (int i = 0; i < typelist.size(); i++) {
							%><option value=<%=typelist.get(i)%>><%=typelist.get(i)%></option>
							<%
								}
							%>
							<option value="其它" id="other">其他</option>
						</select>
					</div>
					<div class="field" id="othertypediv">
						<label>其他类型</label> <input type="text" name="othertype"
							id="othertype" placeholder="其它">
					</div>
				</div>

				<div class="two fields">
					<div class="field">
						<label>支付方式</label> <select name="paytype" id="paytype"
							required="required">
							<option value="online">线上支付</option>
							<option value="offline">线下支付</option>
						</select>
					</div>

					<div class="field">
						<label>报酬</label> <input type="text" name="cost" id="cost"
							placeholder="请输入金额￥" required="required"
							onkeyup="value=value.replace(/[^\d.]/g,'')" /> <input
							type="text" name="cost2" id="cost2" placeholder="请输入报酬"
							required="required" />

					</div>
				</div>

				<%
					String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
					date = date.substring(0, 10) + "T" + date.substring(11);
				%>
				<div class="field">
					<label>任务截止时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" name="deadtime" required="required"
							min=<%=date%>> <i class="wait icon"></i>
					</div>
				</div>



				<div class="field" id="c-int">
					<label>任务内容</label>
					<textarea cols="20" rows="5" name="content" required="required"></textarea>
				</div>
				<div class="field" id="c-int">
					<label>图片</label>
				</div>
				<div class="three fields">
					<div class="field">
						<button class="ui secondary basic button" id="imgbtn1"
							onclick="document.getElementById('file1').click(); return false;">选择图片</button>
					</div>
					<div class="field">
						<button class="ui secondary basic button" id="imgbtn2"
							onclick="document.getElementById('file2').click(); return false;">选择图片</button>
					</div>
					<div class="field">
						<button class="ui secondary basic button" id="imgbtn3"
							onclick="document.getElementById('file3').click(); return false;">选择图片</button>
					</div>
					<input type="file" id="file1" name="userPhoto"
						accept="image/jpeg,image/jpg,image/png"
						onChange="document.getElementById('imgbtn1').innerText='已选择'"
						style="display: none" /> <input type="file" id="file2"
						name="userPhoto2" accept="image/jpeg,image/jpg,image/png"
						onChange="document.getElementById('imgbtn2').innerText='已选择'"
						style="display: none" /> <input type="file" id="file3"
						name="userPhoto3" accept="image/jpeg,image/jpg,image/png"
						onChange="document.getElementById('imgbtn3').innerText='已选择'"
						style="display: none" />
				</div>
				<div class="three fields">
					<div class="field"></div>
					<div class="field">
						<button class=" medium ui basic blue button"
							onclick="return submitit();">
							<i class="check circle icon"></i> 发布
						</button>
					</div>
					<div class="field"></div>
				</div>

			</form>
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
<script src="js/jquery.form.js"></script>
<!--<script type="text/javascript" src="js/regeist.js"></script>-->
<script src="js/gt.js"></script>
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