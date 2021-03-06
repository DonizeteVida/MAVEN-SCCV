package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.Mensagem;
import br.senai.sp.jaguariuna.sccv.utils.StringToMD5;

@ManagedBean
@ViewScoped
public class AlunoEditarPerfilMBean {

	private ClasseGenericaDao classeGenericaDao;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private List<ClasseGenerica> cursos;
	private List<ClasseGenerica> turmas;
	private List<ClasseGenerica> estados;
	private List<ClasseGenerica> cidades;
	private List<ClasseGenerica> categorias;
	private List<ClasseGenerica> sexos;
	private String antigaSenhaDigitada;
	private String antigaSenhaUsuario;

	public AlunoEditarPerfilMBean() {
		usuarioDao = new UsuarioDao();
		turmas = new ArrayList<ClasseGenerica>();
		cursos = new ArrayList<ClasseGenerica>();
		estados = new ArrayList<ClasseGenerica>();
		categorias = new ArrayList<>();

		classeGenericaDao = new ClasseGenericaDao();
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

	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	@PostConstruct
	void passarUsuario() {
		try {
			usuario = usuarioDao.buscaUsuarioPorCpf(alunoIndexMBean.getUsuario().getCpf());
			turmas = classeGenericaDao.buscaTurma(usuario.getCurso().getId());
			cidades = classeGenericaDao.buscaCidade(usuario.getEstado().getId());
			cursos = classeGenericaDao.buscaCurso(usuario.getCategoria().getId());
			antigaSenhaUsuario = usuario.getSenha();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public List<ClasseGenerica> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
	}

	public String getAntigaSenhaDigitada() {
		return antigaSenhaDigitada;
	}

	public void setAntigaSenhaDigitada(String antigaSenhaDigitada) {
		this.antigaSenhaDigitada = antigaSenhaDigitada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<ClasseGenerica> getCidades() {
		return cidades;
	}

	public void setCidades(List<ClasseGenerica> cidades) {
		this.cidades = cidades;
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

	public List<ClasseGenerica> getEstados() {
		return estados;
	}

	public void setEstados(List<ClasseGenerica> estados) {
		this.estados = estados;
	}

	public String salvarUsuario() {
		if (usuario.getSenha().equals(StringToMD5.convertStringToMd5(antigaSenhaDigitada))) {
			usuario.setSenha(antigaSenhaDigitada);
			try {
				if (usuarioDao.updateUsuario(usuario)) {
					Mensagem.make("Usuario atualizado com sucesso !");
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					alunoIndexMBean.setUsuario(usuario);
					return "visualizarPerfil?faces-redirect=true";
				}
				Mensagem.make("Falha ao atualizar o usuario !");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Mensagem.make(e.toString());
			}
		} else {
			Mensagem.make("Digite corretamente a antiga senha !");
		}
		return null;
	}

	public String salvarSenha() {
		if (antigaSenhaUsuario.equals(StringToMD5.convertStringToMd5(antigaSenhaDigitada))) {
			try {
				if (usuarioDao.updateUsuario(usuario)) {
					Mensagem.make("Senha atualizada com sucesso !");
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					alunoIndexMBean.setUsuario(usuario);
					return "visualizarPerfil?faces-redirect=true";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Mensagem.make(e.toString());
			}
		} else {
			Mensagem.make("Antiga senha incorreta !");
		}
		return "";
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

	public void buscaCidade() {
		try {
			cidades = classeGenericaDao.buscaCidade(usuario.getEstado().getId());
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

}
