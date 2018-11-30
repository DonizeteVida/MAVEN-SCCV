package br.senai.sp.jaguariuna.sccv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "cutString", forClass = String.class)
public class CutString implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		return value != null ? value : "";
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		String palavra = value.toString();
		if (palavra.length() > 15) {
			palavra = palavra.substring(0, 15);
			palavra += "...";
		}
		return palavra;
	}

}
