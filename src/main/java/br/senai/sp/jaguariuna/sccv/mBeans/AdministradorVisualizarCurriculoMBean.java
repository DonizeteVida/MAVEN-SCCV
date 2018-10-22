package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.subEntities.Experiencia;
import br.senai.sp.jaguariuna.sccv.subEntities.Formacao;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;

@ManagedBean
@ViewScoped
public class AdministradorVisualizarCurriculoMBean {

	private CurriculumVitae curClick;
	private List<Experiencia> experiencias;
	private List<Formacao> formacoes;
	private CurriculoDao curriculoDao;

	public AdministradorVisualizarCurriculoMBean() {
		curriculoDao = new CurriculoDao();
	}

	@ManagedProperty(value = "#{administradorEditarAlunoMBean}")
	private AdministradorEditarAlunoMBean administradorEditarAlunoMBean;

	public void setAdministradorEditarAlunoMBean(AdministradorEditarAlunoMBean administradorEditarAlunoMBean) {
		this.administradorEditarAlunoMBean = administradorEditarAlunoMBean;
	}

	@PostConstruct
	private void postConstruct() {
		curClick = administradorEditarAlunoMBean.getCurClick();
		try {
			formacoes = curriculoDao.listarFormacoes(curClick);
			experiencias = curriculoDao.listarExperiencias(curClick);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CurriculumVitae getCurClick() {
		return curClick;
	}

	public void setCurClick(CurriculumVitae curClick) {
		this.curClick = curClick;
	}

	public List<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	public List<Formacao> getFormacoes() {
		return formacoes;
	}

	public void setFormacoes(List<Formacao> formacoes) {
		this.formacoes = formacoes;
	}

}
