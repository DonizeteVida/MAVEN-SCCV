package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean
@ViewScoped
public class AlunoCadastroCurriculoMBean {

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private CurriculumVitae curriculumVitae;
	private List<ClasseGenerica> cursos;
	private List<ClasseGenerica> turmas;
	private List<ClasseGenerica> categorias;
	private CurriculoDao curriculoDao;

	private ClasseGenericaDao classeGenericaDao;

	public AlunoCadastroCurriculoMBean() {
		turmas = new ArrayList<>();
		usuarioDao = new UsuarioDao();
		curriculumVitae = new CurriculumVitae();
		classeGenericaDao = new ClasseGenericaDao();
		curriculoDao = new CurriculoDao();
		try {
			categorias = classeGenericaDao.buscaCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ManagedProperty(value = "#{alunoHomeMBean}")
	private AlunoHomeMBean alunoHomeMBean;

	public void setAlunoHomeMBean(AlunoHomeMBean alunoHomeMBean) {
		this.alunoHomeMBean = alunoHomeMBean;
	}

	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}

	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
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

	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
	}

	@PostConstruct
	void post() {
		try {
			usuario = usuarioDao.buscaUsuarioPorCpf(alunoIndexMBean.getUsuario().getCpf());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void buscaCurso() {
		try {
			cursos = classeGenericaDao.buscaCurso(curriculumVitae.getCategoria().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void buscaTurma() {
		try {
			turmas = classeGenericaDao.buscaTurma(curriculumVitae.getCurso().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String salvarCurriculo() {
		try {
			if (curriculoDao.listarCurriculo(usuario.getId(), curriculumVitae.getCurso().getId()).size() == 0) {
				if (curriculoDao.criarCurriculo(curriculumVitae, usuario)) {
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					alunoHomeMBean.listarCurriculo();
					Mensagem.make("Curriculo criado com sucesso !");

					return "home?faces-redirect=true";
				}
				Mensagem.make("Falha ao salvar um novo curriculo !");

			} else {

				Mensagem.make("Já existe um curriculo com este curso !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
		return null;
	}

}
