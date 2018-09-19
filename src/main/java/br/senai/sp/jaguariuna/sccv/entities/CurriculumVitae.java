package br.senai.sp.jaguariuna.sccv.entities;

import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;

public class CurriculumVitae {

	public CurriculumVitae() {
		curso = new ClasseGenerica();
		turma = new ClasseGenerica();
		status = new ClasseGenerica();
		usuario = new Usuario();
		semestre = new Integer(0);
	}

	private Integer id;
	private Integer peso;
	private Long data_criacao;
	private ClasseGenerica curso;
	private ClasseGenerica turma;
	private Integer semestre;
	private ClasseGenerica status;
	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public long getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(long data_criacao) {
		this.data_criacao = data_criacao;
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

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public ClasseGenerica getStatus() {
		return status;
	}

	public void setStatus(ClasseGenerica status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
