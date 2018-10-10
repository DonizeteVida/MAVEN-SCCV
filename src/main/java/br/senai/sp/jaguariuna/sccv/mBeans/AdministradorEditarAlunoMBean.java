package br.senai.sp.jaguariuna.sccv.mBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;

@ManagedBean
@ViewScoped
public class AdministradorEditarAlunoMBean {

	private Usuario usuarioSelecionado;
	private UsuarioDao usuarioDao;
	private List<Usuario> usuario;
	private AdministradorDao administradorDao;

	public AdministradorEditarAlunoMBean() {
		usuarioSelecionado = new Usuario();
		usuarioDao = new UsuarioDao();
		administradorDao = new AdministradorDao();
		usuario = new ArrayList<>();
	}

	@PostConstruct
	void postConstruct() {
		usuarioSelecionado = administradorVerPerfilAlunoMBean.getUsuarioSelecionado();
	}

	@ManagedProperty(value = "#{administradorVerPerfilAlunoMBean}")
	AdministradorVerPerfilAlunoMBean administradorVerPerfilAlunoMBean;

	public void setAdministradorVerPerfilAlunoMBean(AdministradorVerPerfilAlunoMBean administradorVerPerfilAlunoMBean) {
		this.administradorVerPerfilAlunoMBean = administradorVerPerfilAlunoMBean;
	}

	public AdministradorDao getAdministradorDao() {
		return administradorDao;
	}

	public void setAdministradorDao(AdministradorDao administradorDao) {
		this.administradorDao = administradorDao;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

}