package Daos.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import Daos.Dao;
import Interfaces.InterfaceMandanteAg;
import Model.AgendamentoTimes;
import Model.TimeMandante;

@Named
public class DaoControllerMandanteAgenda extends Dao<TimeMandante> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private InterfaceMandanteAg interfaceMandanteAg;
	
	public boolean verificarMandanteHorario(AgendamentoTimes agendaTime) {
		return interfaceMandanteAg.verificarMandanteHorario(agendaTime);
	}
	
}
