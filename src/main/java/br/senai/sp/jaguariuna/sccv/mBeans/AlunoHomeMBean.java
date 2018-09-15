package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.uDao.CurriculoDao;

@ManagedBean
@ViewScoped
public class AlunoHomeMBean {

	CurriculoDao curriculoDao;
	List<CurriculumVitae> listaCurriculo;

	public AlunoHomeMBean() {
		curriculoDao = new CurriculoDao();
		listaCurriculo = new ArrayList<CurriculumVitae>();
	}

	@ManagedProperty(value = "#{alunoIndexMBean}")
	private AlunoIndexMBean alunoIndexMBean;

	public void setAlunoIndexMBean(AlunoIndexMBean alunoIndexMBean) {
		this.alunoIndexMBean = alunoIndexMBean;
	}

	@PostConstruct
	public void postConstruct() {
		try {
			listaCurriculo = curriculoDao.listarCurriculo(alunoIndexMBean.getUsuario().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<CurriculumVitae> getListaCurriculo() {
		return listaCurriculo;
	}

	public void setListaCurriculo(List<CurriculumVitae> listaCurriculo) {
		this.listaCurriculo = listaCurriculo;
	}

}
