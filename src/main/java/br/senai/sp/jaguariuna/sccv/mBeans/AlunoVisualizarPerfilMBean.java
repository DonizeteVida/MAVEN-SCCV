package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;

@ManagedBean
@ViewScoped
public class AlunoVisualizarPerfilMBean {

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private ClasseGenericaDao classeGenericaDao;

	public AlunoVisualizarPerfilMBean() {
		usuarioDao = new UsuarioDao();
		usuario = new Usuario();
		classeGenericaDao = new ClasseGenericaDao();
	}

	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	@PostConstruct
	void passarUsuario() {
		try {
			usuario = usuarioDao.buscaUsuarioPorCpf(alunoIndexMBean.getUsuario().getCpf());
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem(e.toString());
		}
	}

	public void teste() {
		System.out.println(usuario.getCpf());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private void mensagem(String s) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public ClasseGenericaDao getClasseGenericaDao() {
		return classeGenericaDao;
	}

	public void setClasseGenericaDao(ClasseGenericaDao classeGenericaDao) {
		this.classeGenericaDao = classeGenericaDao;
	}

}
