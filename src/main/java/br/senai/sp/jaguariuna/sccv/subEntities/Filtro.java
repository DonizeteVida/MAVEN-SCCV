package br.senai.sp.jaguariuna.sccv.subEntities;

import java.util.List;

public class Filtro {

	private Integer id;
	private Integer idCurso; // vai
	private Integer semestre; // vai
	private List<Integer> idCidade; // vai
	private Integer experiencia; // 0=indiferente 1=SIM 2= N�O
	private Integer sexo; // vai 0=indiferente 1=MACHO 2= MULHER
	private boolean idioma;// vai
	private Integer qualIdioma;// vai 0=indiferente 1=INGLES 2= ESPANHOL
	private Integer deficiencia;// vai 0=indiferente 1=SIM 2= N�O

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public List<Integer> getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(List<Integer> idCidade) {
		this.idCidade = idCidade;
	}

	public Integer getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public boolean isIdioma() {
		return idioma;
	}

	public void setIdioma(boolean idioma) {
		this.idioma = idioma;
	}

	public Integer getQualIdioma() {
		return qualIdioma;
	}

	public void setQualIdioma(Integer qualIdioma) {
		this.qualIdioma = qualIdioma;
	}

	public Integer getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(Integer deficiencia) {
		this.deficiencia = deficiencia;
	}

}
