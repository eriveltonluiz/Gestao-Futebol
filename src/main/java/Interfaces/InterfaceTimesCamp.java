package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.Grupo;
import Model.Rodada;
import Model.TimeCamp;

public interface InterfaceTimesCamp extends Serializable{
	 List<TimeCamp> returnListTimesOrdenado(Grupo grupo);
	 List<TimeCamp> timesDoGrupo(Grupo grupoEscolhido, Rodada rodada);
	 void atualizarCartoesAmarelosTime(TimeCamp time, int qtd);
	 void atualizarCartoesVermelhosTime(TimeCamp time, int qtd);
	 boolean verificarExistenciaCampeonato();
}
