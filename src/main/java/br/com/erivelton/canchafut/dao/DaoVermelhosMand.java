package br.com.erivelton.canchafut.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.erivelton.canchafut.Interface.InterfaceVermelhosMand;
import br.com.erivelton.canchafut.model.CampMandante;
import br.com.erivelton.canchafut.model.VermelhosPartidaMand;

@SuppressWarnings("unchecked")
public class DaoVermelhosMand implements InterfaceVermelhosMand {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public List<VermelhosPartidaMand> vermelhosPartida(CampMandante mand) {
		List<VermelhosPartidaMand> list = em
				.createQuery("select g from VermelhosPartidaMand g where g.mandante.idMandante = " + mand.getIdMandante())
				.getResultList();
		return list;
	}

}
