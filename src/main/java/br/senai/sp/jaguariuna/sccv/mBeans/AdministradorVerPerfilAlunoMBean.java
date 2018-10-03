package br.senai.sp.jaguariuna.sccv.mBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean(eager = true)
@ViewScoped
public class AdministradorVerPerfilAlunoMBean {

	UsuarioDao usuarioDao;
	List<Usuario> listaUsuario;
	Usuario usuarioSelecionado;

	public AdministradorVerPerfilAlunoMBean() {
		usuarioDao = new UsuarioDao();
		listaUsuario = new ArrayList<Usuario>();

		try {
			listaUsuario = usuarioDao.listarUsuario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
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

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
