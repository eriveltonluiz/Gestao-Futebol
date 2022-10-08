package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.CampVisitante;
import br.com.erivelton.canchafut.model.VermelhosPartidaVisit;

public interface InterfaceVermelhosVisit extends Serializable{
	List<VermelhosPartidaVisit> vermelhosPartida(CampVisitante visit);
}
