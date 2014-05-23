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

//	public static void main(String[] args) {
//
//		String senhaNova = "teste";
//		String login = "teste";
//		String destinatario = "eljbento@gmail.com";
//
//
//		sendMail(senhaNova, login, destinatario);
//	}

	public void sendMail(String senhaNova, String nome,
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
			buffer.append("Ask TecKForum.\n\n");
			buffer.append("Prezado,"+nome+",\n\n");
			buffer.append("Informamos abaixo os seus dados para o seu acesso(Login e Senha) ao nosso sistema.\n\n");
			buffer.append("Login: ");
			buffer.append(destinatario + "\n");
			buffer.append("Senha: " + senhaNova + " .\n\n\n");
			buffer.append("Caso não tenha solicitado seus dados, favor desconsiderar.\n\n");
			buffer.append("Atenciosamente,\n\n");
			buffer.append("Ask TechForum\n");

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatario));
			message.setSubject("Email para Recuperacao De Senha");
			message.setText(buffer.toString());


			System.out.println("Done");
			
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}