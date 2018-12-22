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
	});
});
$('.special.cards .image').dimmer({
	on: 'hover'
});

var handler2 = function (captchaObj) {
	$("#telnum").keydown(function(){
		captchaObj.reset();
	});
	$("#pwd").keydown(function(){
		captchaObj.reset();
	});
	$("#submit").click(function (e) {
        var result = captchaObj.getValidate();
        var tel=document.getElementById("telnum").value;
        var password=document.getElementById("pwd").value;
        if (!result) {
        	document.getElementById("notice2").innerHTML="<div class=\"row\"></div><div class=\"ui negative message\"></i><p>请先进行验证</p></div>";
            $("#notice2").show();
            setTimeout(function () {
                $("#notice2").hide();
            }, 2000);
        } else {
            $.ajax({
                url: 'Login',
                type: 'POST',
                dataType: 'json',
                data: {
                    geetest_challenge: result.geetest_challenge,
                    geetest_validate: result.geetest_validate,
                    geetest_seccode: result.geetest_seccode,
                    userid: tel,
                    userpwd: password
                },
                success: function (data) {
                    if (data.status === 'success') {
                        window.location="HomePage";
                    } else if (data.status === 'fail') {
                    	alert("智能验证失败");
                    	captchaObj.reset();
                    }else if(data.status==='pwderror'){
                    	alert("密码错误");
                    	captchaObj.reset();
                    }
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