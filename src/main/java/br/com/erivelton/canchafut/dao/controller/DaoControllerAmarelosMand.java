package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceAmarelosMand;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.AmarelosPartidaMand;
import br.com.erivelton.canchafut.model.CampMandante;

public class DaoControllerAmarelosMand extends Dao<AmarelosPartidaMand>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceAmarelosMand interfaceAmarelosMand;
	
	public List<AmarelosPartidaMand> amarelosPartida(CampMandante mand){
		return interfaceAmarelosMand.amarelosPartida(mand);
	}
}
