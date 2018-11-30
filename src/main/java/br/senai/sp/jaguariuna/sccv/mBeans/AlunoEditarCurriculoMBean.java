package br.senai.sp.jaguariuna.sccv.mBeans;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.subEntities.Experiencia;
import br.senai.sp.jaguariuna.sccv.subEntities.Formacao;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean
@ViewScoped
public class AlunoEditarCurriculoMBean {

	CurriculumVitae curriculumAtual;

	private Experiencia experienciaSelecionada;
	private Formacao formacaoSelecionada;

	private Formacao inserirFormacao;
	private Experiencia inserirExperiencia;

	private List<Experiencia> experiencias;
	private List<Formacao> formacoes;

	private CurriculoDao curriculoDao;
	private Boolean emprego_atual = false;

	public AlunoEditarCurriculoMBean() {
		experienciaSelecionada = new Experiencia();
		formacaoSelecionada = new Formacao();

		inserirExperiencia = new Experiencia();
		inserirFormacao = new Formacao();

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
				e.printStackTrace();
				Mensagem.make(e.toString());
			}
		} else {
			curriculumAtual = alunoHomeMBean.getCurClick();
			listarTudo();
		}
	}

	public void editarExperienciaM() {
		try {
			if (emprego_atual) {
				experienciaSelecionada.getData_fim().setTimeInMillis(0);
			}

			if (curriculoDao.editarExperiencia(experienciaSelecionada)) {
				emprego_atual = false;
				listarTudo();
				Mensagem.make("Experiencia alterada com sucesso !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarFormacaoM() {
		try {
			if (curriculoDao.editarFormacao(formacaoSelecionada)) {
				listarTudo();
				Mensagem.make("Forma��o alterada com sucesso !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserirExperienciaM() {
		try {
			if (emprego_atual) {
				inserirExperiencia.getData_fim().setTimeInMillis(0);
			}
			if (curriculoDao.inserirExperiencia(inserirExperiencia, curriculumAtual)) {
				emprego_atual = false;
				Mensagem.make("Experiencia salva com sucesso");
				listarTudo();
				inserirExperiencia = new Experiencia();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void deletarExperienciaM() {
		try {
			if (curriculoDao.deletarExperiencia(experienciaSelecionada)) {
				listarTudo();
				Mensagem.make("Experiencia deletada com sucesso !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void inserirFormacaoM() {
		try {
			if (curriculoDao.inserirFormacao(inserirFormacao, curriculumAtual)) {
				Mensagem.make("Formacao salva com sucesso");
				listarTudo();
				inserirFormacao = new Formacao();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void deletarFormacaoM() {
		try {
			if (curriculoDao.deletarFormacao(formacaoSelecionada)) {
				listarTudo();
				Mensagem.make("Formacao deletada com sucesso !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public CurriculumVitae getCurriculumAtual() {
		return curriculumAtual;
	}

	public void listarTudo() {
		try {
			experiencias = curriculoDao.listarExperiencias(curriculumAtual);
			formacoes = curriculoDao.listarFormacoes(curriculumAtual);

		} catch (SQLException e) {

			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public String convertTime(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(date);
	}

	public void setCurriculumAtual(CurriculumVitae curriculumAtual) {
		this.curriculumAtual = curriculumAtual;
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

	public Formacao getInserirFormacao() {
		return inserirFormacao;
	}

	public void setInserirFormacao(Formacao inserirFormacao) {
		this.inserirFormacao = inserirFormacao;
	}

	public Experiencia getInserirExperiencia() {
		return inserirExperiencia;
	}

	public void setInserirExperiencia(Experiencia inserirExperiencia) {
		this.inserirExperiencia = inserirExperiencia;
	}

	public Boolean getEmprego_atual() {
		return emprego_atual;
	}

	public void setEmprego_atual(Boolean emprego_atual) {
		this.emprego_atual = emprego_atual;
	}

}
