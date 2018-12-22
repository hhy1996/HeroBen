package com.goli.heroben.util.Message;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailSendMessage implements SendMessage{

	@Override
	public String sendCheckMsg(String mailAddress, int length) {
		char[] choose = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		String code = "";
		for (int i = 0; i < length; i++) {
			code += choose[0 + (int) (Math.random() * ((9 - 0) + 1))];
		}
		String send_msg=Config.MAIL_TEMPLATE.replace("%CODE%", code);
		String smtphost = Config.MAIL_STMPHOST; // 发送邮件服务器
		String user = Config.MAIL_USERID; // 邮件服务器登录用户名
		String password = Config.MAIL_PASSWORD; // 邮件服务器登录密码
		String from = Config.MAIL_USERID; // 发送人邮件地址
		String to = mailAddress; // 接受人邮件地址
		String subject =Config.MAIL_TITLE; // 邮件标题
		String body = send_msg; // 邮件内容
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtphost);
			props.put("mail.smtp.auth","true");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			Session ssn = Session.getInstance(props, null);
			MimeMessage message = new MimeMessage(ssn);
			InternetAddress fromAddress = new InternetAddress(from);
			message.setFrom(fromAddress);
			InternetAddress toAddress = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(subject);
			message.setText(body);
			Transport transport = ssn.getTransport("smtp");
			transport.connect(smtphost, user, password);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			//transport.send(message);
			transport.close();
			return code;
		} catch(Exception m) {
			return null;
		}
	}

}
