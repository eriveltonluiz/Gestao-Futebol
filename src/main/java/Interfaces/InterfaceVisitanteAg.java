package Interfaces;

import java.io.Serializable;

import Model.AgendamentoTimes;
import Model.TimeVisitante;

public interface InterfaceVisitanteAg extends Serializable{
	boolean verificarVisitanteHorario(AgendamentoTimes agendaTime);
	TimeVisitante returnVisitante(TimeVisitante visit);
}
