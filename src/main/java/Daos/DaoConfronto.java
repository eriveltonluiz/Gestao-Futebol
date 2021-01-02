package Daos;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import Interfaces.InterfaceConfronto;
import Model.Confrontos;
import Model.Grupo;
import Model.Rodada;
import Model.TimeCamp;

@SuppressWarnings("unchecked")
public class DaoConfronto extends Dao<Confrontos> implements InterfaceConfronto {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Override
	public Confrontos atualizarPlacarUnico(Confrontos confronto) {
		Confrontos conf = (Confrontos) em.createQuery("select c from Confrontos c where c.id.mandante.idMandante = "
				+ confronto.getMandante().getIdMandante()).getSingleResult();
		return conf;
	}

	@Override
	public List<Confrontos> confrontosRodada(Rodada rodada, Grupo grupo) {
		List<Confrontos> list = em.createQuery(
				"SELECT c FROM Confrontos AS c INNER JOIN c.id.mandante AS m INNER JOIN c.id.visitante AS v"
						+ "INNER JOIN m.time AS t WHERE c.id.rodada.id = " + rodada.getId() + " AND t.grupo.id = "
						+ grupo.getId())
				.getResultList();
		return list;
	}

	@Override
	public List<Confrontos> resultadosRodada(Long id) {
		List<Confrontos> list = new ArrayList<Confrontos>(); // será um parâmetro vindo da requisição
		String sql = "SELECT c from Confrontos c where c.id.rodada.id = " + id
				+ " and c.rodadaConfirmada = 'SIM' ORDER BY c.horario ASC";
		list = em.createQuery(sql).getResultList();
		return list;
	}

	@Override
	public boolean verificarConfrontosRepetidos(TimeCamp mand, TimeCamp visit) {
		BigInteger qtd = (BigInteger) em
				.createNativeQuery("SELECT count(1) FROM Confrontos AS c INNER JOIN CampVisitante AS v "
						+ "ON v.idvisitante = c.visitante_id INNER JOIN CampMandante AS m "
						+ "ON m.idmandante = c.mandante_id INNER JOIN TimeCamp AS t "
						+ "ON t.id = v.time_id INNER JOIN TimeCamp AS tc" + " ON tc.id = m.time_id WHERE t.nome IN('"
						+ mand.getNome() + "', '" + visit.getNome() + "') AND tc.nome IN('" + mand.getNome() + "', '"
						+ visit.getNome() + "')")
				.getSingleResult();
		if (qtd.longValue() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean verificarHorario(Confrontos confronto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
		String hora = sdfHora.format(confronto.getHorario());
		String data = sdf.format(confronto.getDataJogo());

		Long qtd = (Long) em.createQuery("select count(*) from Confrontos c where c.id.rodada.id = "
				+ confronto.getRodada().getId() + " and c.horario = '" + hora
				+ "' and c.dataJogo = '" + data + "'").getSingleResult();
		if (qtd == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean verificarConfrontosPendentes() {
		BigInteger qtd = BigInteger.valueOf((Long) em
				.createQuery("select count(*) from Confrontos c where c.rodadaConfirmada = 'NAO'").getSingleResult());
		if (qtd.longValue() == 0) {
			return true;
		}
		return false;
	}
}
