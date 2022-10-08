package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.CampVisitante;
import br.com.erivelton.canchafut.model.GolsPartidaVisit;

public interface InterfaceGolsVisit extends Serializable{
	List<GolsPartidaVisit> autoresPartida(CampVisitante visit);
}
