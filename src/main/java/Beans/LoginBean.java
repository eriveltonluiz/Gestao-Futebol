package Beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import Daos.controller.DaoControllerLogin;
import Geral.Mensagens;
import Model.Login;

@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Login login = new Login();
	
	@Inject
	private DaoControllerLogin daoControllerLogin;

	@PostConstruct
	public void setar() {
		login = new Login();
	}
	
	public void logar() {
		if (daoControllerLogin.returnUsuario(login.getUsuario(), login.getSenha()) != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext context = facesContext.getExternalContext();
			context.getSessionMap().put("usuarioLogado", login);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(context.getRequestContextPath() + "/cadastro-clientes.jsf");
			} catch (IOException e) {
				Mensagens.msgError("Erro inesperado");
			}
		} else {
			Mensagens.msgError("Usuário ou senha incorreto");
		}
	}

	@SuppressWarnings("static-access")
	public void deslogar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext context = facesContext.getExternalContext();
		context.getSessionMap().remove("usuarioLogado");
		HttpServletRequest httpServletRequest = (HttpServletRequest) facesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		httpServletRequest.getSession().invalidate();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(context.getRequestContextPath() + "/cadastro-clientes.jsf");
		} catch (IOException e) {
			Mensagens.msgError("Erro inesperado!");
		}
	}
	
	public Login getLogin() {
		return login;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
}
