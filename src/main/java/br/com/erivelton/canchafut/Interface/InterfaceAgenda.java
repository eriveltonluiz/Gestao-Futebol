package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import br.com.erivelton.canchafut.model.Agenda;

public interface InterfaceAgenda extends Serializable{
	boolean verificarAgendaExistente(Date data);
	void criarAgendaPorData(Agenda agenda, Calendar dia);
	Agenda retornarRegistroPorData(Date data);
}
