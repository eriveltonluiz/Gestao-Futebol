package Daos;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.StoredProcedureQuery;

import ConexaoBD.UtilBD;
import Model.Cliente;
import Model.Confrontos;
import Model.GolsPartidaMand;
import Model.GolsPartidaVisit;
import Model.Login;
import Model.TimeCamp;

@Named
@SuppressWarnings("unchecked")
public class Dao<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Inject
	private UtilBD util;

	public void salvar(E ent) {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			em.persist(ent);
			trans.commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public Login returnUsuario(String usuario, String senha) {
		try {
			Login login = (Login) em
					.createQuery("from Login l where l.usuario = '" + usuario + "' and l.senha = '" + senha + "'")
					.getSingleResult();
			return login;
		} catch (Exception e) {
			return null;
		}
	}

	public E retEnt(Class<E> entidade, Long id) {
		E e = (E) em.createQuery("from " + entidade.getClass() + " where id = " + id).getSingleResult();
		return e;
	}

	public E returnEntQuery(E entidade) {
		Long id = (Long) util.getPrimaryKey(entidade);
		E e = (E) em.find(entidade.getClass(), id);
		return e;
	}

	public GolsPartidaMand returnUmaLinhaMand(GolsPartidaMand mand) {
		GolsPartidaMand ret = (GolsPartidaMand) em.createQuery("from GolsPartidaMand g where g.jogador.id = " + mand.getJogador().getId()
				+ " and g.mandante.idMandante = " + mand.getMandante().getIdMandante()).setMaxResults(1).getSingleResult();
		return ret;
	}
	
	public GolsPartidaVisit returnUmaLinhaVisit(GolsPartidaVisit visit) {
		GolsPartidaVisit ret = (GolsPartidaVisit) em.createQuery("from GolsPartidaVisit g where g.jogador.id = " + visit.getJogador().getId()
				+ " and g.visitante.idVisitante = " + visit.getVisitante().getIdVisitante()).setMaxResults(1).getSingleResult();
		return ret;
	}
	
	public E listarResultSimples(Class<E> entidade) {
		E list = (E) em.createQuery("from " + entidade.getName()).getSingleResult();
		return list;
	}

	public List<E> listar(Class<E> entidade) {
		List<E> list = em.createQuery("from " + entidade.getName()).getResultList();
		return list;
	}

	public Long qtdRegistros(Class<E> entidade) {
		Long qtd = (Long) em.createQuery("select count(*) from " + entidade.getName()).getSingleResult();
		return qtd;
	}

	public TimeCamp listarTotal(TimeCamp time) {
		try {
			TimeCamp list = (TimeCamp) em.createQuery("select o from " + time.getClass().getName()
					+ " o join fetch o.jogadores where o.id = " + time.getId()).getSingleResult();
			return list;
		} catch (NoResultException e) {
			return null;
		}
	}

	public E atualizar(E ent) {

		try {
			em.clear();
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			E e = em.merge(ent);
			trans.commit();
			return e;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	public void remover(E ent) {
		try {
			em.clear();
			EntityTransaction trans = em.getTransaction();
			Object id = util.getPrimaryKey(ent);
			trans.begin();
			em.remove(em.getReference(ent.getClass(), id));
			trans.commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	public Long qtdRegistrosGeral(Class<E> entidade) {
		Long qtd = (Long) em.createQuery("select count(*) from " + entidade.getName()).getSingleResult();
		return qtd;
	}

	public List<Confrontos> listaConf() {
		em.clear();
		List<Confrontos> list = new ArrayList<Confrontos>(); // será um parâmetro vindo da requisição
		String sql = "select c from Confrontos c inner join c.id.rodada r where r.descricao = '1º Rodada'";
		list = em.createQuery(sql).getResultList();
		return list;
	}

	public List<E> listarAgenda(Class<E> entidade) throws ParseException {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		int num = 1;
		String dia = "'segunda-feira'";

		String sql = "SELECT v FROM AgendamentoTimes as t RIGHT JOIN t.mandante AS m "
				+ "LEFT JOIN m.cliente AS c RIGHT JOIN t.agenda AS a " + "LEFT JOIN m.visitantes as v "
				+ "WHERE a.idAgenda = " + num + " and m.dia = " + dia;

		List<E> list = em.createQuery(sql).getResultList();
		trans.commit();
		return list;
	}

	public Cliente lista(String nomeCliente) {
		Cliente e = (Cliente) em.createQuery("from Cliente c where c.nomeTime = '" + nomeCliente + "'")
				.getSingleResult();
		return e;
	}

	public List<TimeCamp> timesAleatorios() {
		List<TimeCamp> times;
		times = (List<TimeCamp>) em.createQuery("SELECT t FROM TimeCamp t ORDER BY RAND()").getResultList();
		return times;
	}

	public void zerarCampeonato() {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			StoredProcedureQuery query = em.createStoredProcedureQuery("sp_zerar_campeonato");
			query.execute();
			trans.commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
