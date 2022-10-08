package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceVermelhosVisit;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.CampVisitante;
import br.com.erivelton.canchafut.model.VermelhosPartidaVisit;

public class DaoControllerVermelhosVisit extends Dao<VermelhosPartidaVisit>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceVermelhosVisit interfaceVermelhosVisit;

	public List<VermelhosPartidaVisit> vermelhosPartida(CampVisitante visit) {
		return interfaceVermelhosVisit.vermelhosPartida(visit);
	}
}
