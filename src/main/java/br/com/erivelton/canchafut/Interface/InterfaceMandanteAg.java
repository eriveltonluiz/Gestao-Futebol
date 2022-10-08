package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;

import br.com.erivelton.canchafut.model.AgendamentoTimes;

public interface InterfaceMandanteAg extends Serializable{
	boolean verificarMandanteHorario(AgendamentoTimes agendaTime);
}
