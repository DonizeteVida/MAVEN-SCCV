package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@SessionScoped
public class AdministradorVisualizarCursoMBean {

	private List<ClasseGenerica> cursos;
	private ClasseGenericaDao classeGenericaDao;
	private ClasseGenerica cursoSelecionado;
	private Integer categoriaSelecionada;
	private ClasseGenerica inserirCurso;

	public AdministradorVisualizarCursoMBean() {
		cursos = new ArrayList<ClasseGenerica>();
		classeGenericaDao = new ClasseGenericaDao();
		inserirCurso = new ClasseGenerica();

	}

	@ManagedProperty("#{administradorVisualizarCategoriaMBean}")
	private AdministradorVisualizarCategoriaMBean administradorVisualizarCategoriaMBean;

	public void setAdministradorVisualizarCategoriaMBean(
			AdministradorVisualizarCategoriaMBean administradorVisualizarCategoriaMBean) {
		this.administradorVisualizarCategoriaMBean = administradorVisualizarCategoriaMBean;
	}

	@PostConstruct
	public void downloadCursos() {
		try {
			categoriaSelecionada = administradorVisualizarCategoriaMBean.getCategoriaSelecionada().getId();
			cursos = classeGenericaDao.buscaCurso(categoriaSelecionada);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
		administradorVisualizarCategoriaMBean.setAdministradorVisualizarCursoMBean(this);
	}

	public void editarCurso() {
		if (cursoSelecionado != null) {
			try {
				if (classeGenericaDao.updateCurso(cursoSelecionado)) {
					Mensagem.make("Curso editado com sucesso !");
					downloadCursos();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Mensagem.make(e.toString());
			}
		}
	}

	public void inserirCurso() {
		try {
			if (classeGenericaDao.inserirCurso(categoriaSelecionada, inserirCurso)) {
				Mensagem.make("Curso inserido com sucesso !");
				downloadCursos();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public List<ClasseGenerica> getCursos() {
		return cursos;
	}

	public void setCursos(List<ClasseGenerica> cursos) {
		this.cursos = cursos;
	}

	public ClasseGenerica getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(ClasseGenerica cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public ClasseGenerica getInserirCurso() {
		return inserirCurso;
	}

	public void setInserirCurso(ClasseGenerica inserirCurso) {
		this.inserirCurso = inserirCurso;
	}

}
