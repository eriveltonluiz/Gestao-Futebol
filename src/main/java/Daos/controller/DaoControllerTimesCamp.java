package Daos.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import Daos.Dao;
import Interfaces.InterfaceTimesCamp;
import Model.Grupo;
import Model.Rodada;
import Model.TimeCamp;

@Named
public class DaoControllerTimesCamp extends Dao<TimeCamp>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceTimesCamp interfaceTimesCamp;
	
	public List<TimeCamp> returnListTimesOrdenado(Grupo grupo){
		return interfaceTimesCamp.returnListTimesOrdenado(grupo);
	}

	public List<TimeCamp> timesDoGrupo(Grupo grupoEscolhido, Rodada rodada) {
		return interfaceTimesCamp.timesDoGrupo(grupoEscolhido, rodada);
	}
	
	public void atualizarCartoesAmarelosTime(TimeCamp time, int qtd) {
		interfaceTimesCamp.atualizarCartoesAmarelosTime(time, qtd);
	}

	public void atualizarCartoesVermelhosTime(TimeCamp time, int qtd) {
		interfaceTimesCamp.atualizarCartoesVermelhosTime(time, qtd);
	}
	
	public boolean verificarExistenciaCampeonato() {
		return interfaceTimesCamp.verificarExistenciaCampeonato();
	}

}
