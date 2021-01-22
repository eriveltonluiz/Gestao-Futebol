package Daos;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import Interfaces.InterfaceLogin;
import Model.Login;

public class DaoLogin implements InterfaceLogin {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	@Override
	public boolean verificarCadastro(Login login) {
		String sql = "select count(1) from Login as l where l.usuario = '" + login.getUsuario() + "'";
		Long qtd = (Long) em.createQuery(sql).getSingleResult();

		if (qtd == 0) {
			return true;
		}
		return false;
	}
}
