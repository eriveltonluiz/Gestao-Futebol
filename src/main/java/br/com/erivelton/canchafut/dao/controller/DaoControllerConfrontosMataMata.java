package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceConfrontoMataMata;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.ConfrontosMataMata;


public class DaoControllerConfrontosMataMata extends Dao<ConfrontosMataMata>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceConfrontoMataMata interfaceConfrontoMataMata;
	
	public void inserirEtapas(String etapaInicial){
		interfaceConfrontoMataMata.inserirEtapas(etapaInicial);
	}
	
	public List<ConfrontosMataMata> confrontosEtapa(String etapa, String status){
		return interfaceConfrontoMataMata.confrontosEtapa(etapa, status);
	}
	
	public List<ConfrontosMataMata> resultadosConfrontosEtapa(String etapa, String status){
		return interfaceConfrontoMataMata.resultadosConfrontosEtapa(etapa, status);
	}
	
	public ConfrontosMataMata returnProxConfronto(String etapa, int ident) {
		return interfaceConfrontoMataMata.returnProxConfronto(etapa, ident);
	}
	
	public boolean verificarHorario(ConfrontosMataMata confronto) {
		return interfaceConfrontoMataMata.verificarHorario(confronto);
	}
}
