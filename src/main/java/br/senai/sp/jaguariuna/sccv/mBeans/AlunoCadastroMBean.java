package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;

@ManagedBean
@ViewScoped
public class AlunoCadastroMBean {

	// classe usada para buscar classes responsaveis por foreign key
	ClasseGenericaDao classeGenericaDao;
	List<ClasseGenerica> cursos;
	List<ClasseGenerica> turmas;
	List<ClasseGenerica> estados;
	List<ClasseGenerica> cidades;
	List<ClasseGenerica> categorias;
	List<ClasseGenerica> sexos;
	// usuario a ser salvo no banco
	Usuario usuario;
	UsuarioDao usuarioDao;

	public AlunoCadastroMBean() {
		classeGenericaDao = new ClasseGenericaDao();
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		try {
			estados = classeGenericaDao.buscaEstado();
			categorias = classeGenericaDao.buscaCategoria();
			sexos = classeGenericaDao.buscaSexo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}

	}

	public void buscaCurso() {
		try {
			cursos = classeGenericaDao.buscaCurso(usuario.getCategoria().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void buscaTurma() {
		try {
			turmas = classeGenericaDao.buscaTurma(usuario.getCurso().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public void buscaCidade() {
		try {
			cidades = classeGenericaDao.buscaCidade(usuario.getEstado().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public List<ClasseGenerica> getCidades() {
		return cidades;
	}

	public void setCidades(List<ClasseGenerica> cidades) {
		this.cidades = cidades;
	}

	public List<ClasseGenerica> getEstados() {
		return estados;
	}

	public List<ClasseGenerica> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
	}

	public void setEstados(List<ClasseGenerica> estados) {
		this.estados = estados;
	}

	public List<ClasseGenerica> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<ClasseGenerica> turmas) {
		this.turmas = turmas;
	}

	public List<ClasseGenerica> getCursos() {
		return cursos;
	}

	public void setCursos(List<ClasseGenerica> cursos) {
		this.cursos = cursos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ClasseGenerica> getSexos() {
		return sexos;
	}

	public void setSexos(List<ClasseGenerica> sexos) {
		this.sexos = sexos;
	}

	public String salvarUsuario() {
		try {
			if (usuarioDao.buscaUsuarioPorEmail(usuario.getEmail()) == null) {
				if (usuarioDao.buscaUsuarioPorCpf(usuario.getCpf()) == null) {
					if (usuarioDao.inserirUsuario(usuario)) {
						Mensagem.make("Usuario salvo com sucesso !!! ");
						FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

						return "index?faces-redirect=true";
					}
				} else {
					Mensagem.make("Já existe um usuário cadastrado com este CPF !!! ");
				}
			} else {
				Mensagem.make("Já existe um usuário cadastrado com este e-mail !!! ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
		return null;
	}

}
