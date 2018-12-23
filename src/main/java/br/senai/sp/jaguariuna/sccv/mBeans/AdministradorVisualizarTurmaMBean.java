package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean
@ViewScoped
public class AdministradorVisualizarTurmaMBean {

	private ClasseGenerica cursoSelecionado;

	private ClasseGenerica inserirTurma;
	private ClasseGenerica turmaSelecionada;
	private ClasseGenericaDao classeGenericaDao;
	private List<ClasseGenerica> turmas;

	public AdministradorVisualizarTurmaMBean() {
		inserirTurma = new ClasseGenerica();
		classeGenericaDao = new ClasseGenericaDao();
		turmaSelecionada = new ClasseGenerica();
	}

	@ManagedProperty("#{administradorVisualizarCursoMBean}")
	private AdministradorVisualizarCursoMBean administradorVisualizarCursoMBean;

	public void setAdministradorVisualizarCursoMBean(
			AdministradorVisualizarCursoMBean administradorVisualizarCursoMBean) {
		this.administradorVisualizarCursoMBean = administradorVisualizarCursoMBean;
	}

	@PostConstruct
	public void downloadCursos() {
		cursoSelecionado = administradorVisualizarCursoMBean.getCursoSelecionado();
		try {
			turmas = classeGenericaDao.buscaTurma(cursoSelecionado.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void inserirTurma() {
		try {
			if (classeGenericaDao.inserirTurma(cursoSelecionado.getId(), inserirTurma)) {
				Mensagem.make("Turma inserida com sucesso !");
				downloadCursos();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void editarTurma() {
		try {
			if (classeGenericaDao.updateTurma(turmaSelecionada)) {
				Mensagem.make("Turma editada com sucesso !");
				downloadCursos();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public ClasseGenerica getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(ClasseGenerica cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public ClasseGenerica getInserirTurma() {
		return inserirTurma;
	}

	public void setInserirTurma(ClasseGenerica inserirTurma) {
		this.inserirTurma = inserirTurma;
	}

	public ClasseGenerica getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(ClasseGenerica turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public List<ClasseGenerica> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<ClasseGenerica> turmas) {
		this.turmas = turmas;
	}

}
