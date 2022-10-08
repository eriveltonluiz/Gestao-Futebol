package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;

import br.com.erivelton.canchafut.model.Login;

public interface InterfaceLogin extends Serializable{
	boolean verificarCadastro(Login login);
}
