package Daos;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import Interfaces.InterfaceConfrontoMataMata;
import Model.ConfrontosMataMata;

public class DaoConfrontoMataMata extends Dao<ConfrontosMataMata> implements InterfaceConfrontoMataMata {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public void inserirEtapas(String etapaInicial) {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_mata_mata");

		query.registerStoredProcedureParameter("etapaInicial", String.class, ParameterMode.IN);

		query.setParameter("etapaInicial", etapaInicial);
		query.execute();

		trans.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfrontosMataMata> resultadosConfrontosEtapa(String etapa, String status) {
		List<ConfrontosMataMata> list = em
				.createQuery("SELECT c FROM ConfrontosMataMata c WHERE c.confrontoConfirmado = '" + status
						+ "' AND c.etapa.descricao = '" + etapa + "' order by c.id")
				.getResultList();
		return list;
	}

	@Override
	public ConfrontosMataMata returnProxConfronto(String etapa, int ident) {
		ConfrontosMataMata conf = (ConfrontosMataMata) em
				.createQuery("select c from ConfrontosMataMata c where c.etapa.descricao = '" + etapa
						+ "' and c.identificador = " + ident)
				.getSingleResult();
		return conf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfrontosMataMata> confrontosEtapa(String etapa, String status) {
		try {
			List<ConfrontosMataMata> list = em
					.createQuery("SELECT c FROM ConfrontosMataMata c WHERE c.confrontoConfirmado = '" + status
							+ "' AND c.etapa.descricao = '" + etapa
							+ "' AND c.mandante.idMandante != NULL AND c.visitante.idVisitante != NULL order by c.id")
					.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean verificarHorario(ConfrontosMataMata confronto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHora = new SimpleDateFormat("yyyy-MM-dd");
		Long qtd = (Long) em.createQuery("select count(*) from ConfrontosMataMata c where c.etapa.id = "
				+ confronto.getEtapa().getId() + " and c.horario = '" + sdfHora.format(confronto.getHorario()) + "' and c.dataJogo = '"
				+ sdf.format(confronto.getDataJogo()) + "'").getSingleResult();
		if (qtd == 0) {
			return true;
		}
		return false;
	}

}
