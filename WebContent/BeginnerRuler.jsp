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
			<i class="bookmark icon"></i>
			<div class="content">
				规则中心
				<div class="sub header">ruler</div>
			</div>
		</h1>
	</div>
	<h4 class="ui horizontal header divider">
		<i class="block layout icon"></i>
	</h4>
	<div class="ui centered grid">
		<div class="row"></div>
		<div class="eight wide column">

			<h3 class="ui block header">
				<font><font>规则总则</font></font>
			</h3>
			一、为了规范侠客帮的经营秩序，明确用户与侠客帮的权利义务，保障各方合法权益，拟定本规范。<br>
			二、侠客帮有权随时变更本规范并在网站上予以公示。若用户不同意相关变更，应立即停止使用侠客帮服务。<br>

			<h3 class="ui block header">
				<font><font>注册协议</font></font>
			</h3>
			<h4>一、接受条款</h4>
			1.1
			在注册成为侠客帮用户之前，请您认真阅读协议条款，并确保充分理解各条款内容，如有疑问，您可以向侠客帮进行询问。您注册成功后，可接受侠客帮的服务，即意味着本注册协议各条款内容为双方所同意，本注册协议对双方产生法律约束力。<br>
			1.2
			侠客帮现有和将来制定和修改的平台服务规则，在侠客帮网站予以公示。侠客帮有权根据实际情况制定或者修改平台服务规则，如果对用户产生重大影响的，侠客帮将征求用户意见。<br>

			<h4>二、服务内容</h4>
			2.1
			侠客帮的具体服务内容由侠客帮根据实际情况提供。侠客帮保留根据实际情况随时调整侠客帮平台提供的服务种类、形式的权利。侠客帮不承担因业务调整给用户造成的损失。<br>
			2.2 通过侠客帮提供的平台服务，您可在侠客帮网站上发布任务、查询并接受任务等以及使用其它信息服务及技术服务。<br> 2.3
			您在侠客帮的交易过程中与其他用户发生交易纠纷时，一旦您或对方任一方或双方共同提交我们要求调处，则我们作为独立的第三方有权根据了解的事实做出调处决定，您了解并同意接受我们的判断和调处决定。<br>
			2.4
			您因进行交易、向侠客帮获取有偿服务或接触侠客帮服务器而发生的所有应纳税赋，以及相关硬件、软件、通讯、网络服务及其他方面的费用均由您自行承担。<br>
			2.5 侠客帮保留不经事先通知为维护、升级或其它目的暂停本服务任何部分的权利。<br>

			<h4>三、知识产权</h4>
			3.1 本网站的整体内容版权属于Goli小组所有。侠客帮网站所有的产品、技术与程序均属于侠客帮知识产权，未经授权不得使用。<br>
			3.2 本网站所有设计图样、产品及服务名称，均为Goli小组所享有，未经授权任何人不得使用、复制或用作其他用途。<br> 3.3
			侠客帮对网站专有内容、原创内容和其他通过授权取得的独占或者独家内容享有完全知识产权。未经侠客帮许可，任何单位和个人不得私自复制、传播、展示、镜像、上载、下载、使用，或者从事任何其他侵犯侠客帮知识产权的行为。否则，侠客帮将追究相关法律责任。<br>

			<h4>四、注册信息</h4>
			4.1 用户自愿注册用户信息，用户在注册时提供的所有信息，都是基于自愿，用户有权在任何时候拒绝提供这些信息。<br> 4.2
			用户承诺其注册的信息真实、有效，并有绝对控制权，用户须自行负责其账户的保密工作，用户不得将其账号、密码转让或出借予他人使用。如用户发现账号遭他人非法使用，应立即通知侠客帮。因黑客行为或用户的保管疏忽导致账号、密码遭他人非法使用，因此而产生的任何损失或损害，侠客帮不承担任何责任。<br>
			4.3
			如有合理理由怀疑您提供的资料错误、不实、过时或不完整的，侠客帮有权向您发出询问或要求改正的通知，并有权直接做出删除相应资料的处理，直至中止、终止对您提供部分或全部平台服务。因此而造成的直接或间接损失及不利后果将全部由您承担。<br>

			<h4>五、隐私保护</h4>
			5.1
			侠客帮通过技术手段、隐私保护服务功能、强化内部管理等办法充分保护用户的个人资料安全。侠客帮保证不对外公开或向第三方提供用户注册的个人资料，及用户在使用服务时存储的非公开内容。您了解并同意，如您涉嫌侵犯他人知识产权等合法权益，则我们有权在初步判断涉嫌侵权行为存在的情况下，向权利人提供您必要的身份信息。我们亦有权应司法及行政部门的要求，向其提供您的用户信息和交易记录等必要信息。<br>

			<h4>六、行为准则</h4>
			6.1
			用户在使用侠客帮服务过程中，必须遵循国家的相关法律法规，不得利用侠客帮平台发布危害国家安全、色情、暴力等非法内容；不得利用侠客帮平台发布含有虚假、有害、胁迫、侵害他人隐私、骚扰、侵害、中伤、粗俗、或其它道德上令人反感的内容；不得以任何方式危害他人的合法权益。<br>
			6.2
			用户使用侠客帮服务的行为若有任何违反国家法律法规或侵犯任何第三方的合法权益的情形时，侠客帮有权直接删除该等违反规定之内容。如对侠客帮造成影响或损失的，侠客帮有权追究其法律责任。<br>

			<h4>七、责任范围和责任限制</h4>
			7.1
			侠客帮对平台服务不作任何明示或暗示的保证，包括但不限于平台服务的适用性、没有错误或疏漏、持续性、准确性、可靠性、适用于某一特定用途。同时，侠客帮也不对自身和合作方的交易平台服务所涉及的技术及信息的有效性、准确性、正确性、可靠性、质量、稳定、完整和及时性作出任何承诺和保证。<br>
			7.2
			由于信息网络正常的设备维护，信息网络连接故障，电脑、通讯或其他系统的故障，电力故障，罢工，劳动争议，暴乱，起义，骚乱，生产力或生产资料不足，火灾，洪水，风暴，爆炸，战争，政府行为，司法行政机关的命令或第三方的不作为等原因而造成的不能服务或延迟服务，侠客帮均不承担责任。<br>

			<h4>八、服务变更、中断或终止</h4>
			8.1
			如因系统维护或升级的需要而需暂停网络服务、服务功能的调整，侠客帮将尽可能事先在网站上进行通告。如发生下列任何一种情形，侠客帮有权单方面中断或终止向用户提供服务而无需通知用户：
			<br> 8.1.1 侠客帮终止向您提供服务后，您涉嫌再一次直接或间接或以他人名义注册为侠客帮用户的。<br>
			8.1.2 您提供用户信息不真实或不准确或不及时或不完整。 <br> 8.1.3
			侠客帮服务规则变更时，您明示并通知我们不愿接受新的服务规则的。 <br> 8.1.4 其它侠客帮认为应当终止服务的情况。 <br>
			8.2 因上述原因双方终止合作的，不视为侠客帮方的违约情形。<br> 8.3您理解并同意，您与侠客帮的服务关系终止后： <br>
			8.3.1侠客帮依法继续保存您的资料。 <br>
			8.3.2您在使用服务期间存在违法行为或违反本协议和/或规则的行为的，侠客帮仍可依据本协议向您主张权利。 <br>
			8.3.3您在使用服务期间因使用服务与其他用户之间发生的关系，不因本协议的终止而终止，其他用户仍有权向您主张权利，您应继续按您的承诺履行义务。
			<br>

			<h4>九、其他</h4>
			因本协议履行过程中产生的纠纷，由双方友好协商解决，协商不成的，由Goli委员会仲裁。<br>


			<div class="ui divider"></div>


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