package Daos.controller;

import java.util.List;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceConfronto;
import Model.Confrontos;
import Model.Grupo;
import Model.Rodada;
import Model.TimeCamp;


public class DaoControllerConfrontosCamp extends Dao<Confrontos>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceConfronto interfaceConfronto;
	
	public Confrontos atualizarPlacarUnico(Confrontos confronto) {
		return interfaceConfronto.atualizarPlacarUnico(confronto);
	}
	
	public List<Confrontos> confrontosRodada(Rodada rodada, Grupo grupo){
		return interfaceConfronto.confrontosRodada(rodada, grupo);
	}
	
	public List<Confrontos> resultadosRodada(Long id){
		return interfaceConfronto.resultadosRodada(id);
	}
	
	public boolean verificarConfrontosRepetidos(TimeCamp mand, TimeCamp visit) {
		return interfaceConfronto.verificarConfrontosRepetidos(mand, visit);
	}

	public boolean verificarHorario(Confrontos confronto) {
		return interfaceConfronto.verificarHorario(confronto);
	}

	public boolean verificarConfrontosPendentes() {
		return interfaceConfronto.verificarConfrontosPendentes();
	}
}
