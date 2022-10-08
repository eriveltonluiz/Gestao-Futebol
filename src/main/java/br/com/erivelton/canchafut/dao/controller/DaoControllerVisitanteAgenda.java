package br.com.erivelton.canchafut.dao.controller;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.erivelton.canchafut.Interface.InterfaceVisitanteAg;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.TimeVisitante;

@Named
public class DaoControllerVisitanteAgenda extends Dao<TimeVisitante> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private InterfaceVisitanteAg interfaceVisitanteAg;
	
	public boolean verificarVisitanteHorario(AgendamentoTimes agendaTime) {
		return interfaceVisitanteAg.verificarVisitanteHorario(agendaTime);
	}
	
	public TimeVisitante returnVisitante(Date data, String time) {
		return interfaceVisitanteAg.returnVisitante(data, time);
	}
}
