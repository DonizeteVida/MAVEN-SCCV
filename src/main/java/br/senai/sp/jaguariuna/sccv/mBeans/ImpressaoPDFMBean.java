package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.subEntities.Experiencia;
import br.senai.sp.jaguariuna.sccv.subEntities.Formacao;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;

@ManagedBean
@ViewScoped
public class ImpressaoPDFMBean {

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private CurriculumVitae curriculumVitae;
	private List<Formacao> formacao;
	private List<Experiencia> experiencia;
	private CurriculoDao curriculoDao;

	public ImpressaoPDFMBean() {
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		curriculumVitae = new CurriculumVitae();
		curriculoDao = new CurriculoDao();
	}

	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	@ManagedProperty(value = "#{alunoHomeMBean}")
	private AlunoHomeMBean alunoHomeMBean;

	public void setAlunoHomeMBean(AlunoHomeMBean alunoHomeMBean) {
		this.alunoHomeMBean = alunoHomeMBean;
	}

	@PostConstruct
	public void postConstrut() {
		usuario = alunoIndexMBean.getUsuario();
		curriculumVitae = alunoHomeMBean.getCurClick();
		try {
			formacao = curriculoDao.listarFormacoes(curriculumVitae);
			experiencia = curriculoDao.listarExperiencias(curriculumVitae);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}

	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}

	public List<Formacao> getFormacao() {
		return formacao;
	}

	public void setFormacao(List<Formacao> formacao) {
		this.formacao = formacao;
	}

	public List<Experiencia> getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(List<Experiencia> experiencia) {
		this.experiencia = experiencia;
	}

	public String convertTime(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(date);
	}
}
