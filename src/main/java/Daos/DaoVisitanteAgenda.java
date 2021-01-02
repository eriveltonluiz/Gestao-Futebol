package Daos;

import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import Interfaces.InterfaceVisitanteAg;
import Model.AgendamentoTimes;
import Model.TimeVisitante;

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
	public TimeVisitante returnVisitante(TimeVisitante visit) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TimeVisitante visitante = (TimeVisitante) em.createQuery("from TimeVisitante as v where v.nomeTimeVisit = '"
				+ visit.getNomeTimeVisit() + "' and v.data = '" + sdf.format(visit.getData()) + "'").setMaxResults(1).getSingleResult();
		return visitante;
	}

}
