package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@SessionScoped
public class AdministradorVisualizarCategoriaMBean {

	private ClasseGenericaDao classeGenericaDao;
	private List<ClasseGenerica> categorias;
	private ClasseGenerica categoriaSelecionada;
	private ClasseGenerica inserirCategoria;

	public AdministradorVisualizarCategoriaMBean() {
		classeGenericaDao = new ClasseGenericaDao();
		categorias = new ArrayList<ClasseGenerica>();
		inserirCategoria = new ClasseGenerica();
		atualizaCategoria();
	}

	private AdministradorVisualizarCursoMBean administradorVisualizarCursoMBean;

	public AdministradorVisualizarCursoMBean getAdministradorVisualizarCursoMBean() {
		return administradorVisualizarCursoMBean;
	}

	public void setAdministradorVisualizarCursoMBean(
			AdministradorVisualizarCursoMBean administradorVisualizarCursoMBean) {
		this.administradorVisualizarCursoMBean = administradorVisualizarCursoMBean;
	}

	public ClasseGenericaDao getClasseGenericaDao() {
		return classeGenericaDao;
	}

	public void setClasseGenericaDao(ClasseGenericaDao classeGenericaDao) {
		this.classeGenericaDao = classeGenericaDao;
	}

	public List<ClasseGenerica> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
	}

	public ClasseGenerica getInserirCategoria() {
		return inserirCategoria;
	}

	public void setInserirCategoria(ClasseGenerica inserirCategoria) {
		this.inserirCategoria = inserirCategoria;
	}

	public void inserirCategoria() {
		try {
			if (classeGenericaDao.inserirCategoria(inserirCategoria)) {
				Mensagem.make("Inserido com sucesso !");
			}
			atualizaCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void editarCategoria() {
		try {
			if (categoriaSelecionada != null) {
				if (classeGenericaDao.updateCategoria(categoriaSelecionada)) {
					Mensagem.make("Categoria editada com sucesso !");
				}
				atualizaCategoria();
				categoriaSelecionada = null;
			} else {
				Mensagem.make("Selecione uma categoria !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public ClasseGenerica getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(ClasseGenerica categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	private void atualizaCategoria() {
		try {
			categorias = classeGenericaDao.buscaCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public String visualizarCurso() {
		if (categoriaSelecionada != null) {
			if (administradorVisualizarCursoMBean != null) {
				administradorVisualizarCursoMBean.downloadCursos();
			}
			return "administradorVisualizarCurso?faces-redirect=true";
		}
		Mensagem.make("Selecione uma categoria !");

		return null;

	}

}
