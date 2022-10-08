package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.AmarelosPartidaVisit;
import br.com.erivelton.canchafut.model.CampVisitante;

public interface InterfaceAmarelosVisit extends Serializable{
	List<AmarelosPartidaVisit> amarelosPartida(CampVisitante visit);
}
