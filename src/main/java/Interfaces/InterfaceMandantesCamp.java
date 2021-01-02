package Interfaces;

import java.io.Serializable;

import Model.CampMandante;

public interface InterfaceMandantesCamp extends Serializable{
	void atualizarResultadoFavor(CampMandante mandante, int qtd, String escolha);
	void atualizarResultadoContra(CampMandante mandante, int qtd, String escolha);
	void atualizarSaldoDeGols(CampMandante confronto);
	void atualizarDadosMataMata(CampMandante confronto);
}
