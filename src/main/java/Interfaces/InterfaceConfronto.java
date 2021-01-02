package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.Confrontos;
import Model.Grupo;
import Model.Rodada;
import Model.TimeCamp;

public interface InterfaceConfronto extends Serializable{
	Confrontos atualizarPlacarUnico(Confrontos confronto);
	List<Confrontos> confrontosRodada(Rodada rodada, Grupo grupo);
	List<Confrontos> resultadosRodada(Long id);
	boolean verificarConfrontosRepetidos(TimeCamp mand, TimeCamp visit);
	boolean verificarHorario(Confrontos confronto);
	boolean verificarConfrontosPendentes();
}
