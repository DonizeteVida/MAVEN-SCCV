package br.senai.sp.jaguariuna.sccv.mBeans;

<<<<<<< HEAD
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AdministradorVerPerfilAlunoMBean {

=======
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

>>>>>>> fdb699e2bbe075aa4ce7f6b2740d28217cf9e32d
}
