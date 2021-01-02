package Daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import Interfaces.InterfaceGolsVisit;
import Model.CampVisitante;
import Model.GolsPartidaVisit;

@SuppressWarnings("unchecked")
public class DaoGolsVisit implements InterfaceGolsVisit {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public List<GolsPartidaVisit> autoresPartida(CampVisitante visit) {
		List<GolsPartidaVisit> list = em
				.createQuery("select g from GolsPartidaVisit g where g.visitante.idVisitante = " + visit.getIdVisitante())
				.getResultList();
		return list;
	}

}
