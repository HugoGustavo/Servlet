package br;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String to = "hugog.valin@gmail.com";
		
		String from = "hugog.valin@gmail.com";
		
		String host = "localhost";
		
		Properties properties = System.getProperties();
		
		properties.setProperty("mail.smtp.host", host);
		
		Session session = Session.getDefaultInstance(properties);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(from));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("This is the Subject Line!");
			
			BodyPart messageBodyPart = new MimeBodyPart();
			
			messageBodyPart.setText("This is message body");
			
			Multipart multipart = new MimeMultipart();
			
			multipart.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();
			String filename = "file.txt";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			
			message.setContent(multipart);
			
			Transport.send(message);
			
			String title = "Send Email";
	         String res = "Sent message successfully....";
	         String docType =
	            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	         
	         out.println(docType +
	            "<html>\n" +
	               "<head><title>" + title + "</title></head>\n" +
	               "<body bgcolor = \"#f0f0f0\">\n" +
	                  "<h1 align = \"center\">" + title + "</h1>\n" +
	                  "<p align = \"center\">" + res + "</p>\n" +
	               "</body></html>"
	         );
		} catch(MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
}
