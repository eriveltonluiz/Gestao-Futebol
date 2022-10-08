package br.com.erivelton.canchafut.dao.controller;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceVisitanteCamp;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.CampVisitante;

public class DaoControllerVisitantesCamp extends Dao<CampVisitante>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceVisitanteCamp interfaceVisitanteCamp;
	
	public void atualizarResultadoFavor(CampVisitante visitante, int qtd, String escolha) {
		interfaceVisitanteCamp.atualizarResultadoFavor(visitante, qtd, escolha);
	}
	
	public void atualizarResultadoContra(CampVisitante visitante, int qtd, String escolha) {
		interfaceVisitanteCamp.atualizarResultadoContra(visitante, qtd, escolha);
	}
	
	public void atualizarSaldoDeGols(CampVisitante confronto) {
		interfaceVisitanteCamp.atualizarSaldoDeGols(confronto);
	}
	
	public void atualizarDadosMataMata(CampVisitante confronto) {
		interfaceVisitanteCamp.atualizarDadosMataMata(confronto);
	}
}
