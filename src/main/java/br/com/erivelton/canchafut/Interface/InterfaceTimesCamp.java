package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.Rodada;
import br.com.erivelton.canchafut.model.TimeCamp;

public interface InterfaceTimesCamp extends Serializable{
	 List<TimeCamp> returnListTimesOrdenado(Grupo grupo);
	 List<TimeCamp> timesDoGrupo(Grupo grupoEscolhido, Rodada rodada);
	 void atualizarCartoesAmarelosTime(TimeCamp time, int qtd);
	 void atualizarCartoesVermelhosTime(TimeCamp time, int qtd);
	 boolean verificarExistenciaCampeonato();
}
