package br.com.erivelton.canchafut.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.erivelton.canchafut.Interface.InterfaceVisitanteAg;
import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.TimeVisitante;

public class DaoVisitanteAgenda implements InterfaceVisitanteAg {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public boolean verificarVisitanteHorario(AgendamentoTimes agendaTime) {
		Long qtd = (Long) em.createQuery("select count(1) from TimeVisitante as t where t.cliente.idCliente = "
				+ agendaTime.getVisitante().getCliente().getIdCliente()).getSingleResult();
		if (qtd == 1) {
			return true;
		}
		return false;
	}

	@Override
	public TimeVisitante returnVisitante(Date data, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TimeVisitante visitante = (TimeVisitante) em.createQuery("from TimeVisitante as v where v.nomeTimeVisit = '"
				+ time + "' and v.data = '" + sdf.format(data) + "'").setMaxResults(1).getSingleResult();
		return visitante;
	}

}
