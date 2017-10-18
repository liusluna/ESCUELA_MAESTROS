package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaCorreo {

	//EnviaCorreo corre =  new GeneraEnviaCorreo("lius.luna@gmail.com" , "rafaelchagolla@gmail.com", "Prueba", "Prueba de envio de correo con JavaMail" + "<br><br> Saludos, <br>Luis") ;
	public EnviaCorreo(String TO, String CC, String Asunto, String MensajeHTML) {
		// TODO Auto-generated constructor stub
		
		Properties mailServerProperties;
		Session getMailSession;
		MimeMessage generateMailMessage;
		
		try {
		//paso1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
		
		//paso2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(CC));
		generateMailMessage.setSubject(Asunto);
		generateMailMessage.setContent(MensajeHTML, "text/html");
		
		System.out.println("Mail Session has been created successfully..");
		
		
		//paso3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "sistemasdistribuidosuniva@gmail.com", "supercompleja");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		}catch (MessagingException e) {
			throw new RuntimeException(e);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al intentar enviar correo: " + e.getMessage());

		} 
		
	}

}
