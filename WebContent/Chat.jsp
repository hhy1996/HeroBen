<%@page import="com.goli.heroben.factory.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.goli.heroben.vo.UserBean"%>
<% 
	UserBean user=(UserBean)request.getSession().getAttribute("userbean");
	String chatwith=request.getParameter("chatwith");
%>
<!Doctype html>
<html>
	<%if(user==null){ %>
	<script>
		alert("请先登录");
		window.close();
	</script>
	<%}else{ 
		if(chatwith!=null&&user.getTelephone().equals(chatwith)){
	%>
	<script>
		alert("您不可以和自己聊天");
		window.close();
	</script>
	<%
		}
	%>
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
		<div class="ui center aligned grid">
			<div class="row"></div>
			<div class="fourteen wide column">
			<form class="ui form">
				<div class="field">
				<label><i class="comments icon"></i>正在与<%=chatwith %>交流中</label>
				<textarea rows="15" style="resize: none;" readonly="readonly" id="history"></textarea>
				</div>
				<div class="field">
					<textarea rows="3" style="resize: none;" id="sendvalue"></textarea>
				</div>
				<div class="field" style="float:right;">
					<button class="ui basic button" onClick="return resetit();">清空</button>
					<button class="ui primary button" id="sendbtn" onClick="return false;">发送</button>
				</div>
			</form>
			</div>
	</body>
	<script>
		function resetit()
		{
			document.getElementById("sendvalue").value="";
			return false;
		}

		function attachmsg(people,message)
		{
			var date = new Date();
			var time=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			var tempstr=document.getElementById("history").value;
			tempstr+="\n"+time+"  "+people+"说:\n"+message;
			document.getElementById("history").value=tempstr;
			
			return false;
		}
	</script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	        <script type="text/javascript" >
        $(function() {
            var url = "ws://localhost:8080/HeroBen/websocket";
            var ws = "";
            var message ={"id":"","msg":"","form":"","to":""};
            function connection() {
                ws = new WebSocket(url);
                console.log("connection.......");
                ws.onmessage = function (e){
                    var json = eval('(' +  e.data.toString() + ')');
                    attachmsg(<%=chatwith%>,json.msg);
                }
                ws.onclose = function() {
                }
                ws.onerror = function (e){
                }
                ws.onopen = function() {
                    message.id = <%=user.getTelephone()%>;
                    message.to = <%=chatwith%>
                    message.msg = "newUser";
                    console.log(JSON.stringify(message));
                    ws.send(JSON.stringify(message));
                    message.msg = "";
                }
            }
           	connection();

            $("#sendbtn").click(function() {//send message
                message.to = <%=chatwith%>;
                message.msg = $("#sendvalue").val();
                $("#sendvalue").val("");
                ws.send(JSON.stringify(message));
                attachmsg("您",message.msg);
                message.msg = "";
            });
        });
        
        </script>
        <%} %>
</html>