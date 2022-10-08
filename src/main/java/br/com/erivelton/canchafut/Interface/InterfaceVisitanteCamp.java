package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;

import br.com.erivelton.canchafut.model.CampVisitante;

public interface InterfaceVisitanteCamp extends Serializable{
	void atualizarResultadoFavor(CampVisitante visitante, int qtd, String escolha);
	void atualizarResultadoContra(CampVisitante visitante, int qtd, String escolha);
	void atualizarSaldoDeGols(CampVisitante confronto);
	void atualizarDadosMataMata(CampVisitante confronto);
}
