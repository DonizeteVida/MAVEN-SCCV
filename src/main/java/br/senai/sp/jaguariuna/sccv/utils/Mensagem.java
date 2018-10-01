package br.senai.sp.jaguariuna.sccv.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {

	public static void make(String s) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
	}

}
