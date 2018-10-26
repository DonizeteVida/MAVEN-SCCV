package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@SessionScoped
public class AdministradorEditarAlunoMBean {

	private Usuario usuarioSelecionado;
	private UsuarioDao usuarioDao;
	private AdministradorDao administradorDao;
	private CurriculoDao curriculoDao;
	private List<CurriculumVitae> curriculumVitaes;
	private CurriculumVitae curClick;

	public AdministradorEditarAlunoMBean() {
		usuarioSelecionado = new Usuario();
		usuarioDao = new UsuarioDao();
		administradorDao = new AdministradorDao();
		curriculoDao = new CurriculoDao();
	}

	@ManagedProperty(value = "#{administradorVerPerfilAlunoMBean}")
	private AdministradorVerPerfilAlunoMBean administradorVerPerfilAlunoMBean;

	public void setAdministradorVerPerfilAlunoMBean(AdministradorVerPerfilAlunoMBean administradorVerPerfilAlunoMBean) {
		this.administradorVerPerfilAlunoMBean = administradorVerPerfilAlunoMBean;
	}

	@PostConstruct
	void postConstruct() {
		try {
			downloadUsuario();
			downloadListaCurriculo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void downloadUsuario() throws SQLException {
		usuarioSelecionado = usuarioDao
				.buscaUsuarioPorCpf(administradorVerPerfilAlunoMBean.getUsuarioSelecionado().getCpf());
		if (administradorVerPerfilAlunoMBean.getAdministradorEditarAlunoMBean() == null) {
			administradorVerPerfilAlunoMBean.setAdministradorEditarAlunoMBean(this);
		}
	}

	public void downloadListaCurriculo() throws SQLException {
		curriculumVitaes = curriculoDao.listarCurriculo(usuarioSelecionado.getId());
	}

	public AdministradorDao getAdministradorDao() {
		return administradorDao;
	}

	public void setAdministradorDao(AdministradorDao administradorDao) {
		this.administradorDao = administradorDao;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public List<CurriculumVitae> getCurriculumVitaes() {
		return curriculumVitaes;
	}

	public void setCurriculumVitaes(List<CurriculumVitae> curriculumVitaes) {
		this.curriculumVitaes = curriculumVitaes;
	}

	public CurriculumVitae getCurClick() {
		return curClick;
	}

	public void setCurClick(CurriculumVitae curClick) {
		this.curClick = curClick;
	}

	public String updateUsuario() {
		try {
			if (usuarioDao.updateUsuarioModoAdministrador(usuarioSelecionado)) {
				administradorVerPerfilAlunoMBean.atualizaListaUsuario();
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				Mensagem.make("Aluno alterado com sucesso !");
				return "administradorVerPerfilAluno?faces-redirect=true";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String visualizarCurriculo() {
		if (curClick != null) {
			return "administradorVisualizarCurriculo?faces-redirect=true";
		}
		return null;
	}

	public void alterarStatus() {
		ClasseGenerica a = curClick.getStatus();
		a.setId(a.getId() == 1 ? 2 : 1);
		curClick.setStatus(a);
		try {
			curriculoDao.updateCurriculum(curClick);
			downloadListaCurriculo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make("O status foi alterado com sucesso !");
		}
	}

}
