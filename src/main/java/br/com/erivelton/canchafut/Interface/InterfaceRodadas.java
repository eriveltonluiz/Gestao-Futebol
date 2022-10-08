package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.Confrontos;
import br.com.erivelton.canchafut.model.Rodada;
import br.com.erivelton.canchafut.model.TimeCamp;
import br.com.erivelton.canchafut.model.enums.RodadaConfirmada;

public interface InterfaceRodadas extends Serializable{
	List<Confrontos> confrontosRodada(String parametro);
	Rodada rodadaAtuall(String rodada);
	List<TimeCamp> timesAleatorios();
	Rodada rodadaAtual(RodadaConfirmada confirm);
	void inserirProximaRodada(int ident, String identificacao);
}
