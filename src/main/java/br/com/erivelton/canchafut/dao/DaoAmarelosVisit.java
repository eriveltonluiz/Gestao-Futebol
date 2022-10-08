package br.com.erivelton.canchafut.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.erivelton.canchafut.Interface.InterfaceAmarelosVisit;
import br.com.erivelton.canchafut.model.AmarelosPartidaVisit;
import br.com.erivelton.canchafut.model.CampVisitante;

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
