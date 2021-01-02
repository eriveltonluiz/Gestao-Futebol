package Daos.controller;

import javax.inject.Inject;
import javax.inject.Named;

import Daos.Dao;
import Interfaces.InterfaceMandantesCamp;
import Model.CampMandante;

@Named
public class DaoControllerMandantesCamp extends Dao<CampMandante>{

	private static final long serialVersionUID = 1L;

	@Inject
	private InterfaceMandantesCamp interfaceMandantesCamp;
	
	public void atualizarResultadoFavor(CampMandante mandante, int qtd, String escolha) {
		interfaceMandantesCamp.atualizarResultadoFavor(mandante, qtd, escolha);
	}
	
	public void atualizarResultadoContra(CampMandante mandante, int qtd, String escolha) {
		interfaceMandantesCamp.atualizarResultadoContra(mandante, qtd, escolha);
	}

	public void atualizarSaldoDeGols(CampMandante confronto) {
		interfaceMandantesCamp.atualizarSaldoDeGols(confronto);
	}
	
	public void atualizarDadosMataMata(CampMandante confronto) {
		interfaceMandantesCamp.atualizarDadosMataMata(confronto);
	}
}
