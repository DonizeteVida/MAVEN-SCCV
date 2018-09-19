package br.senai.sp.jaguariuna.sccv.entities;

import java.util.Calendar;

import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;

public class Usuario {

	public Usuario() {
		status = new ClasseGenerica();
		curso = new ClasseGenerica();
		turma = new ClasseGenerica();
		cidade = new ClasseGenerica();
		estado = new ClasseGenerica();
		categoria = new ClasseGenerica();
		idade = Calendar.getInstance();
	}

	private Integer id;
	private String nome;
	private String email;
	private String senha;
	private Calendar idade;
	private String cpf;
	private String rg;
	private ClasseGenerica status;
	private ClasseGenerica curso;
	private ClasseGenerica turma;
	private ClasseGenerica cidade;
	private ClasseGenerica estado;
	private ClasseGenerica categoria;

	public ClasseGenerica getCategoria() {
		return categoria;
	}

	public void setCategoria(ClasseGenerica categoria) {
		this.categoria = categoria;
	}

	public ClasseGenerica getEstado() {
		return estado;
	}

	public void setEstado(ClasseGenerica estado) {
		this.estado = estado;
	}

	public ClasseGenerica getCidade() {
		return cidade;
	}

	public void setCidade(ClasseGenerica cidade) {
		this.cidade = cidade;
	}

	public ClasseGenerica getStatus() {
		return status;
	}

	public void setStatus(ClasseGenerica status) {
		this.status = status;
	}

	public ClasseGenerica getCurso() {
		return curso;
	}

	public void setCurso(ClasseGenerica curso) {
		this.curso = curso;
	}

	public ClasseGenerica getTurma() {
		return turma;
	}

	public void setTurma(ClasseGenerica turma) {
		this.turma = turma;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getIdade() {
		return idade;
	}

	public void setIdade(Calendar idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

}
