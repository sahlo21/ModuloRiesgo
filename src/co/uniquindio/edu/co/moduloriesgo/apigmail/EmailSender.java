package co.uniquindio.edu.co.moduloriesgo.apigmail;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

public class EmailSender implements Runnable {

	String destinatario, asunto, cuerpo;

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public EmailSender(String destinatario, String asunto, String cuerpo) {
		super();
		this.destinatario = destinatario;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}

	@Override
	public void run() {
		String remitente = "moduloriesgosuniquindio@gmail.com";
	    String claveemail = "dzpamcgjotepgsqt";

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", claveemail);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, claveemail);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();
	    }

	}
}
