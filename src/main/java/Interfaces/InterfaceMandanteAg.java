package Interfaces;

import java.io.Serializable;

import Model.AgendamentoTimes;

public interface InterfaceMandanteAg extends Serializable{
	boolean verificarMandanteHorario(AgendamentoTimes agendaTime);
}
