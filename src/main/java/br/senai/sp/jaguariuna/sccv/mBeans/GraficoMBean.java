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

	private PieChartModel grafico;

	@PostConstruct
	public void init() throws SQLException {
		createGrafico();
	}

	public PieChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(PieChartModel grafico) {
		this.grafico = grafico;
	}

	public void createGrafico() throws SQLException {

		createPieModel();

	}

	public void createPieModel() throws SQLException {

		grafico = new PieChartModel();

		Map<String, Number> dados = new HashMap<>();
		GraficoDao dao = new GraficoDao();
		List<ClasseGenericaGrafico> classeGenericaGraficos = dao.quantideCategoria();
		for (ClasseGenericaGrafico cg : classeGenericaGraficos) {
			dados.put(cg.getNome(), cg.getValor());
		}

		grafico.setData(dados);

		grafico.setTitle("Curriclos cadastrados por categoria");
		grafico.setLegendPosition("e");
		grafico.setFill(true);
		grafico.setShowDataLabels(true);
		grafico.setShadow(false);
		grafico.setDiameter(200);
	}
}
