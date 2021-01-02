package Daos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import Interfaces.InterfaceAgenda;
import Model.Agenda;

public class DaoAgenda implements InterfaceAgenda {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	@Override
	public boolean verificarAgendaExistente(Date data) {
		String sql = "select count(1) as qtd from Agenda where data = '"
				+ new SimpleDateFormat("yyyy-MM-dd").format(data) + "'";
		Long qtd = (Long) em.createQuery(sql).getSingleResult();

		if (qtd == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void criarAgendaPorData(Agenda agenda, Calendar dia) {
		try {
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			StoredProcedureQuery query = em.createStoredProcedureQuery("sp_agendaTimes");

			query.registerStoredProcedureParameter("dia", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("idAgenda", Long.class, ParameterMode.IN);

			query.setParameter("dia", dia.get(Calendar.DAY_OF_WEEK));
			query.setParameter("idAgenda", agenda.getIdAgenda());
			query.execute();
			trans.commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Agenda retornarRegistroPorData(Date data) {
		Agenda agenda = (Agenda) em
				.createQuery(
						"from Agenda where data = '" + new SimpleDateFormat("yyyy-MM-dd").format(data) + "'")
				.getSingleResult();
		return agenda;
	}
}
