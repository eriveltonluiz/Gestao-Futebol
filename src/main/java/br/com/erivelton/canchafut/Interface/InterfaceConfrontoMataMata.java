package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.ConfrontosMataMata;

public interface InterfaceConfrontoMataMata extends Serializable{
	void inserirEtapas(String etapaInicial);
	List<ConfrontosMataMata> confrontosEtapa(String etapa, String status);
	List<ConfrontosMataMata> resultadosConfrontosEtapa(String etapa, String status);
	ConfrontosMataMata returnProxConfronto(String etapa, int ident);
	boolean verificarHorario(ConfrontosMataMata confronto);
}
