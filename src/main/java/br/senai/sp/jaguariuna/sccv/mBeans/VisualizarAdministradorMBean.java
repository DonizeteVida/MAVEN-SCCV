package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@SessionScoped
public class VisualizarAdministradorMBean {

	AdministradorDao administradorDao;
	List<UsuarioAdministrador> listaAdministrador;
	UsuarioAdministrador administradorSelecionado;
	private String filtro;
	private EditarAdministradorMBean editarAdministradorMBean;

	public VisualizarAdministradorMBean() {
		administradorDao = new AdministradorDao();
		listaAdministrador = new ArrayList<UsuarioAdministrador>();
		administradorSelecionado = new UsuarioAdministrador();
		atualizaListaAdministrador();
	}

	public void buscaFiltro() {
		try {
			if (filtro.length() > 0 && filtro.contains("sn")) {
				listaAdministrador = administradorDao.listarAdministradorPorNIF(filtro);
			} else {
				listaAdministrador = administradorDao.listarAdministrador(filtro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public EditarAdministradorMBean getEditarAdministradorMBean() {
		return editarAdministradorMBean;
	}

	public void setEditarAdministradorMBean(EditarAdministradorMBean editarAdministradorMBean) {
		this.editarAdministradorMBean = editarAdministradorMBean;
	}

	public List<UsuarioAdministrador> getListaAdministrador() {
		return listaAdministrador;
	}

	public void setListaAdministrador(List<UsuarioAdministrador> listaAdministrador) {
		this.listaAdministrador = listaAdministrador;
	}

	public UsuarioAdministrador getAdministradorSelecionado() {
		return administradorSelecionado;
	}

	public void setAdministradorSelecionado(UsuarioAdministrador administradorSelecionado) {
		this.administradorSelecionado = administradorSelecionado;
		if (editarAdministradorMBean != null) {
			try {
				editarAdministradorMBean.downloadAdministrador();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void atualizaListaAdministrador() {
		try {
			listaAdministrador = administradorDao.listarAdministrador();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public String visualizarAdministrador() {
		if (administradorSelecionado != null) {
			return "editarAdministrador?faces-redirect=true";
		} else {
			Mensagem.make("Selecione um usuario !");
		}
		return null;
	}

	public String convertTime(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}

}
