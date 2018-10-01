package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@SessionScoped
public class AlunoHomeMBean {

	CurriculoDao curriculoDao;
	List<CurriculumVitae> listaCurriculo;

	CurriculumVitae curClick;
	Boolean clicado;

	public AlunoHomeMBean() {
		curriculoDao = new CurriculoDao();
		listaCurriculo = new ArrayList<CurriculumVitae>();
		clicado = false;
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
}
