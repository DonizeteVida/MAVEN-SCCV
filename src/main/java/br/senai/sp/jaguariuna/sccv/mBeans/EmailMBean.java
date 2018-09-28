package br.senai.sp.jaguariuna.sccv.mBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.senai.sp.jaguariuna.sccv.email.EmailUtil;
import br.senai.sp.jaguariuna.sccv.email.Mensagem;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;

@ManagedBean
@SessionScoped
public class EmailMBean {
	private Mensagem mensagem;
	private String codigo;
	private Usuario usuario;
	private UsuarioDao usuarioDao;


	public void enviar() {
		try {
			EmailUtil.enviaEmail(mensagem);
		} catch (EmailException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao enviar o e-mail", e.toString()));
			e.printStackTrace();
		}
	}

	public EmailMBean() {
		mensagem = new Mensagem();
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
}
