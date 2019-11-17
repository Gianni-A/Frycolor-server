package com.gianni.frycolor.util;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

//https://myaccount.google.com/lesssecureapps?zx=xvonmtjnhxau&pli=1
public class SendMail {
	
	@Autowired
    public JavaMailSender javaMailSender;
	
	public static void sendEmail(String recipient, int userId) throws AddressException, MessagingException, IOException {
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587");
	   
	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication("todoanimados@gmail.com", "gianni91");
	      }
	   });
	   Message msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress("todoanimados@gmail.com", false));

	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	   msg.setSubject("Tutorials point email");
	   //msg.setContent("Tutorials point email", "text/html");
	   //String html = "<html lang='en' dir='ltr'><head><meta charset='utf-8'><title></title><style media='screen'>p {font-size: 25px; border: 1px solid blue;}</style></head><body><p class='texto'>This is a demo</p></body></html>";
	   msg.setContent("Thank you for registered at Frycolor, to finish you need to enter to this URL: http://localhost:8090/users/" + userId, "text/html");
	   msg.setSentDate(new Date());

	   /*MimeBodyPart messageBodyPart = new MimeBodyPart();
	   messageBodyPart.setContent("Tutorials point email", "text/html");*/

	   /*Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(messageBodyPart);
	   MimeBodyPart attachPart = new MimeBodyPart();

	   attachPart.attachFile("/var/tmp/image19.png");
	   multipart.addBodyPart(attachPart);
	   msg.setContent(multipart);*/
	   Transport.send(msg);   
	}

}
