package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.erivelton.canchafut.Interface.InterfaceRodadas;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.Confrontos;
import br.com.erivelton.canchafut.model.Rodada;
import br.com.erivelton.canchafut.model.enums.RodadaConfirmada;

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
