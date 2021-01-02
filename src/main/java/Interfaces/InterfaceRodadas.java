package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.Confrontos;
import Model.Rodada;
import Model.TimeCamp;
import Model.enums.RodadaConfirmada;

public interface InterfaceRodadas extends Serializable{
	List<Confrontos> confrontosRodada(String parametro);
	Rodada rodadaAtuall(String rodada);
	List<TimeCamp> timesAleatorios();
	Rodada rodadaAtual(RodadaConfirmada confirm);
	void inserirProximaRodada(int ident, String identificacao);
}
