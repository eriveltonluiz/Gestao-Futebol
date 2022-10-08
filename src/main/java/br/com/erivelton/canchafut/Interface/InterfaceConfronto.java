package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.Confrontos;
import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.Rodada;
import br.com.erivelton.canchafut.model.TimeCamp;

public interface InterfaceConfronto extends Serializable{
	Confrontos atualizarPlacarUnico(Confrontos confronto);
	List<Confrontos> confrontosRodada(Rodada rodada, Grupo grupo);
	List<Confrontos> resultadosRodada(Long id);
	boolean verificarConfrontosRepetidos(TimeCamp mand, TimeCamp visit);
	boolean verificarHorario(Confrontos confronto);
	boolean verificarConfrontosPendentes();
}
