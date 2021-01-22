package Interfaces;

import java.io.Serializable;

import Model.Login;

public interface InterfaceLogin extends Serializable{
	boolean verificarCadastro(Login login);
}
