package br.com.erivelton.canchafut.dao.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.erivelton.canchafut.Interface.InterfaceVermelhosMand;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.CampMandante;
import br.com.erivelton.canchafut.model.VermelhosPartidaMand;

public class DaoControllerVermelhosMand extends Dao<VermelhosPartidaMand> {

	private static final long serialVersionUID = 1L;

	@Inject
	private InterfaceVermelhosMand interfaceVermelhosMand;

	public List<VermelhosPartidaMand> vermelhosPartida(CampMandante mand) {
		return interfaceVermelhosMand.vermelhosPartida(mand);
	}
}
