package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.senai.sp.jaguariuna.sccv.entities.ClasseGenericaGrafico;
import br.senai.sp.jaguariuna.sccv.uDao.GraficoDao;

@ManagedBean
@ViewScoped
public class GraficoMBean {

	private PieChartModel graficoCategoria = new PieChartModel();
	private PieChartModel graficoSexo = new PieChartModel();
	GraficoDao dao;

	public GraficoMBean() {
		dao = new GraficoDao();
		createPieModelCategoria();
		createPieModelSexo();
	}

	public void createPieModelCategoria() {

		graficoCategoria = new PieChartModel();

		Map<String, Number> dados = new HashMap<>();
		List<ClasseGenericaGrafico> classeGenericaGraficos = null;
		try {
			classeGenericaGraficos = dao.quantideCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ClasseGenericaGrafico cg : classeGenericaGraficos) {
			dados.put(cg.getNome(), cg.getValor());
		}

		graficoCategoria.setData(dados);

		graficoCategoria.setTitle("Curriculos cadastrados por categoria");
		graficoCategoria.setLegendPosition("e");
		graficoCategoria.setFill(true);
		graficoCategoria.setShowDataLabels(true);
		graficoCategoria.setShadow(false);
		graficoCategoria.setDiameter(200);
	}

	public void createPieModelSexo() {

		graficoSexo = new PieChartModel();

		Map<String, Number> dados = new HashMap<>();
		List<ClasseGenericaGrafico> classeGenericaGraficos = null;
		try {
			classeGenericaGraficos = dao.quantidadeSexo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ClasseGenericaGrafico cg : classeGenericaGraficos) {
			dados.put(cg.getNome(), cg.getValor());
		}

<<<<<<< HEAD
		dados.put("Técnico em informática", 500);
		dados.put("Técnico em eletrônica", 500);
=======
		graficoSexo.setData(dados);
>>>>>>> 7fbad7e79ffbcda8a4e10268c344c2a352de61bf

		graficoSexo.setTitle("Perfis cadastrados por sexo");
		graficoSexo.setLegendPosition("e");
		graficoSexo.setFill(true);
		graficoSexo.setShowDataLabels(true);
		graficoSexo.setShadow(false);
		graficoSexo.setDiameter(200);
	}

	public PieChartModel getGraficoCategoria() {
		return graficoCategoria;
	}

	public void setGraficoCategoria(PieChartModel graficoCategoria) {
		this.graficoCategoria = graficoCategoria;
	}

	public PieChartModel getGraficoSexo() {
		return graficoSexo;
	}

	public void setGraficoSexo(PieChartModel graficoSexo) {
		this.graficoSexo = graficoSexo;
	}

}
