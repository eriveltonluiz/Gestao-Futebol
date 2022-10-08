package br.com.erivelton.canchafut.geral;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Mensagens extends FacesContext implements Serializable{

	private static final long serialVersionUID = 1L;

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public static void sucesso() {
		msgInfo("operação realizada com sucesso");
	}
	
	private static boolean facesContextValido() {
		return getFacesContext() !=null;
	}
	
	public static void msgWarn(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}
	}
	
	public static void msgFatal(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		}
	}
	
	public static void msgError(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}
	
	public static void msgInfo(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
	}
}
