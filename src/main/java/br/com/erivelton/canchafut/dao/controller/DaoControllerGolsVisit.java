package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceGolsVisit;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.CampVisitante;
import br.com.erivelton.canchafut.model.GolsPartidaVisit;

public class DaoControllerGolsVisit extends Dao<GolsPartidaVisit>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceGolsVisit interfaceGolsVisit;
	
	public List<GolsPartidaVisit> autoresPartida(CampVisitante visit){
		return interfaceGolsVisit.autoresPartida(visit);
	}
}
