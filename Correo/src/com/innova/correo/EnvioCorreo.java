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

public class EnvioCorreo {
	
	Properties propiedades = new Properties();
	
	public void envio(){
		try {
			// Nombre del host de correo, es smtp.gmail.com
			propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");		
			// TLS si está disponible
			propiedades.setProperty("mail.smtp.starttls.enable", "true");		
			// Puerto de gmail para envio de correos
			propiedades.setProperty("mail.smtp.port","587");		
			// Nombre del usuario
			propiedades.setProperty("mail.smtp.user", "iexplorerservices@gmail.com");		
			// Si requiere o no usuario y password para conectarse.
			propiedades.setProperty("mail.smtp.auth", "true");
			
			//Se crea la sesion
			Session session = Session.getDefaultInstance(propiedades);
			
			//Quitar para ocultar operaciones
			session.setDebug(true);
			
			//Para adjuntar el archivo se crean dos partes: texto y archivo
			//Texto
			BodyPart texto = new MimeBodyPart();
			texto.setText("Texto del correo");
			
			//Archivo adjunto
//			BodyPart adjunto = new MimeBodyPart();
//			adjunto.setDataHandler(new DataHandler(new FileDataSource("/home/juan/Desktop/ThinkandGrowRich.pdf")));
//			adjunto.setFileName("ThinkandGrowRich.pdf");
			
			MimeMultipart multiParte = new MimeMultipart();

			multiParte.addBodyPart(texto);
//			multiParte.addBodyPart(adjunto);
			
			MimeMessage message = new MimeMessage(session);
			
			
			// Quien envia el correo
			message.setFrom(new InternetAddress("juan1018@gmail.com"));
			
			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("juan1018@gmail.com"));
			
			// Asunto y mensaje
			message.setSubject("Correo casero");
			
			// Contenido del correo
			message.setContent(multiParte);
			
			// Envio del mensaje
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
		new EnvioCorreo().envio();
	}

}

///home/juan/Desktop  TypeRecord