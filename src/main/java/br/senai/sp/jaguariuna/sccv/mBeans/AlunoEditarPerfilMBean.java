package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;
import br.senai.sp.jaguariuna.sccv.uDao.ClasseGenericaDao;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
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
	private String antigaSenha;
	private String antigaSenhaDigitada;

	public AlunoEditarPerfilMBean() {
		usuarioDao = new UsuarioDao();
		turmas = new ArrayList<ClasseGenerica>();
		cursos = new ArrayList<ClasseGenerica>();
		estados = new ArrayList<ClasseGenerica>();

		classeGenericaDao = new ClasseGenericaDao();
		try {
			cursos = classeGenericaDao.buscaCurso();
			estados = classeGenericaDao.buscaEstado();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			antigaSenha = usuario.getSenha();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagem(e.toString());
		}
	}

	public void buscaCidade() {
		try {
			cidades = classeGenericaDao.buscaCidade(usuario.getEstado().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagem(e.toString());
		}
	}

	public void buscaTurma() {
		try {
			turmas = classeGenericaDao.buscaTurma(usuario.getCurso().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagem(e.toString());
		}
	}

	public String getAntigaSenhaDigitada() {
		return antigaSenhaDigitada;
	}

	public void setAntigaSenhaDigitada(String antigaSenhaDigitada) {
		this.antigaSenhaDigitada = antigaSenhaDigitada;
	}

	public String getAntigaSenha() {
		return antigaSenha;
	}

	public void setAntigaSenha(String antigaSenha) {
		this.antigaSenha = antigaSenha;
	}

	private void mensagem(String s) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
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

	public String salvarUsuario() {
		if(antigaSenha.equals(StringToMD5.convertStringToMd5(antigaSenhaDigitada))) {
			try {
				if(usuarioDao.updateUsuario(usuario)) {
					mensagem("Usuario atualizado com sucesso !");
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					
					System.out.println(sdf.format(usuario.getIdade().getTimeInMillis()));
					
					return "visualizarPerfil?faces-redirect=true";
				}else {
					mensagem("Falha ao atualizar o usuario !");
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mensagem(e.toString());
			}
		}else {
			mensagem("As senhas digitadas são incorretas");
			return null;
		}
		return null;
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

}
