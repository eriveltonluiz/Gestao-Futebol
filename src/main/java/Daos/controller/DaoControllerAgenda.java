package Daos.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import Daos.Dao;
import Interfaces.InterfaceAgenda;
import Model.Agenda;

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
