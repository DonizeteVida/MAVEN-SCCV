package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;

@ManagedBean
@ViewScoped
public class AlunoCadastroCurriculoMBean {

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private CurriculumVitae curriculumVitae;
	private List<ClasseGenerica> cursos;
	private List<ClasseGenerica> turmas;

	private ClasseGenericaDao classeGenericaDao;

	public AlunoCadastroCurriculoMBean() {
		turmas = new ArrayList<>();
		usuarioDao = new UsuarioDao();
		curriculumVitae = new CurriculumVitae();
		classeGenericaDao = new ClasseGenericaDao();
		try {
			cursos = classeGenericaDao.buscaCurso();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	@PostConstruct
	void post() {
		try {
			usuario = usuarioDao.buscaUsuarioPorCpf(alunoIndexMBean.getUsuario().getCpf());
			curriculumVitae.setUsuario(alunoIndexMBean.getUsuario());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mens(e.toString());
		}
	}

	public void buscaTurma() {
		try {
			turmas = classeGenericaDao.buscaTurma(curriculumVitae.getCurso().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String salvarCurriculo() {
		try {
			if (usuarioDao.criarCurriculo(curriculumVitae)) {
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

				return "home?faces-redirect=true";
			} else {
				mens("Falha ao salvar usuario !");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mens(e.toString());
		}
		return null;
	}

	private void mens(String s) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}

	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}

	public List<ClasseGenerica> getCursos() {
		return cursos;
	}

	public void setCursos(List<ClasseGenerica> cursos) {
		this.cursos = cursos;
	}

	public List<ClasseGenerica> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<ClasseGenerica> turmas) {
		this.turmas = turmas;
	}

}
