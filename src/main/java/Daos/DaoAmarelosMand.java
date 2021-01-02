package Daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import Interfaces.InterfaceAmarelosMand;
import Model.CampMandante;
import Model.AmarelosPartidaMand;

@SuppressWarnings("unchecked")
public class DaoAmarelosMand implements InterfaceAmarelosMand {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public List<AmarelosPartidaMand> amarelosPartida(CampMandante mand) {
		List<AmarelosPartidaMand> list = em
				.createQuery("select g from AmarelosPartidaMand g where g.mandante.idMandante = " + mand.getIdMandante())
				.getResultList();
		return list;
	}

}
