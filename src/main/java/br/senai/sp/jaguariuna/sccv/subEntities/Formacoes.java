package br.senai.sp.jaguariuna.sccv.subEntities;

import java.util.Calendar;

public class Formacoes {
	
	public Formacoes(){
		tipoformacao = new ClasseGenerica();
	}
	
	private Integer id;
	private String nome;
	private Calendar data_inicio;
	private Calendar data_fim;
	private ClasseGenerica tipoformacao;
	
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
	public ClasseGenerica getTipoformacao() {
		return tipoformacao;
	}
	public void setTipoformacao(ClasseGenerica tipoformacao) {
		this.tipoformacao = tipoformacao;
	}
	
	
	
}
