package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@SessionScoped
public class AlunoHomeMBean {

	CurriculoDao curriculoDao;
	List<CurriculumVitae> listaCurriculo;

	CurriculumVitae curClick;
	Boolean clicado;

	private List<ClasseGenerica> cursos;
	private List<ClasseGenerica> turmas;
	private List<ClasseGenerica> categorias;

	private ClasseGenericaDao classeGenericaDao;

	public AlunoHomeMBean() {
		classeGenericaDao = new ClasseGenericaDao();
		curriculoDao = new CurriculoDao();
		listaCurriculo = new ArrayList<CurriculumVitae>();
		clicado = false;
		curClick = new CurriculumVitae();
		try {
			categorias = classeGenericaDao.buscaCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	@PostConstruct
	public void postConstruct() {
		listarCurriculo();
	}

	public void listarCurriculo() {
		try {
			listaCurriculo = curriculoDao.listarCurriculo(alunoIndexMBean.getUsuario().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void buscaCurso() {
		try {
			cursos = classeGenericaDao.buscaCurso(curClick.getCategoria().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void buscaTurma() {
		try {
			turmas = classeGenericaDao.buscaTurma(curClick.getCurso().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<CurriculumVitae> getListaCurriculo() {
		return listaCurriculo;
	}

	public void setListaCurriculo(List<CurriculumVitae> listaCurriculo) {
		this.listaCurriculo = listaCurriculo;
	}

	public CurriculumVitae getCurClick() {
		return curClick;
	}

	public void setCurClick(CurriculumVitae curClick) {
		try {
			turmas = classeGenericaDao.buscaTurma(curClick.getCurso().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
		this.curClick = curClick;
	}

	public String visualizarCurriculo() {
		if (curClick != null) {
			return "editarCurriculo?faces-redirect=true";
		} else {
			Mensagem.make("Selecione um curriculo");
		}
		return null;
	}

	public String abrirEditar() {
		return null;
	}

	public Boolean getClicado() {
		return clicado;
	}

	public void setClicado(Boolean clicado) {
		this.clicado = clicado;
	}

	public void salvarCurriculo() {

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

	public List<ClasseGenerica> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
	}

}
