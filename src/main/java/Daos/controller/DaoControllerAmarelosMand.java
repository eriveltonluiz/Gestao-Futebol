package Daos.controller;

import java.util.List;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceAmarelosMand;
import Model.AmarelosPartidaMand;
import Model.CampMandante;

public class DaoControllerAmarelosMand extends Dao<AmarelosPartidaMand>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceAmarelosMand interfaceAmarelosMand;
	
	public List<AmarelosPartidaMand> amarelosPartida(CampMandante mand){
		return interfaceAmarelosMand.amarelosPartida(mand);
	}
}
