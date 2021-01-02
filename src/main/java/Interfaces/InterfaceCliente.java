package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.Cliente;

public interface InterfaceCliente extends Serializable{
	List<Cliente> listaPendencias(Cliente cliente);
}
