package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.Cliente;

public interface InterfaceCliente extends Serializable{
	List<Cliente> listaPendencias(Cliente cliente);
}
