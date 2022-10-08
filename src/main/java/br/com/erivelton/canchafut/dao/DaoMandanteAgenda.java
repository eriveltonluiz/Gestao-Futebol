package br.com.erivelton.canchafut.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.erivelton.canchafut.Interface.InterfaceMandanteAg;
import br.com.erivelton.canchafut.model.AgendamentoTimes;

public class DaoMandanteAgenda implements InterfaceMandanteAg {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public boolean verificarMandanteHorario(AgendamentoTimes agendaTime) {
		Long qtd = (Long) em.createQuery("select count(1) from TimeMandante as t where t.cliente.idCliente = "
				+ agendaTime.getMandante().getCliente().getIdCliente()).getSingleResult();
		if(qtd > 0) {
			return true;
		}
		return false;
	}

}
