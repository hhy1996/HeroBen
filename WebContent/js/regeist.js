var code="null";
var nums = 60;
var btn;

$(document)
.ready(function() {
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
$('.special.cards .image').dimmer({
	on: 'hover'
});

function selectimg(){
	document.getElementById("headpic").click();
}

function uploading(fnUpload){
	var dom = document.getElementById("headpic");
	var fileSize =  dom.files[0].size;
	var filename = fnUpload.value; 
	var mime = filename.toLowerCase().substr(filename.lastIndexOf(".")); 
	if(fileSize>2*1024*1024)
	{
		document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>图片文件不能大于2M</p></div>";
	}else if(mime!=".jpg"&&mime!=".png"){ 
		document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>图片格式必须为jpg或者png</p></div>";
	}else{
		$.ajaxFileUpload
		(
		{
            url: 'UploadHead', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'headpic', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
            	if(data.src=="102")
            		document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>图片文件不能大于2M</p></div>"; 
            	else if(data.src=="103")
            		document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>图片格式必须为jpg或者png</p></div>"; 
            	else
            		$("#headimg").attr("src", data.src);
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
            	document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">上传失败</div><p>网络错误！</p></div>"; 
            }
        }
        )
		$('.message .close')
		.on('click', function() {
			$(this)
			.closest('.message')
			.transition('fade')
			;
		})
		;
	}
}

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

function submitit(){
	var pwd=document.getElementById("pwd").value;
	var reqpwd=document.getElementById("reqpwd").value;
	var code2=document.getElementById("checkcode").value;
	if(reqpwd!=pwd){	
		document.getElementById("errormsg2").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">输入错误</div><p>两次密码输入不一致！</p></div>";
	}else if(code2!=code){
		document.getElementById("errormsg2").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">输入错误</div><p>短信验证码输入错误！</p></div>";
	}else{
		$("#infoform").ajaxSubmit({        
			url: 'Regeist',
			type: 'POST',
			dataType: 'json',
			data: {},
			success: function (data) {   
				if(data.status=="success"){
					alert("注册成功");
					window.location.href='HomePage';
				}else if(data.status=="nulluser")
				document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">注册失败</div><p>网络发生了异常</p></div>"; 
				else if(data.status=="erroremail")
					document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">注册失败</div><p>邮箱格式有误</p></div>"; 
				else if(data.status=="errortel")
					document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">注册失败</div><p>电话号码格式有误</p></div>"; 
				else if(data.status=="hadit")
					document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">注册失败</div><p>电话号码或邮箱已注册过</p></div>"; 
				else if(data.status=="errorsex")
					document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">注册失败</div><p>请选择性别</p></div>"; 
				else if(data.status=="codeerror")
					document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">注册失败</div><p>短信验证码错误</p></div>"; 
				else if(data.status=="unknow")
					document.getElementById('headimg').src=data.src;
			},  
			error: function()
			{ 
				document.getElementById("imgerror").innerHTML="<div class=\"ui negative transition message\"><i class=\"close icon\"></i><div class=\"header\">注册失败</div><p>网络发生了异常</p></div>"; 
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
}

$('.message .close')
.on('click', function() {
	$(this)
	.closest('.message')
	.transition('fade')
	;
})
;


$('.ui.form')
.form({
	fields: {
		telnum: {
			identifier: 'telnum',
			rules: [
			{
				type   : 'regExp[/^[0-9]{11}$/]',
				prompt : '请输入有效的电话号码'
			}
			]
		},
		checkcode: {
			identifier: 'checkcode',
			rules: [
			{
				type   : 'regExp[/^[0-9]{6}$/]',
				prompt : '请输入有效的验证码'
			}
			]
		},
		email: {
			identifier: 'email',
			rules: [
			{
				type   : 'email',
				prompt : '请输入有效的邮箱'
			}
			]
		},
		username: {
			identifier: 'username',
			rules: [
			{
				type   : 'maxLength[20]',
				prompt : '昵称过长'
			}
			]
		},
		pwd: {
			identifier: 'pwd',
			rules: [
			{
				type   : 'regExp[/^[a-zA-Z0-9]{6,20}$/]',
				prompt : '请输入6到20位由英文字母和数字组成的密码'
			}
			]
		},
		checkrule: {
			identifier: 'checkrule',
			rules: [
			{
				type   : 'checked',
				prompt : '请先同意服务条款'
			}
			]
		},
		headsrc: {
			identifier: 'headsrc',
			rules: [
			{
				type   : 'empty',
				prompt : '请先上传头像'
			}
			]
		}
	}
})
;

var handler2 = function (captchaObj) {
	$("#telnum").keydown(function(){
		captchaObj.reset();
	});
	$("#getcode").click(function (e) {
		var result = captchaObj.getValidate();
		var telephone=document.getElementById("telnum").value;
		btn = document.getElementById("getcode");
		if(telephone=="")
		{	
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">获取失败</div><p>请先输入电话号码</p></div>";
		}else if (!result) {
			document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">获取失败</div><p>请先点击验证码验证</p></div>";
		} else {		
			btn.disabled=true;
			btn.value = nums+'秒后重试';
			clock = setInterval(doLoop, 1000); //一秒执行一次
			$.ajax({
				url: 'SendCode',
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
							captchaObj.reset();
						}
						else
							code=data.code;
					} else if (data.status === 'fail') {
						document.getElementById("errormsg2").innerHTML="<div class=\"ui negative message\"><i class=\"close icon\"></i><div class=\"header\">获取失败</div><p>验证失效,请先点击验证码验证</p></div>";
						clearInterval(clock); //清除js定时器
						btn.disabled = false;
						btn.value = '获取验证码';
						captchaObj.reset();
					}
				}
			})
		}
		$('.message .close')
		.on('click', function() {
			$(this)
			.closest('.message')
			.transition('fade')
			;
		})
		;
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