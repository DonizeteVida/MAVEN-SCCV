package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.entities.Curriculo;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;

@ManagedBean
@ViewScoped
public class AlunoHomeMBean {
	
	CurriculoDao curriculoDao;
	List<Curriculo> listarCurriculo;
	
	public AlunoHomeMBean() {
		curriculoDao = new CurriculoDao();
		listarCurriculo = new ArrayList<Curriculo>();
		System.out.println("Pegou 65465465");

	}
	
	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
		System.out.println("Pegou usuario");
	}
	
	@PostConstruct
	public void postConstruct() {
		Usuario u = alunoIndexMBean.getUsuario();
		try {
			listarCurriculo = curriculoDao.listarCurriculo(u.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listarCurriculo.size());
		System.out.println("Teste");
	}

	public List<Curriculo> getListarCurriculo() {
		return listarCurriculo;
	}

	public void setListarCurriculo(List<Curriculo> listarCurriculo) {
		this.listarCurriculo = listarCurriculo;
	}

}
