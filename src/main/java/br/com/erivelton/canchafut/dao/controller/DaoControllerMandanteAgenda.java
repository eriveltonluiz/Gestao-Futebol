package br.com.erivelton.canchafut.dao.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.erivelton.canchafut.Interface.InterfaceMandanteAg;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.TimeMandante;

@Named
public class DaoControllerMandanteAgenda extends Dao<TimeMandante> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private InterfaceMandanteAg interfaceMandanteAg;
	
	public boolean verificarMandanteHorario(AgendamentoTimes agendaTime) {
		return interfaceMandanteAg.verificarMandanteHorario(agendaTime);
	}
	
}
