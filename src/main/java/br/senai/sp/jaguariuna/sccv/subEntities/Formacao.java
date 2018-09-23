package br.senai.sp.jaguariuna.sccv.subEntities;

import java.util.Calendar;

public class Formacao {

	public Formacao() {
	}

	public Formacao(Integer id, String nome, Calendar data_inicio, Calendar data_fim, Integer id_curriculum_vitae) {
		this.id = id;
		this.nome = nome;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.id_curriculum_vitae = id_curriculum_vitae;
	}

	private Integer id;
	private String nome;
	private Calendar data_inicio;
	private Calendar data_fim;
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

}
