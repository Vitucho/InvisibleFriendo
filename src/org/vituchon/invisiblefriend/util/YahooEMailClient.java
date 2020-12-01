package org.vituchon.invisiblefriend.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class YahooEMailClient implements EMailClient {
	private final String user = "yahoo_user";
	private final String from = "yahoo_user@yahoo.com";
	private final String host = "smtp.mail.yahoo.com";
	private final Properties properties = System.getProperties();
	
	public YahooEMailClient() {
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
	}
	
	@Override
	public void send(String to, String subject, String content) {
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, "token_granted_for_app"); // granted at https://login.yahoo.com/account/security
			}
		});

		session.setDebug(true);
		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(content,"text/html; charset=utf-8");

			System.out.println("sending...");
			Transport.send(message);
			System.out.println("Message sent OK");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		String subject = "subject";
		String content = "content"; 
		YahooEMailClient client = new YahooEMailClient();
		client.send("somebody@else.com", subject, content);
	}
}
