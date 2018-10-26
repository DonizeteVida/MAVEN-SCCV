package br.senai.sp.jaguariuna.sccv.mBeans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class GraficoMBean {

	private PieChartModel grafico;

	@PostConstruct
	public void init() {
		createGrafico();
	}

	public PieChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(PieChartModel grafico) {
		this.grafico = grafico;
	}

	public void createGrafico() {
		createPieModel();
	}

	public void createPieModel() {

		grafico = new PieChartModel();

		Map<String, Number> dados = new HashMap<>();

		dados.put("Técnico em informática", 500);
		dados.put("Técnico em eletrônica", 500);

		grafico.setData(dados);

		grafico.setTitle("Curriclos cadastrados");
		grafico.setLegendPosition("e");
		grafico.setFill(true);
		grafico.setShowDataLabels(true);
		grafico.setShadow(false);
		grafico.setDiameter(200);
	}
}
