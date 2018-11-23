package br.senai.sp.jaguariuna.sccv.mBeans;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean
@ViewScoped
public class InserirAdministradorMBean {

	private UsuarioAdministrador usuarioAdministrador;
	private AdministradorDao administradorDao;

	public InserirAdministradorMBean() {
		administradorDao = new AdministradorDao();
		usuarioAdministrador = new UsuarioAdministrador();
	}

	@ManagedProperty("#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	@ManagedProperty("#{visualizarAdministradorMBean}")
	private VisualizarAdministradorMBean visualizarAdministradorMBean;

	public void setVisualizarAdministradorMBean(VisualizarAdministradorMBean visualizarAdministradorMBean) {
		this.visualizarAdministradorMBean = visualizarAdministradorMBean;
	}

	@PostConstruct
	public void post() {
		UsuarioAdministrador usuarioAdministrador = alunoIndexMBean.getUsuarioAdministrador();
		if (usuarioAdministrador.get_super() == 0) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/MAVEN-SCCV/aluno/index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String inserirUsuarioAdministrador() {
		try {
			if (administradorDao.inserirUsuarioAdministrador(usuarioAdministrador)) {
				Mensagem.make("Usuário administrador inserido com sucesso !");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				visualizarAdministradorMBean.atualizaListaAdministrador();
				return "administradorHome?faces-redirect=true";
			} else {
				Mensagem.make("Erro ao inserir usuário administrador !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.make(e.toString());
		}
		return null;
	}

	public UsuarioAdministrador getUsuarioAdministrador() {
		return usuarioAdministrador;
	}

	public void setUsuarioAdministrador(UsuarioAdministrador usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

}
