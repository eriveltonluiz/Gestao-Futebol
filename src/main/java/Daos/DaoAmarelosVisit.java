package Daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import Interfaces.InterfaceAmarelosVisit;
import Model.CampVisitante;
import Model.AmarelosPartidaVisit;

@SuppressWarnings("unchecked")
public class DaoAmarelosVisit implements InterfaceAmarelosVisit {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public List<AmarelosPartidaVisit> amarelosPartida(CampVisitante visit) {
		List<AmarelosPartidaVisit> list = em
				.createQuery("select g from AmarelosPartidaVisit g where g.visitante.idVisitante = " + visit.getIdVisitante())
				.getResultList();
		return list;
	}

}
