package Daos.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import Daos.Dao;
import Interfaces.InterfaceRodadas;
import Model.Confrontos;
import Model.Rodada;
import Model.enums.RodadaConfirmada;

@Named
public class DaoControllerRodada extends Dao<Rodada>{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceRodadas interfaceRodadas;
	
	public List<Confrontos> confrontosRodada(String parametro){
		return interfaceRodadas.confrontosRodada(parametro);
	}
	
	public Rodada rodadaAtuall(String rodada) {
		return interfaceRodadas.rodadaAtuall(rodada);
	}
	
	public Rodada rodadaAtual(RodadaConfirmada confirm) {
		return interfaceRodadas.rodadaAtual(confirm);
	}
	
	public void inserirProximaRodada(int ident, String identificacao) {
		interfaceRodadas.inserirProximaRodada(ident, identificacao);
	}
	
}
