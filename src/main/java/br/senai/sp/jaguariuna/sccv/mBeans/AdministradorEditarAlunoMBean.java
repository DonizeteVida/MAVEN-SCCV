package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.uDao.AdministradorDao;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean
@ViewScoped
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

	@PostConstruct
	void postConstruct() {
		try {
			downloadUsuario();
			curriculumVitaes = curriculoDao.listarCurriculo(usuarioSelecionado.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	private void downloadUsuario() throws SQLException {
		usuarioSelecionado = usuarioDao
				.buscaUsuarioPorCpf(administradorVerPerfilAlunoMBean.getUsuarioSelecionado().getCpf());
	}

	@ManagedProperty(value = "#{administradorVerPerfilAlunoMBean}")
	AdministradorVerPerfilAlunoMBean administradorVerPerfilAlunoMBean;

	public void setAdministradorVerPerfilAlunoMBean(AdministradorVerPerfilAlunoMBean administradorVerPerfilAlunoMBean) {
		this.administradorVerPerfilAlunoMBean = administradorVerPerfilAlunoMBean;
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
				downloadUsuario();
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
			return "visualizarCurriculoAdministrador?faces-redirect=true";
		}
		return null;
	}

}
