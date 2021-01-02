package Daos;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import Interfaces.InterfaceVisitanteCamp;
import Model.CampVisitante;

public class DaoCampVisitante extends Dao<CampVisitante> implements InterfaceVisitanteCamp {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public void atualizarResultadoFavor(CampVisitante visitante, int qtd, String escolha) {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.createNativeQuery("update campvisitante e set e.golsFeitos = e.golsFeitos " + escolha + " " + qtd
				+ " where e.idVisitante = " + visitante.getIdVisitante()).executeUpdate();
		trans.commit();
	}

	@Override
	public void atualizarResultadoContra(CampVisitante visitante, int qtd, String escolha) {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.createNativeQuery("update campvisitante e set e.golsTomados = e.golsTomados " + escolha + " " + qtd
				+ " where e.idVisitante = " + visitante.getIdVisitante()).executeUpdate();
		trans.commit();
	}

	@Override
	public void atualizarSaldoDeGols(CampVisitante confronto) {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_dadosResultado");

		query.registerStoredProcedureParameter("idtime", Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("golsFeitos", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("golsTomados", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("qtdempates", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("qtdvitorias", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("qtdderrotas", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("qtdpontos", Integer.class, ParameterMode.IN);

		query.setParameter("idtime", confronto.getTime().getId());
		query.setParameter("golsFeitos", confronto.getGolsFeitos());
		query.setParameter("golsTomados", confronto.getGolsTomados());
		query.setParameter("qtdempates", confronto.getTime().getEmpates());
		query.setParameter("qtdvitorias", confronto.getTime().getVitorias());
		query.setParameter("qtdderrotas", confronto.getTime().getDerrotas());
		query.setParameter("qtdpontos", confronto.getTime().getPontos());
		query.execute();

		trans.commit();
	}

	@Override
	public void atualizarDadosMataMata(CampVisitante confronto) {
		EntityTransaction trans = em.getTransaction();
		trans.begin();

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_dadosResultadoMataMata");

		query.registerStoredProcedureParameter("idtime", Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("golsFeitos", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("golsTomados", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("qtdgolspro", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("qtdgolscontra", Integer.class, ParameterMode.IN);

		query.setParameter("idtime", confronto.getTime().getId());
		query.setParameter("golsFeitos", confronto.getGolsFeitos());
		query.setParameter("golsTomados", confronto.getGolsTomados());
		query.setParameter("qtdgolspro", confronto.getTime().getGolsPro());
		query.setParameter("qtdgolscontra", confronto.getTime().getGolsContra());
		query.execute();
		trans.commit();
	}

}
