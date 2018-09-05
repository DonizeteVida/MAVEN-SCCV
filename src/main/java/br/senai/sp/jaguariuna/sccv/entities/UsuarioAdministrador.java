package br.senai.sp.jaguariuna.sccv.entities;

import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;

public class UsuarioAdministrador {
	
	public UsuarioAdministrador() {
		status = new ClasseGenerica();
	}
	
	private Integer id;
	private String nome;
	private String email;
	private String senha;
	private String nif;
	private ClasseGenerica status;
	
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
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public ClasseGenerica getStatus() {
		return status;
	}
	public void setStatus(ClasseGenerica status) {
		this.status = status;
	}
	
	

}
