package br.senai.sp.jaguariuna.sccv.mBeans;

import java.io.IOException;
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
import br.senai.sp.jaguariuna.sccv.subEntities.Experiencia;
import br.senai.sp.jaguariuna.sccv.subEntities.Formacao;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;

@ManagedBean
@ViewScoped
public class AlunoEditarCurriculoMBean {

	CurriculumVitae curriculumAtual;

	private Experiencia experienciaSelecionada;
	private Formacao formacaoSelecionada;

	private List<Experiencia> experiencias;
	private List<Formacao> formacoes;

	private CurriculoDao curriculoDao;

	public AlunoEditarCurriculoMBean() {
		experienciaSelecionada = new Experiencia();
		formacaoSelecionada = new Formacao();
		curriculoDao = new CurriculoDao();
		experiencias = new ArrayList<>();
		formacoes = new ArrayList<>();
	}

	@ManagedProperty(value = "#{alunoHomeMBean}")
	private AlunoHomeMBean alunoHomeMBean;

	public void setAlunoHomeMBean(AlunoHomeMBean alunoHomeMBean) {
		this.alunoHomeMBean = alunoHomeMBean;
	}

	@PostConstruct
	public void postConstruct() {
		if (alunoHomeMBean.getCurClick() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			curriculumAtual = alunoHomeMBean.getCurClick();
			try {
				experiencias = curriculoDao.listarExperiencias(curriculumAtual);
				formacoes = curriculoDao.listarFormacoes(curriculumAtual);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mens(e.toString());
			}
		}
	}

	public CurriculumVitae getCurriculumAtual() {
		return curriculumAtual;
	}

	public void setCurriculumAtual(CurriculumVitae curriculumAtual) {
		this.curriculumAtual = curriculumAtual;
	}

	private void mens(String s) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
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

	public Experiencia getExperienciaSelecionada() {
		return experienciaSelecionada;
	}

	public void setExperienciaSelecionada(Experiencia experienciaSelecionada) {
		this.experienciaSelecionada = experienciaSelecionada;
	}

	public Formacao getFormacaoSelecionada() {
		return formacaoSelecionada;
	}

	public void setFormacaoSelecionada(Formacao formacaoSelecionada) {
		this.formacaoSelecionada = formacaoSelecionada;
	}

}
