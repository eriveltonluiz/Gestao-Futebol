package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceGolsMand;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.CampMandante;
import br.com.erivelton.canchafut.model.GolsPartidaMand;

public class DaoControllerGolsMand extends Dao<GolsPartidaMand>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceGolsMand interfaceGolsMand;
	
	public List<GolsPartidaMand> autoresPartida(CampMandante mand){
		return interfaceGolsMand.autoresPartida(mand);
	}
	
}
