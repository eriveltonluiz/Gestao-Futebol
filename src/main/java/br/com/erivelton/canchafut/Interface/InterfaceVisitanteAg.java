package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.Date;

import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.TimeVisitante;

public interface InterfaceVisitanteAg extends Serializable{
	boolean verificarVisitanteHorario(AgendamentoTimes agendaTime);
	TimeVisitante returnVisitante(Date data, String time);
}
