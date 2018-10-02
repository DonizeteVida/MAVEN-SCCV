package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.senai.sp.jaguariuna.sccv.email.EmailUtil;
import br.senai.sp.jaguariuna.sccv.email.Mensagem;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.StringToMD5;

@ManagedBean(eager = true)
@SessionScoped
public class AlunoIndexMBean {

	private String modoSelecionado;
	private String funcaoCPF;
	private Usuario usuario;
	private UsuarioAdministrador usuarioAdministrador;
	private String cpfOuNif;
	private String senha;
	private UsuarioDao usuarioDao;
	private AdministradorDao administradorDao;

	private String cpfRecuperar;
	private Mensagem mensagem;
	private String codigo;
	private String codEnviado;
	private String novaSenha;

	public AlunoIndexMBean() {
		modoSelecionado = "user";
		funcaoCPF = "!TestaCPF(this.value) ? this.value = '' : this.value; avisoGrowl(TestaCPF(this.value));";
		usuarioDao = new UsuarioDao();
		administradorDao = new AdministradorDao();
		mensagem = new Mensagem();
	}

	public void enviar() {
		try {
			Usuario usuarioLocal = usuarioDao.buscaUsuarioPorCpf(cpfRecuperar);
			if (usuarioLocal != null) {
				codigo = String.valueOf(Calendar.getInstance().getTimeInMillis());
				mensagem.setMensagem(codigo);
				mensagem.setDestinatario(usuarioLocal.getEmail());
				mensagem.setAssunto("Codigo de Alteraï¿½ï¿½o de Senha");
				EmailUtil.enviaEmail(mensagem);
				Mensagem("Codigo enviado, aguarde alguns instantes !");

				Mensagem("Verifique sua caixa de entrada, e/ou caixa de spam !");
			} else {
<<<<<<< HEAD
				Mensagem("Usuario não encontrado !");
=======
				Mensagem("Usuario nï¿½o encontrado !");
>>>>>>> bc0e93ab894ddf123cca5690bc81a94d82de9d16
			}
		} catch (EmailException e) {
			e.printStackTrace();
			Mensagem("Erro ao enviar o e-mail: " + e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem(e.toString());
		}
	}

	public void comparaCod() {
		if (codigo.equals(codEnviado)) {
			try {
				Usuario usuarioLocal = usuarioDao.buscaUsuarioPorCpf(cpfRecuperar);
				usuarioLocal.setSenha(novaSenha);
				if (usuarioDao.updateUsuario(usuarioLocal)) {
					Mensagem("Senha editada com sucesso");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Mensagem(e.toString());
			}
		} else {
<<<<<<< HEAD
			Mensagem("Código incorreto");
=======
			Mensagem("Cï¿½digo incorreto");
>>>>>>> bc0e93ab894ddf123cca5690bc81a94d82de9d16
		}
	}

	public void trocaModoSelecionado() {
		modoSelecionado = modoSelecionado.equals("user") ? "admin" : "user";
		funcaoCPF = modoSelecionado.equals("user")
				? "!TestaCPF(this.value) ? this.value = '' : this.value; avisoGrowl(TestaCPF(this.value));"
				: "";
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getCodEnviado() {
		return codEnviado;
	}

	public void setCodEnviado(String codEnviado) {
		this.codEnviado = codEnviado;
	}

	public String getCpfOuNif() {
		return cpfOuNif;
	}

	public void setCpfOuNif(String cpfOuNif) {
		this.cpfOuNif = cpfOuNif;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getModoSelecionado() {
		return modoSelecionado;
	}

	public void setModoSelecionado(String modoSelecionado) {
		this.modoSelecionado = modoSelecionado;
	}

	public String getCpfRecuperar() {
		return cpfRecuperar;
	}

	public void setCpfRecuperar(String cpfRecuperar) {
		this.cpfRecuperar = cpfRecuperar;
	}

	public String getFuncaoCPF() {
		return funcaoCPF;
	}

	public void setFuncaoCPF(String funcaoCPF) {
		this.funcaoCPF = funcaoCPF;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioAdministrador getUsuarioAdministrador() {
		return usuarioAdministrador;
	}

	public void setUsuarioAdministrador(UsuarioAdministrador usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

	public String fazerLogin() {
		if (modoSelecionado.equals("user")) {
			try {
				Usuario usuarioLocal = usuarioDao.buscaUsuarioPorCpf(cpfOuNif);
				if (usuarioLocal != null) {
					if (usuarioLocal.getSenha().equals(StringToMD5.convertStringToMd5(senha))) {
						if (usuarioLocal.getStatus().getId() == 2) {
							Mensagem("Usuario encontra-se inativo !");
						} else {
							usuario = usuarioLocal;
							return "home?faces-redirect=true";
						}
					} else {
						Mensagem("Usuario e/ou senha incorretos !");
					}
				} else {
<<<<<<< HEAD
					Mensagem("Usuario não encontrado !!! ");
=======
					Mensagem("Usuario nï¿½o encontrado !!! ");
>>>>>>> bc0e93ab894ddf123cca5690bc81a94d82de9d16
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Mensagem(e.toString());
			}
		} else if (modoSelecionado.equals("admin")) {
			try {
				UsuarioAdministrador usuarioAdministrador = administradorDao.buscarAdministradorPorNif(cpfOuNif);

				if (usuarioAdministrador.getSenha().equals(senha)) {
					this.usuarioAdministrador = usuarioAdministrador;
					return "/admin/home?faces-redirect=true";
				} else {
					br.senai.sp.jaguariuna.sccv.utils.Mensagem.make("Admnistrador nÃ£o encontrado !");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				br.senai.sp.jaguariuna.sccv.utils.Mensagem.make(e.toString());
			}

		}
		return null;
	}

	public void Mensagem(String s) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
	}

	public void Mensagem(String s) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
	}

}
