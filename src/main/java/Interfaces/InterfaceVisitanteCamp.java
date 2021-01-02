package Interfaces;

import java.io.Serializable;

import Model.CampVisitante;

public interface InterfaceVisitanteCamp extends Serializable{
	void atualizarResultadoFavor(CampVisitante visitante, int qtd, String escolha);
	void atualizarResultadoContra(CampVisitante visitante, int qtd, String escolha);
	void atualizarSaldoDeGols(CampVisitante confronto);
	void atualizarDadosMataMata(CampVisitante confronto);
}
