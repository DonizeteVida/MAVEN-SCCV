package br.senai.sp.jaguariuna.sccv.subEntities;

import java.util.Calendar;

public class Experiencia {

	public Experiencia() {
		data_fim = Calendar.getInstance();
		data_inicio = Calendar.getInstance();
	}

	public Experiencia(Integer id, String nome, Calendar data_inicio, Calendar data_fim, String cargo, String empresa,
			String funcoes, Integer id_curriculum_vitae) {
		this.id = id;
		this.nome = nome;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.cargo = cargo;
		this.empresa = empresa;
		this.funcoes = funcoes;
		this.id_curriculum_vitae = id_curriculum_vitae;
	}

	private Integer id;
	private String nome;
	private Calendar data_inicio;
	private Calendar data_fim;
	private String cargo;
	private String empresa;
	private String funcoes;
	private Integer id_curriculum_vitae;

	public Integer getId_curriculum_vitae() {
		return id_curriculum_vitae;
	}

	public void setId_curriculum_vitae(Integer id_curriculum_vitae) {
		this.id_curriculum_vitae = id_curriculum_vitae;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Calendar data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Calendar getData_fim() {
		return data_fim;
	}

	public void setData_fim(Calendar data_fim) {
		this.data_fim = data_fim;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(String funcoes) {
		this.funcoes = funcoes;
	}

}
