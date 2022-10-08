package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceCliente;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.Cliente;

public class DaoControllerCliente extends Dao<Cliente>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceCliente interfaceCliente;
	
	public List<Cliente> listaPendencias(Cliente cliente){
		return interfaceCliente.listaPendencias(cliente);
	}
	
}
