package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;

import br.com.erivelton.canchafut.model.CampMandante;

public interface InterfaceMandantesCamp extends Serializable{
	void atualizarResultadoFavor(CampMandante mandante, int qtd, String escolha);
	void atualizarResultadoContra(CampMandante mandante, int qtd, String escolha);
	void atualizarSaldoDeGols(CampMandante confronto);
	void atualizarDadosMataMata(CampMandante confronto);
}
