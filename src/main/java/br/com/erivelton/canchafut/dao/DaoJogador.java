package br.com.erivelton.canchafut.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.erivelton.canchafut.Interface.InterfaceJogadores;
import br.com.erivelton.canchafut.model.Jogador;
import br.com.erivelton.canchafut.model.TimeCamp;

@SuppressWarnings("unchecked")
@Named
public class DaoJogador extends Dao<Jogador> implements InterfaceJogadores {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public Long verificarNumeroCamisa(Jogador jogador) {
		Long qtd = (Long) em.createQuery("select count(*) from Jogador j where j.numCamisa = " + jogador.getNumCamisa()
				+ " and j.time.id = " + jogador.getTime().getId()).getSingleResult();
		return qtd;
	}

	@Override
	public List<Jogador> listarJogadoresTime(TimeCamp time) {
		return em.createQuery("from Jogador j where j.time.id = " + time.getId()).getResultList();
	}

	@Override
	public void atualizarQtdGols(Jogador jog, int qt, String escolha) {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.createNativeQuery("update Jogador j set j.golsPorJogo = j.golsPorJogo " + escolha + " " + qt
				+ ", j.gols = j.gols " + escolha + " " + qt + " where j.id = " + jog.getId()).executeUpdate();
		trans.commit();
	}

	@Override
	public void atualizarQtdCartoesAmarelos(Jogador jog, int qtd) {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			em.createQuery("update Jogador j set j.qtdAmarelosJogo = j.qtdAmarelosJogo + " + qtd
					+ ", j.qtdCartoesAmarelos = j.qtdCartoesAmarelos + " + qtd + "where j.id = " + jog.getId())
					.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public void atualizarQtdCartoesVermelhos(Jogador jog, int qtd) {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			em.createQuery("update Jogador j set j.qtdVermelhosJogo = j.qtdVermelhosJogo + " + qtd
					+ ", j.qtdCartoesVermelhos = j.qtdCartoesVermelhos + " + qtd + "where j.id = " + jog.getId())
					.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public List<Jogador> listarJogadoresAmarelados(TimeCamp time) {
		List<Jogador> list = new ArrayList<Jogador>();
		list = em.createQuery(
				"select j from Jogador j where j.qtdVermelhosJogo < 1 and j.qtdAmarelosJogo in(0,1) and j.time.id = "
						+ time.getId())
				.getResultList();
		return list;
	}

	@Override
	public List<Jogador> listarJogadoresVermelhos(TimeCamp time) {
		List<Jogador> list = em.createQuery(
				"from Jogador j where j.qtdVermelhosJogo < 1 and j.qtdAmarelosJogo < 2 and j.time.id = " + time.getId())
				.getResultList();
		return list;
	}

	@Override
	public List<Jogador> jogadoresComGols(TimeCamp timeCamp) {
		List<Jogador> list = new ArrayList<Jogador>();
		list = em.createQuery("select j from Jogador j where j.golsPorJogo != 0 and j.time.id = " + timeCamp.getId())
				.getResultList();
		return list;
	}

	@Override
	public List<Jogador> listarArtilharia() {
		List<Jogador> list = em.createQuery("from Jogador as j order by j.gols desc").setMaxResults(20).getResultList();
		return list;
	}

	@Override
	public List<Jogador> listarAmarelados() {
		List<Jogador> list = em.createQuery("from Jogador as j order by j.qtdCartoesAmarelos desc").setMaxResults(20).getResultList();
		return list;
	}

	@Override
	public List<Jogador> listarVermelhos() {
		List<Jogador> list = em.createQuery("from Jogador as j order by j.qtdCartoesVermelhos desc").setMaxResults(20).getResultList();
		return list;
	}

	@Override
	public void zerarDadosJogo(TimeCamp mand, TimeCamp visit) {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			em.createQuery(
					"update Jogador j set j.qtdAmarelosJogo = 0, j.qtdVermelhosJogo = 0, j.golsPorJogo = 0 where j.time.id in("
							+ mand.getId() + ", " + visit.getId() + ")")
					.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

	}

}
