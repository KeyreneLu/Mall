package com.keyrene.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		props.setProperty("mail.smtp.ssl.enable","true");
		props.setProperty("mail.smtp.ssl.socketFactory",sf+"");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", PropertiesUtil.getValue("mail.smtp.host"));
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true
		props.setProperty("mail.smtp.port",PropertiesUtil.getValue("mail.smtp.port"));
		props.setProperty("mail.debug", "true");
		// 创建验证器

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("997321338@qq.com", PropertiesUtil.getValue("password"));
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(PropertiesUtil.getValue("username"))); // 设置发送者

		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}
}
