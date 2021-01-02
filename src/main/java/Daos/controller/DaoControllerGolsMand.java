package Daos.controller;

import java.util.List;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceGolsMand;
import Model.CampMandante;
import Model.GolsPartidaMand;

public class DaoControllerGolsMand extends Dao<GolsPartidaMand>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceGolsMand interfaceGolsMand;
	
	public List<GolsPartidaMand> autoresPartida(CampMandante mand){
		return interfaceGolsMand.autoresPartida(mand);
	}
	
}
