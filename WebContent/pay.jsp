<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.taobao.api.internal.util.StringUtils"%>
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
	margin-top: 30px;
}

#Pic2 {
	border: 1px solid grey;
	width: 160px;
}

#WPtext {
	font-size: 16px;
	width: 160px;
	margin-bottom: 100px;
}

h1 {
	text-align: center;
	margin-bottom: 50px;
	font-size: 50px;
	margin-top: 120px;
}

h2 {
	text-align: center;
	margin-bottom: 50px;
	font-size: 50px;
	margin-bottom: 120px;
}

#WTwo {
	border: 1px solid grey;
}
</style>

</head>
<body>
	<%
		String id = request.getParameter("taskid");
		String cost = null;
		String path = null;
		try {
			cost = (String) request.getSession().getAttribute(id + "cost");
			path = (String) request.getSession().getAttribute(id + "url");
			if(StringUtils.isEmpty(cost)||StringUtils.isEmpty(path))
			{
				response.sendRedirect("HomePage");
				return;
			}
		} catch (Exception e) {
			response.sendRedirect("HomePage");
			return;
		}
	%>
	<div class="ui center aligned grid">
		<div class="row"></div>
		<div class="seven wide column"></div>
		<div class="two wide column">
			<img src="images/WePayLogo.png" class="ui fluid image">
		</div>
		<div class="seven wide column"></div>
		<div class="seven wide column"></div>
		<div class="two wide column">
			<img src="http://qr.liantu.com/api.php?text=<%=path%>"
				class="ui fluid image">
		</div>
		<div class="seven wide column"></div>
		<div class="six wide column"></div>
		<div class="four wide column">
			<label>本次共计支付<%=cost%>元
			</label>
		</div>
		<div class="six wide column"></div>
	</div>
</body>
<script src="js/paycheck.js"></script>
<script>
	setInterval("checkpay(<%=id%>)","500");
</script>
</html>