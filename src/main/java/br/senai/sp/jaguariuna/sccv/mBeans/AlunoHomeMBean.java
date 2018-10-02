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
	CurriculumVitae curEdit;
	private List<ClasseGenerica> cursos;
	private List<ClasseGenerica> turmas;
	private List<ClasseGenerica> categorias;

	private ClasseGenericaDao classeGenericaDao;

	private List<ClasseGenerica> cursos;
	private List<ClasseGenerica> turmas;
	private List<ClasseGenerica> categorias;

	private ClasseGenericaDao classeGenericaDao;

	public AlunoHomeMBean() {
		classeGenericaDao = new ClasseGenericaDao();
		curriculoDao = new CurriculoDao();
		listaCurriculo = new ArrayList<CurriculumVitae>();
<<<<<<< HEAD
		clicado = false;
		curClick = new CurriculumVitae();
=======
		curEdit = new CurriculumVitae();
>>>>>>> fdb699e2bbe075aa4ce7f6b2740d28217cf9e32d
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
			e.printStackTrace();
		}
	}

	public void buscaCurso() {
		try {
			cursos = classeGenericaDao.buscaCurso(curClick.getCategoria().getId());
		} catch (SQLException e) {
<<<<<<< HEAD
			// TODO Auto-generated catch block
=======
>>>>>>> fdb699e2bbe075aa4ce7f6b2740d28217cf9e32d
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void buscaTurma() {
		try {
			turmas = classeGenericaDao.buscaTurma(curClick.getCurso().getId());
		} catch (SQLException e) {
<<<<<<< HEAD
			// TODO Auto-generated catch block
=======
>>>>>>> fdb699e2bbe075aa4ce7f6b2740d28217cf9e32d
			e.printStackTrace();
			Mensagem.make(e.toString());
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
			cursos = classeGenericaDao.buscaCurso(curClick.getCategoria().getId());

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

	public List<ClasseGenerica> getCursos() {
		return cursos;
	}

	public void setCursos(List<ClasseGenerica> cursos) {
		this.cursos = cursos;
	}

	public List<ClasseGenerica> getTurmas() {
		return turmas;
	}
<<<<<<< HEAD
=======

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

<<<<<<< HEAD
	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
=======
	public void setTurmas(List<ClasseGenerica> turmas) {
		this.turmas = turmas;
	}

	public List<ClasseGenerica> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
	}

	public CurriculumVitae getCurEdit() {
		return curEdit;
	}

	public void setCurEdit(CurriculumVitae curEdit) {
		try {
			turmas = classeGenericaDao.buscaTurma(curEdit.getCurso().getId());
			cursos = classeGenericaDao.buscaCurso(curEdit.getCategoria().getId());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.curEdit = curEdit;
	}

	public void salvarCurriculo() {
		try {
			if (curriculoDao.updateCurriculum(curEdit)) {
				Mensagem.make("Curriculo alterado com sucesso !");
				listarCurriculo();
			} else {
				Mensagem.make("Erro ao alterar currículo !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
>>>>>>> fdb699e2bbe075aa4ce7f6b2740d28217cf9e32d
	}

>>>>>>> bc0e93ab894ddf123cca5690bc81a94d82de9d16
}
