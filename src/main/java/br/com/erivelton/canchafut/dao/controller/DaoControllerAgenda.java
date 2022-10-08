package br.com.erivelton.canchafut.dao.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.erivelton.canchafut.Interface.InterfaceAgenda;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.Agenda;

@Named
public class DaoControllerAgenda extends Dao<Agenda> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceAgenda interfaceAgenda;

	public boolean verificarAgendaExistente(Date data) {
		return interfaceAgenda.verificarAgendaExistente(data);
	}
	
	public void criarAgendaPorData(Agenda agenda, Calendar dia) {
		interfaceAgenda.criarAgendaPorData(agenda, dia);
	}
	
	public Agenda retornarRegistroPorData(Date data) {
		return interfaceAgenda.retornarRegistroPorData(data);
	}
}
