package Daos.controller;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceLogin;
import Model.Login;

public class DaoControllerLogin extends Dao<Login>{

	@Inject
	private InterfaceLogin interfaceLogin;
	
	private static final long serialVersionUID = 1L;

	public boolean verificarCadastro(Login login) {
		return interfaceLogin.verificarCadastro(login);
	}
	
}
