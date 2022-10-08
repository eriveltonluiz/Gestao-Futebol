package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceAmarelosVisit;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.AmarelosPartidaVisit;
import br.com.erivelton.canchafut.model.CampVisitante;

public class DaoControllerAmarelosVisit extends Dao<AmarelosPartidaVisit>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceAmarelosVisit interfaceAmarelosVisit;
	
	public List<AmarelosPartidaVisit> amarelosPartida(CampVisitante visit){
		return interfaceAmarelosVisit.amarelosPartida(visit);
	}
}
