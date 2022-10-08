package br.com.erivelton.canchafut.dao.controller;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceLogin;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.Login;

public class DaoControllerLogin extends Dao<Login>{

	@Inject
	private InterfaceLogin interfaceLogin;
	
	private static final long serialVersionUID = 1L;

	public boolean verificarCadastro(Login login) {
		return interfaceLogin.verificarCadastro(login);
	}
	
}
