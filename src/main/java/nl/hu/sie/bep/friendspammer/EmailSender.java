package nl.hu.sie.bep.friendspammer;

import nl.hu.sie.bep.exceptions.MailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

	private EmailSender()
	{

	}

	public static void sendEmail(String subject, String to, String messageBody, boolean asHtml) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");
		props.put("mail.smtp.auth", "true");

		final String username = "4f9c719a29a510";
		final String password = "38e954709fce75";

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("spammer@spammer.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);
			
			if (asHtml) {
					message.setContent(messageBody, "text/html; charset=utf-8");
			} else {
				message.setText(messageBody);	
			}
			Transport.send(message);

			MongoSaver.saveEmail(to, "spammer@spamer.com", subject, messageBody, asHtml);

		} catch (MessagingException e) {
			throw new MailException(e);
		}
	}

	public static void sendEmail(String subject, String[] toList, String messageBody, boolean asHtml) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");
		props.put("mail.smtp.auth", "true");
		
		final String username = "4f9c719a29a510";
		final String password = "38e954709fce75";

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		try {

			for (int index = 0; index < toList.length; index++) {
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("spammer@spammer.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toList[index]));
				message.setSubject(subject);
				
				if (asHtml) {
						message.setContent(messageBody, "text/html; charset=utf-8");
				} else {
					message.setText(messageBody);	
				}
				Transport.send(message);

				String sentToEmail = Arrays.toString(InternetAddress.parse(toList[index]));

				if(sentToEmail != null)
				{
					logger.info("Sent email to {0}", sentToEmail);
				}
				else
				{
					logger.error("No email address found");
				}
			}
		} catch (MessagingException e) {
			throw new MailException(e);
		}
	}
	
}
