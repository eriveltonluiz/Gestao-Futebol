package Daos.controller;

import java.util.List;

import javax.inject.Inject;

import Daos.Dao;
import Interfaces.InterfaceVermelhosMand;
import Model.CampMandante;
import Model.VermelhosPartidaMand;

public class DaoControllerVermelhosMand extends Dao<VermelhosPartidaMand> {

	private static final long serialVersionUID = 1L;

	@Inject
	private InterfaceVermelhosMand interfaceVermelhosMand;

	public List<VermelhosPartidaMand> vermelhosPartida(CampMandante mand) {
		return interfaceVermelhosMand.vermelhosPartida(mand);
	}
}
