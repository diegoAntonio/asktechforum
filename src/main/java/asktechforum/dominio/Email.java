package asktechforum.dominio;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Email {

	private static final String USR = "nti.curriculos2@gmail.com";
	private static final String PWD = "curriculo2";

	public static void main(String[] args) {

		String senhaNova = "teste";
		String login = "teste";
		String destinatario = "eljbento@gmail.com";


		sendMail(senhaNova, login, destinatario);
	}

	public static void sendMail(String senhaNova, String login,
			String destinatario) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USR, PWD);
					}
				});

		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("Universidade Federal De Pernambuco - UFPE.\n");
			buffer.append("Nucleo de Tecnologia da Informação - NTI.\n");
			buffer.append("Curriculos NTI.\n");
			buffer.append("Sua senha no sistema foi alterada para a senha abaixo\n");
			buffer.append("nova Senha: " + senhaNova + " .\n\n\n");
			buffer.append("Usuario: ");
			buffer.append(login);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatario));
			message.setSubject("Email para Recuperacao De Senha");
			message.setText(buffer.toString());

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}