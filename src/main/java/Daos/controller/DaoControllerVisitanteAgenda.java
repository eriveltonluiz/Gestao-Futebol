package Daos.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import Daos.Dao;
import Interfaces.InterfaceVisitanteAg;
import Model.AgendamentoTimes;
import Model.TimeVisitante;

@Named
public class DaoControllerVisitanteAgenda extends Dao<TimeVisitante> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private InterfaceVisitanteAg interfaceVisitanteAg;
	
	public boolean verificarVisitanteHorario(AgendamentoTimes agendaTime) {
		return interfaceVisitanteAg.verificarVisitanteHorario(agendaTime);
	}
	
	public TimeVisitante returnVisitante(TimeVisitante visit) {
		return interfaceVisitanteAg.returnVisitante(visit);
	}
}
