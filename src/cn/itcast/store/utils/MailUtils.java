package cn.itcast.store.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.Session

		Properties props = new Properties();
		//
		//props.setProperty("mail.transport.protocol", "SMTP");
		
		//
		//props.setProperty("mail.host", "smtp.126.com");
		//props.setProperty("mail.smtp.auth", "true");// 

		// 
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//
				return new PasswordAuthentication("admin@store.com", "admin");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.
		Message message = new MimeMessage(session);

		//
		message.setFrom(new InternetAddress("admin@store.com"));

		//
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//
		message.setSubject("注册");
		// message.setText("<a href='#'></a>");

		String url="http://localhost:8080/store_v5/UserServlet?method=active&code="+emailMsg;
		String content="<h1>来自毒蛇网的用户激活邮件，请点击以下链接!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		//
		message.setContent(content, "text/html;charset=utf-8");

		// 3.
		Transport.send(message);
	}
	public static void main(String[] args) throws AddressException, MessagingException {
		MailUtils.sendMail("aaa@store.com", "123456789");
	}
}
