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
	private ClasseGenerica categoria;
	private ClasseGenerica cursoSelecionado;

	public AdministradorVisualizarCursoMBean() {
		cursos = new ArrayList<ClasseGenerica>();
		classeGenericaDao = new ClasseGenericaDao();

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
			cursos = classeGenericaDao
					.buscaCurso(administradorVisualizarCategoriaMBean.getCategoriaSelecionada().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
		administradorVisualizarCategoriaMBean.setAdministradorVisualizarCursoMBean(this);
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

}
