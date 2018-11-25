package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;

@ManagedBean(eager = true)
@ViewScoped
public class AdministradorEditarAdministradorMBean {

	private UsuarioAdministrador administradorSelecionado;
	private AdministradorDao administradorDao;

	public AdministradorEditarAdministradorMBean() {
		administradorSelecionado = new UsuarioAdministrador();
		administradorDao = new AdministradorDao();
	}

	@ManagedProperty(value = "#{administradorVisualizarAdministradorMBean}")
	private AdministradorVisualizarAdministradorMBean administradorVisualizarAdministradorMBean;

	public void setAdministradorVisualizarAdministradorMBean(
			AdministradorVisualizarAdministradorMBean administradorVisualizarAdministradorMBean) {
		this.administradorVisualizarAdministradorMBean = administradorVisualizarAdministradorMBean;
	}

	@PostConstruct
	void postConstruct() {
		try {
			downloadAdministrador();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void downloadAdministrador() throws SQLException {
		administradorSelecionado = administradorDao.buscarAdministradorPorNif(
				administradorVisualizarAdministradorMBean.getAdministradorSelecionado().getNif());
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

}
