package Daos.controller;

import java.util.List;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceAmarelosVisit;
import Model.AmarelosPartidaVisit;
import Model.CampVisitante;

public class DaoControllerAmarelosVisit extends Dao<AmarelosPartidaVisit>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceAmarelosVisit interfaceAmarelosVisit;
	
	public List<AmarelosPartidaVisit> amarelosPartida(CampVisitante visit){
		return interfaceAmarelosVisit.amarelosPartida(visit);
	}
}
