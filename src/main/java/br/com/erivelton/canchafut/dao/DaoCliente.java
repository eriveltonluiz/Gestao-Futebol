package br.com.erivelton.canchafut.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.erivelton.canchafut.Interface.InterfaceCliente;
import br.com.erivelton.canchafut.model.Cliente;

public class DaoCliente extends Dao<Cliente> implements InterfaceCliente{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Cliente listaCliente (String nomeCliente) {
		Cliente e = (Cliente) em.createQuery("from Cliente c where c.nomeTime = '" + nomeCliente + "'").getSingleResult();
		return e;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listaPendencias(Cliente cliente) {
		try {
			String sql = "SELECT t FROM TimeMandante t"
					+ "INNER JOIN t.cliente"
					+ "INNER JOIN t.pagamentos"
					+ "WHERE t.cliente.idCliente = " + cliente.getIdCliente();
			List<Cliente> list = em.createQuery(sql).getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
