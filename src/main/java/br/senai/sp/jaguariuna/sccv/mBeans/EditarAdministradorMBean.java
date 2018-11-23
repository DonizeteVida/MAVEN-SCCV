package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;

@ManagedBean(eager = true)
@SessionScoped
public class EditarAdministradorMBean {

	private UsuarioAdministrador administradorSelecionado;
	private AdministradorDao administradorDao;

	public EditarAdministradorMBean() {
		administradorSelecionado = new UsuarioAdministrador();
		administradorDao = new AdministradorDao();
	}

	@ManagedProperty(value = "#{visualizarAdministradorMBean}")
	private VisualizarAdministradorMBean visualizarAdministradorMBean;

	public void setVisualizarAdministradorMBean(VisualizarAdministradorMBean visualizarAdministradorMBean) {
		this.visualizarAdministradorMBean = visualizarAdministradorMBean;
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
		administradorSelecionado = administradorDao
				.buscarAdministradorPorNif(visualizarAdministradorMBean.getAdministradorSelecionado().getNif());
		if (visualizarAdministradorMBean.getEditarAdministradorMBean() == null) {
			visualizarAdministradorMBean.setEditarAdministradorMBean(this);
		}

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
