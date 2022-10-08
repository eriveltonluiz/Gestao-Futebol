package br.com.erivelton.canchafut.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.erivelton.canchafut.Interface.InterfaceTimesCamp;
import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.Rodada;
import br.com.erivelton.canchafut.model.TimeCamp;

@SuppressWarnings("unchecked")
public class DaoTimeCamp extends Dao<TimeCamp> implements InterfaceTimesCamp {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public List<TimeCamp> returnListTimesOrdenado(Grupo grupo) {
		List<TimeCamp> list = em.createQuery("from TimeCamp t where t.grupo.id = " + grupo.getId()
				+ " order by t.pontos desc, t.saldoDeGols desc, t.golsPro desc").getResultList();
		return list;
	}

	@Override
	public List<TimeCamp> timesDoGrupo(Grupo grupoEscolhido, Rodada rodada) {
		List<TimeCamp> list = new ArrayList<TimeCamp>();
		list = em.createQuery(
				"select t from TimeCamp t left join t.mandantes as m left join t.visitantes v where m.idMandante is null and v.idVisitante is null and t.grupo.id = "
						+ grupoEscolhido.getId())
				.getResultList();
		return list;
	}

	@Override
	public void atualizarCartoesAmarelosTime(TimeCamp time, int qtd) {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			em.createQuery("update TimeCamp t set t.qtdCartoesAmarelos = t.qtdCartoesAmarelos + " + qtd
					+ " where t.id = " + time.getId()).executeUpdate();
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public void atualizarCartoesVermelhosTime(TimeCamp time, int qtd) {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			em.createQuery("update TimeCamp t set t.qtdCartoesVermelhos = t.qtdCartoesVermelhos + " + qtd
					+ " where t.id = " + time.getId()).executeUpdate();
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public boolean verificarExistenciaCampeonato() {
		Long qtd = (Long) em.createQuery("select count(*) from TimeCamp t where t.grupo.id = null").getSingleResult();
		if(qtd > 0) {
			return true;
		}
		return false;
	}
}
