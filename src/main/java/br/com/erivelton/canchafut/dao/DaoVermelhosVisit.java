package br.com.erivelton.canchafut.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.erivelton.canchafut.Interface.InterfaceVermelhosVisit;
import br.com.erivelton.canchafut.model.CampVisitante;
import br.com.erivelton.canchafut.model.VermelhosPartidaVisit;

@SuppressWarnings("unchecked")
public class DaoVermelhosVisit implements InterfaceVermelhosVisit {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public List<VermelhosPartidaVisit> vermelhosPartida(CampVisitante visit) {
		List<VermelhosPartidaVisit> list = em
				.createQuery("select g from VermelhosPartidaVisit g where g.visitante.idVisitante = " + visit.getIdVisitante())
				.getResultList();
		return list;
	}

}
