<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.github.wxpay.sdk.WXPayUtil"
	import="com.taobao.api.internal.util.StringUtils"
	import="java.util.Map" import="com.goli.heroben.factory.DaoFactory"
	import="com.goli.heroben.vo.PaymentBean"
	import="com.goli.heroben.vo.TaskBean"%>
<%
	String inputLine;
	String notityXml = "";
	try {
		while ((inputLine = request.getReader().readLine()) != null) {
			notityXml += inputLine;
		}
		request.getReader().close();
	} catch (Exception e) {
	}
	if (!StringUtils.isEmpty(notityXml)) {
		Map m = null;
		try {
			m = WXPayUtil.xmlToMap(notityXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (m != null) {
			System.out.println(m);
			String return_code = (String) m.get("return_code");
			String result_code = (String) m.get("result_code");
			if ("SUCCESS".equals(return_code)) {
				if ("SUCCESS".equals(result_code)) {
					String id = (String) m.get("out_trade_no");
					String fee = (String) m.get("total_fee");
					int total_fee = Integer.parseInt(fee);
					double money = ((double) total_fee) / 100.0;
					try {
						PaymentBean paybean = DaoFactory.getIPaymentDaoInstance().findPaymentById(id);
						if (paybean != null && paybean.getMoney() == money) {
							String transaction_id = (String) m.get("transaction_id");
							DaoFactory.getIPaymentDaoInstance().doUpdatePlatformId(id, transaction_id);
							DaoFactory.getIPaymentDaoInstance().doUpdatePlatName(id, "wechat");
							TaskBean task = DaoFactory.getITaskDaoInstance().findTaskByPayId(id);
							DaoFactory.getITaskDaoInstance().doUpdateState(task.getId(), "waitingrev");
							response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
	}
%>