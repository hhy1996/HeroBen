var code="null";
var nums = 60;
var btn;
$(document).ready(function() {
	$('.ui.menu .ui.dropdown').dropdown({
		on: 'hover'
	});
	$('.ui.menu a.item')
	.on('click', function() {
		$(this)
		.addClass('active')
		.siblings()
		.removeClass('active')
		;
	})
	;
})
;
function doLoop(){
	nums--;
	if(nums > 0){
		btn.value = nums+'秒后重试';
	}else{
		clearInterval(clock); //清除js定时器
		btn.disabled = false;
		btn.value = '重新发送验证码';
		nums = 60; //重置时间
	}
}
var handler2 = function (captchaObj) {
	$("#forgetbtn").click(function(e){
		var result = captchaObj.getValidate();
		var id=document.getElementById("telnum").value;
		var code=document.getElementById("checkcode").value;
		var pwd=document.getElementById("pwd").value;
		var reqpwd=document.getElementById("reqpwd").value;
		var flag=true;
		if(id==""){
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>请先输入电话或邮箱</p></div>";
			flag=false;
		}if(code==""){
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>请先输入验证码</p></div>";
			flag=false;
		}if(pwd==""){
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>请先输入密码</p></div>";
			flag=false;
		}if(reqpwd==""){
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>请先输入确认密码</p></div>";
			flag=false;
		}if(reqpwd!=pwd){
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>两次输入密码不一样</p></div>";
			flag=false;
		}if(!result){
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>请先进行验证</p></div>";
			flag=false;
		}
		if(flag){
			$("#infoform").ajaxSubmit({        
				url: 'Forget',
				type: 'POST',
				dataType: 'json',
				data: {},
				success: function (data) {   
					if(data.status=="success"){
						alert("重置密码成功");
						window.location.href='login.jsp';
					}else if(data.status="nouser"){
						document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>用户不存在</p></div>";
					}else if(data,status=="codeError"){
						document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>验证码错误</p></div>";
					}else
						document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>重置密码失败,请重试</p></div>";
						
				},  
				error: function()
				{ 
					document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">操作失败</div><p>网络发生了异常</p></div>"; 
				}
			});
		}
		$('.message .close')
    	.on('click', function() {
    		$(this)
    		.closest('.message')
    		.transition('fade')
    		;
    	})
    	;
	});
	
	$("#telnum").keydown(function(){
		captchaObj.reset();
	});
	$("#getcode").click(function (e) {
		var result = captchaObj.getValidate();
		var telephone=document.getElementById("telnum").value;
		btn = document.getElementById("getcode");
		if(telephone=="")
		{	
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">获取失败</div><p>请先输入电话号码或邮箱</p></div>";
		}else if (!result) {
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">获取失败</div><p>请先点击验证码验证</p></div>";
		} else {		
			btn.disabled=true;
			btn.value = nums+'秒后重试';
			clock = setInterval(doLoop, 1000); //一秒执行一次
			$.ajax({
				url: 'SendForgetCode',
				type: 'POST',
				dataType: 'json',
				data: {
					geetest_challenge: result.geetest_challenge,
					geetest_validate: result.geetest_validate,
					geetest_seccode: result.geetest_seccode,
					tel: telephone
				},
				success: function (data) {
					if (data.status === 'success') {
						if(data.code=="error")
						{
							document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">获取失败</div><p>获取验证码过快,请稍后重试</p></div>";
							clearInterval(clock); //清除js定时器
							btn.disabled = false;
							btn.value = '获取验证码';
						}else if(data.code=='nouser'){
							document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">获取失败</div><p>账户不存在哦</p></div>";
							clearInterval(clock); //清除js定时器
							btn.disabled = false;
							btn.value = '获取验证码';
						}
					} else if (data.status === 'fail') {
						document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">获取失败</div><p>验证失效,请先点击验证码验证</p></div>";
						clearInterval(clock); //清除js定时器
						btn.disabled = false;
						btn.value = '获取验证码';
						captchaObj.reset();
					}
			        $('.message .close')
			    	.on('click', function() {
			    		$(this)
			    		.closest('.message')
			    		.transition('fade')
			    		;
			    	})
			    	;
				}
			})
		}
		
		e.preventDefault();
	});
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha2");
        captchaObj.onReady(function () {
        	$("#wait2").hide();
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
};
    $.ajax({
        url: "Vertify?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // 调用 initGeetest 初始化参数
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
            initGeetest({
            	gt: data.gt,
            	challenge: data.challenge,
                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                product: "popup", // 产品形式，包括：float，popup
                width: "100%"
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handler2);
        }
    });
    $('.message .close')
	.on('click', function() {
		$(this)
		.closest('.message')
		.transition('fade')
		;
	})
	;