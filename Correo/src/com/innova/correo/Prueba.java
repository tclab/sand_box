package com.innova.correo;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Prueba {
	
	Properties propiedades = new Properties();
	
	public void envio(){
		try {
			
			MimeMultipart multiParte = new MimeMultipart();
			Session session = null;
			MimeMessage message = null;
			
			BodyPart texto = new MimeBodyPart();
			BodyPart adjunto = new MimeBodyPart();
			
			// PROPIEDADES DE LA CONECCIÓN
				propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");		
				propiedades.setProperty("mail.smtp.starttls.enable", "true");		
				propiedades.setProperty("mail.smtp.port","587");		
				propiedades.setProperty("mail.smtp.user", "iexplorerservices@gmail.com");		
				propiedades.setProperty("mail.smtp.auth", "true");
			
			// SESSION
				session = Session.getDefaultInstance(propiedades);
			
			// CONTENIDO DEL CORREO			
				//Texto
				texto.setText("Tiped text");
				
				//Adjunto
				adjunto.setDataHandler(new DataHandler(new FileDataSource("/home/juan/Desktop/prueba.txt")));
				adjunto.setFileName("tiped.txt");
			
			
			// CONSTRUCCION DEL MENSAJE
				message = new MimeMessage(session);
				message.setFrom(new InternetAddress("iexplorerservices@gmail.com")); // From
				message.addRecipient(Message.RecipientType.TO, new InternetAddress("iexplorerservices@gmail.com")); // To
				message.setSubject("A"); // Subject
				
				// Se agrega el contenido del correo
				multiParte.addBodyPart(texto);
				multiParte.addBodyPart(adjunto);
				message.setContent(multiParte);
			
			// ENVIO DEL MENSAJE
				Transport t = session.getTransport("smtp");
				t.connect("iexplorerservices@gmail.com","qwerty*12");
				t.sendMessage(message,message.getAllRecipients());
				t.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Prueba().envio();
	}

}

///home/juan/Desktop  TypeRecord