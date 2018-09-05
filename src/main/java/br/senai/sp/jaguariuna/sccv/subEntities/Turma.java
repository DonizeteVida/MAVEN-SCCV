package br.senai.sp.jaguariuna.sccv.subEntities;

public class Turma extends ClasseGenerica {
	
	public Turma() {
		curso = new ClasseGenerica();
	}

	private ClasseGenerica curso;

	public ClasseGenerica getCurso() {
		return curso;
	}

	public void setCurso(ClasseGenerica curso) {
		this.curso = curso;
	}
		
	
}
