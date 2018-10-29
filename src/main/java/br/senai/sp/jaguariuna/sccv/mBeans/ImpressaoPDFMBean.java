package br.senai.sp.jaguariuna.sccv.mBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;

@ManagedBean
@ViewScoped
public class ImpressaoPDFMBean {

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private CurriculumVitae curriculumVitae;
	private List<ClasseGenerica> classeGenerica;

	public ImpressaoPDFMBean() {
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		curriculumVitae = new CurriculumVitae();
		classeGenerica = new ArrayList<ClasseGenerica>();
	}

	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}

	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}

	public List<ClasseGenerica> getClasseGenerica() {
		return classeGenerica;
	}

	public void setClasseGenerica(List<ClasseGenerica> classeGenerica) {
		this.classeGenerica = classeGenerica;
	}
	
	

}
