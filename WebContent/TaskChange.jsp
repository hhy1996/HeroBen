<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.goli.heroben.factory.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goli.heroben.vo.*"%>
<%@ page import="java.text.*"%>

<%
	UserBean user = (UserBean) request.getSession().getAttribute("userbean");
	boolean loginflag = false;
	if (user != null)
		loginflag = true;
	else
		response.sendRedirect("login.jsp");
	List<String> typelist = ServiceFactory.getTaskServiceInstance().getTaskType();
%>

<%
	String taskid = request.getParameter("taskid");//获取当前任务的id
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


<script type="text/javascript">
$(document).ready(function(){
  $("#othertypediv").hide();
  
  $("#tasktype").change(function(){
	  if(this.value=="其它"){
	         $("#othertypediv").show();
	         $("#othertype").attr("required","required");
	  }
	  else{
	         $("#othertypediv").hide();
	         $("#othertype").removeAttr("required");
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
			<i class="open dnvelope icon"></i>
			<div class="content">
				任务信息
				<div class="sub header">task message</div>
			</div>
		</h1>
	</div>
	<h4 class="ui horizontal header divider">
		<i class="block layout icon"></i>
	</h4>
	<%
		if (taskid != null && !taskid.equals("")) {
	%>
	<div class="ui centered grid">
		<div class="row"></div>
		<div class="four wide column">
			<form class="ui form" name="form1" action="" method="POST">
				<%
					TaskBean task = DaoFactory.getITaskDaoInstance().findTaskById(taskid);
					request.getSession().setAttribute("servicecost", task.getCost());
					request.getSession().setAttribute("serviceid", task.getId());
				%>
				<%
					String state = task.getState();
				%>
				<%
					switch (state) {
							case "waitingpay" :
							case "waitingrev" :
				%>

				<div class="field">
					<label>任务标题</label> <input type="text" name="title" id="title"
						required="required" value=<%=task.getTitle()%>>
				</div>

				<div class="two fields">
					<div class="field">
						<label>任务类型</label> <select name="tasktype" id="tasktype"
							required="required">
							<%
								for (int i = 0; i < typelist.size(); i++) {
												String ttype = typelist.get(i);
												if (ttype.equals(task.getTaskType())) {
							%><option value=<%=ttype%> selected="selected"><%=ttype%></option>
							<%
								} else {
							%>
							<option value=<%=ttype%>><%=ttype%></option>
							<%
								}
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
							disabled="disabled">
							<%
								if (task.getPayType().equals("online")) {
							%>
							<option value="online" selected="selected">线上支付</option>
							<option value="offline">线下支付</option>
							<%
								} else {
							%>
							<option value="online">线上支付</option>
							<option value="offline" selected="selected">线下支付</option>
							<%
								}
							%>
						</select>
					</div>

					<div class="field">
						<label>报酬</label> <input type="text" name="cost" id="cost"
							disabled="disabled" value=<%=task.getCost()%>>
					</div>
				</div>


				<%
					String pdate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getPubTime());
								pdate = pdate.substring(0, 10) + "T" + pdate.substring(11);

								String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getDeadTime());
								date = date.substring(0, 10) + "T" + date.substring(11);
				%>

				<div class="field">
					<label>任务发布时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="pubtime" name="pubtime"
							disabled="disabled" value=<%=pdate%>> <i
							class="wait icon"></i>
					</div>
				</div>

				<div class="field">
					<label>任务截止时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="deadtime" name="deadtime"
							required="required" value=<%=date%> min=<%=pdate%>> <i
							class="wait icon"></i>
					</div>
				</div>



				<div class="field" id="c-int">
					<label>任务内容</label>
					<textarea cols="20" rows="5" name="content" required="required"
						value=<%=task.getContent()%>><%=task.getContent()%></textarea>
				</div>





				<div class="fields">
					<div class="field">
						<button class="  ui basic blue button" onclick="return modal()">
							删除</button>
					</div>

					<div class="field"></div>

					<%
						if (state.equals("waitingpay")) {
							request.getSession().setAttribute(task.getId()+"cost", task.getCost());
					%>
					<div class="field">
						<button id="pay" class=" ui basic blue button"
							onclick="payAction()">支付</button>
					</div>
					<%
						}
					%>

					<div class="field"></div>
					<div class="field">
						<button class=" ui basic blue button" onclick="changeAction()">
							修改</button>
					</div>
				</div>


				<div class="ui basic modal">
					<div class="ui icon header">
						<i class="archive icon"></i><font><font> 关闭该任务 </font></font>
					</div>

					<div class="content">
						<p>
							<font><font>您是否希望关闭该任务？</font></font>
						</p>
					</div>

					<div class="actions">
						<div class="ui red basic cancel inverted button">
							<i class="remove icon"></i> No
						</div>
						<div class="ui green ok inverted button" onclick="deleteAction()">
							<!-- "window.location='?yemian=kk&taskid=" -->
							<i class="checkmark icon"></i> Yes
						</div>
					</div>
				</div>

				<%
					break;

							case "waitingfin" :
							case "waitingcheck" :
				%>

				<div class="field">
					<label>任务标题</label> <input type="text" name="title2" id="title2"
						value=<%=task.getTitle()%> disabled="disabled">
				</div>

				<div class="two fields">
					<div class="field">
						<label>任务类型</label> <select name="tasktype2" id="tasktype2"
							disabled="disabled">
							<%
								for (int i = 0; i < typelist.size(); i++) {
												String ttype = typelist.get(i);
												if (ttype.equals(task.getTaskType())) {
							%><option value=<%=ttype%> selected="selected"><%=ttype%></option>
							<%
								} else {
							%>
							<option value=<%=ttype%>><%=ttype%></option>
							<%
								}
											}
							%>
							<option value="其它" id="other2">其他</option>
						</select>
					</div>

					<%
						if (state.equals("waitingfin")) {
					%>
					<div class="field">
						<label>发布者</label> <input type="text" name="pubid" id="pubid"
							value=<%=task.getPubid()%> disabled="disabled">
					</div>
					<%
						} else {
					%><div class="field">
						<label>接受者</label> <input type="text" name="revid" id="revid"
							value=<%=task.getRevid()%> disabled="disabled">
					</div>
					<%
						}
					%>

				</div>


				<div class="two fields">
					<div class="field">
						<label>支付方式</label> <select name="paytype2" id="paytype2"
							disabled="disabled">
							<%
								if (task.getPayType().equals("online")) {
							%>
							<option value="online" selected="selected">线上支付</option>
							<option value="offline">线下支付</option>
							<%
								} else {
							%>
							<option value="online">线上支付</option>
							<option value="offline" selected="selected">线下支付</option>
							<%
								}
							%>
						</select>
					</div>

					<div class="field">
						<label>报酬</label> <input type="text" name="cost2" id="cost2"
							disabled="disabled" value=<%=task.getCost()%>>
					</div>
				</div>



				<%
					String pdate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getPubTime());
								pdate2 = pdate2.substring(0, 10) + "T" + pdate2.substring(11);

								String date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getDeadTime());
								date2 = date2.substring(0, 10) + "T" + date2.substring(11);

								String rdate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getRevTime());
								rdate2 = rdate2.substring(0, 10) + "T" + rdate2.substring(11);
				%>


				<div class="field">
					<label>任务发布时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="pubtime" name="pubtime"
							disabled="disabled" value=<%=pdate2%>> <i
							class="wait icon"></i>
					</div>
				</div>

				<div class="field">
					<label>任务截止时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="deadtime" name="deadtime"
							value=<%=date2%> disabled="disabled"> <i
							class="wait icon"></i>
					</div>
				</div>

				<div class="field">
					<label>任务接受时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="revtime" name="revtime"
							value=<%=rdate2%> disabled="disabled"> <i
							class="wait icon"></i>
					</div>
				</div>

				<%
					if (state.equals("waitingcheck")) {
									String fdate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getFinTime());
									fdate2 = fdate2.substring(0, 10) + "T" + fdate2.substring(11);
				%>

				<div class="field">
					<label>任务完成时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="fintime" name="fintime"
							value=<%=fdate2%> disabled="disabled"> <i
							class="wait icon"></i>
					</div>
				</div>
				<%
					}
				%>

				<div class="field" id="c-int">
					<label>任务内容</label>
					<textarea cols="20" rows="5" name="content2" disabled="disabled"
						value=<%=task.getContent()%>><%=task.getContent()%></textarea>
				</div>


				<div class="field"></div>
				<%
					if (state.equals("waitingfin")) {
				%>
				<div class="field">
					<button id="fin" class=" ui basic blue button"
						onclick="changeAction()">确认完成</button>
				</div>
				<%
					} else {
				%><div class="field">
					<button id="check" class=" ui basic blue button"
						onclick="changeAction()">确认对方完成</button>
				</div>
				<%
					}
				%>


				<%
					break;
							case "close" :
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
					break;
							case "success" :
				%>
				<div class="ui center aligned grid">
					<div class="row"></div>
					<div class="row"></div>
					<div class="content">
						<h1>这个任务已完成</h1>
					</div>
					<div class="row"></div>
					<div class="row"></div>
				</div>

				<div class="field">
					<label>任务标题</label> <input type="text" name="title3" id="title3"
						value=<%=task.getTitle()%> disabled="disabled">
				</div>


				<div class="field">
					<label>任务类型</label> <select name="tasktype3" id="tasktype3"
						disabled="disabled">
						<%
							for (int i = 0; i < typelist.size(); i++) {
											String ttype = typelist.get(i);
											if (ttype.equals(task.getTaskType())) {
						%><option value=<%=ttype%> selected="selected"><%=ttype%></option>
						<%
							} else {
						%>
						<option value=<%=ttype%>><%=ttype%></option>
						<%
							}
										}
						%>
						<option value="其它" id="other3">其他</option>
					</select>
				</div>

				<div class="field">
					<label>发布者</label> <input type="text" name="pubid" id="pubid"
						value=<%=task.getPubid()%> disabled="disabled">
				</div>

				<div class="field">
					<label>接受者</label> <input type="text" name="revid" id="revid"
						value=<%=task.getRevid()%> disabled="disabled">
				</div>



				<div class="two fields">
					<div class="field">
						<label>支付方式</label> <select name="paytype3" id="paytype3"
							disabled="disabled">
							<%
								if (task.getPayType().equals("online")) {
							%>
							<option value="online" selected="selected">线上支付</option>
							<option value="offline">线下支付</option>
							<%
								} else {
							%>
							<option value="online">线上支付</option>
							<option value="offline" selected="selected">线下支付</option>
							<%
								}
							%>
						</select>
					</div>

					<div class="field">
						<label>报酬</label> <input type="text" name="cost3" id="cost3"
							disabled="disabled" value=<%=task.getCost()%>>
					</div>
				</div>



				<%
					String pdate3 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getPubTime());
								pdate3 = pdate3.substring(0, 10) + "T" + pdate3.substring(11);

								String date3 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getDeadTime());
								date3 = date3.substring(0, 10) + "T" + date3.substring(11);

								String rdate3 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getRevTime());
								rdate3 = rdate3.substring(0, 10) + "T" + rdate3.substring(11);

								String fdate3 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(task.getFinTime());
								fdate3 = fdate3.substring(0, 10) + "T" + fdate3.substring(11);
				%>


				<div class="field">
					<label>任务发布时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="pubtime" name="pubtime"
							disabled="disabled" value=<%=pdate3%>> <i
							class="wait icon"></i>
					</div>
				</div>

				<div class="field">
					<label>任务截止时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="deadtime" name="deadtime"
							value=<%=date3%> disabled="disabled"> <i
							class="wait icon"></i>
					</div>
				</div>

				<div class="field">
					<label>任务接受时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="revtime" name="revtime"
							value=<%=rdate3%> disabled="disabled"> <i
							class="wait icon"></i>
					</div>
				</div>


				<div class="field">
					<label>任务完成时间</label>
					<div class="ui left icon input">
						<input type="datetime-local" id="fintime" name="fintime"
							value=<%=fdate3%> disabled="disabled"> <i
							class="wait icon"></i>
					</div>
				</div>


				<div class="field" id="c-int">
					<label>任务内容</label>
					<textarea cols="20" rows="5" name="content2" disabled="disabled"
						value=<%=task.getContent()%>><%=task.getContent()%></textarea>
				</div>

				<div class="sub header">
					<h4>
						您可以点<a href="TaskSer">这里</a>看看其他喜欢的内容
					</h4>
				</div>

				<%
					}
				%>

			</form>

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
<script type="text/javascript">
	function modal() {
		$('.ui.basic.modal').modal('show');
		return false;
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
<script type="text/javascript">
function changeAction(){
    document.form1.action="ChangeTaskInfoServlet?taskId=<%=taskid%>";
    document.form1.submit();
}
function deleteAction(){
    document.form1.action="CloseTask?taskId=<%=taskid%>&resPath=UserPutTask.jsp";
		document.form1.submit();
	}
	function payAction() {
		document.form1.action = "choosepay.jsp?taskid=<%=taskid%>";//跳转到支付界面
		document.form1.submit();
	}
</script>
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