package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@ViewScoped
public class AdministradorEditarAdministradorMBean {

	private UsuarioAdministrador administradorSelecionado;
	private AdministradorDao administradorDao;

	private String alterarSenha;
	private Boolean controleAlterarSenha;

	public AdministradorEditarAdministradorMBean() {
		administradorSelecionado = new UsuarioAdministrador();
		administradorDao = new AdministradorDao();
		alterarSenha = "FALSE";
		controleAlterarSenha = false;
	}

	@ManagedProperty(value = "#{administradorVisualizarAdministradorMBean}")
	private AdministradorVisualizarAdministradorMBean administradorVisualizarAdministradorMBean;

	public void setAdministradorVisualizarAdministradorMBean(
			AdministradorVisualizarAdministradorMBean administradorVisualizarAdministradorMBean) {
		this.administradorVisualizarAdministradorMBean = administradorVisualizarAdministradorMBean;
	}

	@PostConstruct
	void postConstruct() {
		downloadAdministrador();
	}

	public void downloadAdministrador() {
		administradorSelecionado = administradorVisualizarAdministradorMBean.getAdministradorSelecionado();
	}

	public UsuarioAdministrador getAdministradorSelecionado() {
		return administradorSelecionado;
	}

	public void setAdministradorSelecionado(UsuarioAdministrador administradorSelecionado) {
		this.administradorSelecionado = administradorSelecionado;
	}

	public AdministradorDao getAdministradorDao() {
		return administradorDao;
	}

	public void setAdministradorDao(AdministradorDao administradorDao) {
		this.administradorDao = administradorDao;
	}

	public String getAlterarSenha() {
		return alterarSenha;
	}

	public void setAlterarSenha(String alterarSenha) {
		this.alterarSenha = alterarSenha;
	}

	public String updateAdministradorSelecionado() {
		try {
			if (!(alterarSenha.equals("FALSE"))) {
				controleAlterarSenha = true;
				administradorSelecionado.setSenha(alterarSenha);
			}
			if (administradorDao.updateUsuarioAdministrador(administradorSelecionado, controleAlterarSenha)) {
				Mensagem.make("Administrador alterado com sucesso !");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				administradorVisualizarAdministradorMBean.atualizaListaAdministrador();
				return "administradorVisualizarAdministrador?faces-redirect=true";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			Mensagem.make(e.toString());
		}
		return "";
	}

}
