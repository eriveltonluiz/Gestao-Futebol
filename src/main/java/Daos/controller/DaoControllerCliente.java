package Daos.controller;

import java.util.List;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceCliente;
import Model.Cliente;

public class DaoControllerCliente extends Dao<Cliente>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceCliente interfaceCliente;
	
	public List<Cliente> listaPendencias(Cliente cliente){
		return interfaceCliente.listaPendencias(cliente);
	}
	
}
