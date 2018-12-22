function checkpay(taskid)
{
	$.ajax({
		url: 'CheckPay',
		type: 'POST',
		dataType: 'json',
		data: {
			_taskid:taskid
		},
		success: function (data) {
			if (data.status === 'success') {
				alert("支付成功!即将跳往用户中心");
				window.location="UserPutTask.jsp";
			}
		}
	})
}