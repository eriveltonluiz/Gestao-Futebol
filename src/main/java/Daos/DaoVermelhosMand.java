package Daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import Interfaces.InterfaceVermelhosMand;
import Model.CampMandante;
import Model.VermelhosPartidaMand;

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
