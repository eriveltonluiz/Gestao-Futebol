package Daos.controller;

import java.util.List;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceGolsVisit;
import Model.CampVisitante;
import Model.GolsPartidaVisit;

public class DaoControllerGolsVisit extends Dao<GolsPartidaVisit>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceGolsVisit interfaceGolsVisit;
	
	public List<GolsPartidaVisit> autoresPartida(CampVisitante visit){
		return interfaceGolsVisit.autoresPartida(visit);
	}
}
