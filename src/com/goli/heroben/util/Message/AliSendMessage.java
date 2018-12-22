package com.goli.heroben.util.Message;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class AliSendMessage implements SendMessage {

	@Override
	public String sendCheckMsg(String tel, int length) {
		char[] choose = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		String code = "";
		for (int i = 0; i < length; i++) {
			code += choose[0 + (int) (Math.random() * ((9 - 0) + 1))];
		}
		try {
			TaobaoClient client = new DefaultTaobaoClient("https://eco.taobao.com/router/rest", "24541610",
					"60ff420deb54819aaad26908ba95b3b9");
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend("");
			req.setSmsType("normal");
			req.setSmsFreeSignName("侠客帮HeroBen");
			req.setSmsParamString("{code:'" + code + "'}");
			req.setRecNum(tel);
			req.setSmsTemplateCode("SMS_76365151");
			AlibabaAliqinFcSmsNumSendResponse rsp;
			rsp = client.execute(req);
			if(!rsp.isSuccess())
			{	
				return null;
			}else
				return code;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
