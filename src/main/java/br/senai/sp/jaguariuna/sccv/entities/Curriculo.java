package br.senai.sp.jaguariuna.sccv.entities;

import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;

public class Curriculo {

	public Curriculo() {
		turma = new ClasseGenerica();
		curso = new ClasseGenerica();
	}

	private ClasseGenerica turma;
	private ClasseGenerica curso;
	private Integer data_criacao;

	public ClasseGenerica getTurma() {
		return turma;
	}

	public void setTurma(ClasseGenerica turma) {
		this.turma = turma;
	}

	public ClasseGenerica getCurso() {
		return curso;
	}

	public void setCurso(ClasseGenerica curso) {
		this.curso = curso;
	}

	public Integer getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Integer data_criacao) {
		this.data_criacao = data_criacao;
	}

}
