package br.senai.sp.jaguariuna.sccv.mBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@SessionScoped
public class AdministradorVerPerfilAlunoMBean {

	UsuarioDao usuarioDao;
	List<Usuario> listaUsuario;
	Usuario usuarioSelecionado;
	private String filtro;
	private AdministradorEditarAlunoMBean administradorEditarAlunoMBean;

	public AdministradorVerPerfilAlunoMBean() {
		usuarioDao = new UsuarioDao();
		listaUsuario = new ArrayList<Usuario>();
		usuarioSelecionado = new Usuario();

		atualizaListaUsuario();
	}

	public void setAdministradorEditarAlunoMBean(AdministradorEditarAlunoMBean administradorEditarAlunoMBean) {
		this.administradorEditarAlunoMBean = administradorEditarAlunoMBean;
	}

	public void buscaFiltro() {
		try {

			if (filtro.length() > 0 && Character.isDigit(filtro.charAt(0))) {
				listaUsuario = usuarioDao.listarUsuarioCpf(filtro);
			} else {
				listaUsuario = usuarioDao.listarUsuario(filtro);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public AdministradorEditarAlunoMBean getAdministradorEditarAlunoMBean() {
		return administradorEditarAlunoMBean;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
		if (administradorEditarAlunoMBean != null) {
			try {
				administradorEditarAlunoMBean.downloadUsuario();
				administradorEditarAlunoMBean.downloadListaCurriculo();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void atualizaListaUsuario() {
		try {
			listaUsuario = usuarioDao.listarUsuario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public String visualizarAluno() {
		if (usuarioSelecionado != null) {
			return "administradorEditarPerfilAluno?faces-redirect=true";
		} else {
			Mensagem.make("Selecione um usuario !");
		}
		return null;
	}

	public String convertTime(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
}
