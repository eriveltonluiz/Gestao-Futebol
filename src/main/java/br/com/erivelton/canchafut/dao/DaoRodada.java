package br.com.erivelton.canchafut.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import br.com.erivelton.canchafut.Interface.InterfaceRodadas;
import br.com.erivelton.canchafut.model.Confrontos;
import br.com.erivelton.canchafut.model.Rodada;
import br.com.erivelton.canchafut.model.TimeCamp;
import br.com.erivelton.canchafut.model.enums.RodadaConfirmada;

@SuppressWarnings("unchecked")
@Named
public class DaoRodada extends Dao<Rodada> implements Serializable, InterfaceRodadas {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override // aqui terá um parâmetro String rodada
	public List<Confrontos> confrontosRodada(String parametro) {
		List<Confrontos> list = new ArrayList<Confrontos>(); // será um parâmetro vindo da requisição
		String sql = "select c from Confrontos c inner join c.id.rodada r where r.descricao = '" + parametro
				+ "' and c.rodadaConfirmada != 'SIM'";
		list = em.createQuery(sql).getResultList();
		return list;
	}

	@Override
	public Rodada rodadaAtuall(String rodadaAtual) {
		Rodada rodada = (Rodada) em.createQuery("from Rodada r where r.descricao = '" + rodadaAtual + "'")
				.getSingleResult();
		return rodada;
	}

	@Override
	public List<TimeCamp> timesAleatorios() {
		List<TimeCamp> times = new ArrayList<TimeCamp>();
		times = (List<TimeCamp>) em.createQuery("SELECT t FROM TimeCamp t ORDER BY RAND()").getResultList();
		return times;
	}

	@Override
	public Rodada rodadaAtual(RodadaConfirmada confirm) {
		Rodada rodada = (Rodada) em.createQuery("select r from Rodada r where r.confirmacao = '" + confirm + "'")
				.getSingleResult();
		return rodada;
	}

	@Override
	public void inserirProximaRodada(int ident, String identificacao) {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			StoredProcedureQuery query = em.createStoredProcedureQuery("sp_inserir_rodada");

			query.registerStoredProcedureParameter("descInicial", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("ident", Integer.class, ParameterMode.IN);

			query.setParameter("descInicial", identificacao);
			query.setParameter("ident", ident);
			query.execute();
			trans.commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
