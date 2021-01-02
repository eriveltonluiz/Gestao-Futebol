package Daos.controller;

import java.util.List;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceVermelhosVisit;
import Model.CampVisitante;
import Model.VermelhosPartidaVisit;

public class DaoControllerVermelhosVisit extends Dao<VermelhosPartidaVisit>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceVermelhosVisit interfaceVermelhosVisit;

	public List<VermelhosPartidaVisit> vermelhosPartida(CampVisitante visit) {
		return interfaceVermelhosVisit.vermelhosPartida(visit);
	}
}
