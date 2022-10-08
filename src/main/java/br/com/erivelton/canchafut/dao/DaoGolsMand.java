package br.com.erivelton.canchafut.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.erivelton.canchafut.Interface.InterfaceGolsMand;
import br.com.erivelton.canchafut.model.CampMandante;
import br.com.erivelton.canchafut.model.GolsPartidaMand;

@SuppressWarnings("unchecked")
public class DaoGolsMand implements InterfaceGolsMand {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public List<GolsPartidaMand> autoresPartida(CampMandante mand) {
		List<GolsPartidaMand> list = em
				.createQuery("select g from GolsPartidaMand g inner join g.jogador j where g.mandante.idMandante = " + mand.getIdMandante())
				.getResultList();
		return list;
	}
}
