package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceConfronto;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.Confrontos;
import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.Rodada;
import br.com.erivelton.canchafut.model.TimeCamp;


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
