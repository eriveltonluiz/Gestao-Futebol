package Daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import ConexaoBD.UtilBD;
import ConexaoBD.UtilBDJdbc;
import Model.Agenda;
import Model.AgendamentoTimes;
import Model.Cliente;
import Model.PagMandante;
import Model.TimeMandante;
import Model.TimeVisitante;

public class DaoJdbc implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	private EntityManager em = UtilBD.getEntityMangaer();

	public DaoJdbc() {
		con = UtilBDJdbc.getConexao();
	}

	public Cliente listaCliente(String nomeCliente) {
		Cliente e = (Cliente) em.createQuery("from Cliente c where c.nomeTime = '" + nomeCliente + "'")
				.getSingleResult();
		return e;
	}

	public List<AgendamentoTimes> listarJdbcAg(Agenda agendaEsc) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(agendaEsc.getData());
		int diaInt = cal.get(Calendar.DAY_OF_WEEK);
		String dia = "";

		switch (diaInt) {
		case 1:
			dia = "'domingo'";
			break;

		case 2:
			dia = "'segunda-feira'";
			break;

		case 3:
			dia = "'terça-feira'";
			break;

		case 4:
			dia = "'quarta-feira'";
			break;

		case 5:
			dia = "'quinta-feira'";
			break;

		case 6:
			dia = "'sexta-feira'";
			break;

		case 7:
			dia = "'sábado'";
			break;
		}

		String sql = "SELECT * FROM agendamentotimes AS t RIGHT JOIN timemandante AS m ON t.mandante_idMandante = m.idMandante "
				+ "LEFT JOIN cliente as c on c.idCliente = m.cliente_idCliente RIGHT JOIN agenda AS a ON t.agenda_idAgenda = a.idAgenda "
				+ "LEFT JOIN timevisitante AS v ON m.idMandante = v.mandante_idMandante AND v.data = a.data "
				+ "WHERE a.idAgenda = " + agendaEsc.getIdAgenda() + " and m.dia = " + dia;

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);

			List<AgendamentoTimes> list = new ArrayList<AgendamentoTimes>();

			while (rs.next()) {
				AgendamentoTimes bean = new AgendamentoTimes();

				Agenda agenda = new Agenda();
				agenda.setIdAgenda(rs.getLong("idAgenda"));
				agenda.setData(rs.getDate("data"));

				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getLong("idCliente"));
				cliente.setNomeTime(rs.getString("nomeTime"));
				cliente.setNomeUser(rs.getString("nomeUser"));

				TimeMandante mandante = new TimeMandante();
				mandante.setIdMandante(rs.getLong("idMandante"));
				mandante.setHorario(rs.getDate("horario"));
				mandante.setDia(rs.getString("dia"));
				mandante.setCliente(cliente);

				TimeVisitante visitante = new TimeVisitante();
				visitante.setIdVisitante(rs.getLong("idVisitante"));
				visitante.setNomeTimeVisit(rs.getString("nomeTimeVisit"));
				visitante.setMandante(mandante);
				if (visitante.getNomeTimeVisit() != null) {
					Cliente clienteAux = listaCliente(visitante.getNomeTimeVisit());
					clienteAux.setNomeTime(visitante.getNomeTimeVisit());
					visitante.setCliente(clienteAux);
				}

				bean.setId(rs.getLong("id"));
				bean.setStatusHorario(rs.getString("statusHorario"));
				bean.setMandante(mandante);
				bean.setCliente(cliente);
				bean.setVisitante(visitante);
				bean.setAgenda(agenda);
				list.add(bean);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PagMandante> listaHorariosConfirmados(Cliente cliente) {
		String sql = "select valorPendente,dataPag,valorPago, null as situacao from timemandante as t "
				+ "inner join pagmandante as p on p.mandante_idMandante = t.idMandante "
				+ "inner join cliente as c on c.idCliente = t.cliente_idCliente "
				+ "where p.valorPendente = 0 and t.cliente_idCliente = " + cliente.getIdCliente() + " union "
				+ "select v.valorPendente,v.data, v.valorPago, v.nometimevisit from cliente as c "
				+ "inner join timevisitante as v on v.cliente_idCliente = c.idCliente "
				+ "where v.valorPendente = 0 and c.idCliente = " + cliente.getIdCliente();
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			List<PagMandante> list = new ArrayList<PagMandante>();

			while (rs.next()) {
				PagMandante pag = new PagMandante();
				pag.setDataPag(rs.getDate("dataPag"));
				pag.setValorPago(rs.getDouble("valorPago"));
				pag.setValorPendente(rs.getDouble("valorPendente"));
				pag.setSituacao(rs.getString("situacao"));
				list.add(pag);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PagMandante> listaPendencias(Cliente cliente) {
		String sql = "select valorPendente,dataPag,valorPago, null as situacao from timemandante as t "
				+ "inner join pagmandante as p on p.mandante_idMandante = t.idMandante "
				+ "inner join cliente as c on c.idCliente = t.cliente_idCliente "
				+ "where p.valorPendente > 0 and t.cliente_idCliente = " + cliente.getIdCliente() + " union "
				+ "select v.valorPendente,v.data, v.valorPago, v.nometimevisit from cliente as c "
				+ "inner join timevisitante as v on v.cliente_idCliente = c.idCliente "
				+ "where v.valorPendente > 0 and c.idCliente = " + cliente.getIdCliente();
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			List<PagMandante> list = new ArrayList<PagMandante>();

			while (rs.next()) {
				PagMandante pag = new PagMandante();
				pag.setDataPag(rs.getDate("dataPag"));
				pag.setValorPago(rs.getDouble("valorPago"));
				pag.setValorPendente(rs.getDouble("valorPendente"));
				pag.setSituacao(rs.getString("situacao"));
				list.add(pag);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
