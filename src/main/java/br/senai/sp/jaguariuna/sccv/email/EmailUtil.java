package br.senai.sp.jaguariuna.sccv.email;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {
	private static final String HOSTNAME = "smtp.gmail.com";
	private static final String USERNAME = "sccv.senai@gmail.com";
	private static final String PASSWORD = "senaisccv_2018";
	private static final String EMAILORIGEM = "sccv.senai@gmail.com";
	
	public static Email conectaEmail() throws EmailException{
		Email email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setStartTLSEnabled(true);
		email.setFrom(EMAILORIGEM);
		
		return email;
	}
	
	public static void enviaEmail(Mensagem mensagem) throws EmailException{
		Email email = conectaEmail();
		email.setSubject(mensagem.getAssunto());
		email.setContent("<html><body>" + mensagem.getMensagem() + "</body></html>","text/html");
		email.addTo(mensagem.getDestinatario());
		String retorno = email.send();
		System.out.println(retorno);
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Email enviado!","Para:" + mensagem.getDestinatario()));
	}
	
}
