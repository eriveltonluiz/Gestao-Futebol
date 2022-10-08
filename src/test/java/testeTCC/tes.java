package testeTCC;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.dao.DaoConfronto;
import br.com.erivelton.canchafut.dao.DaoJogador;
import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.Jogador;
import br.com.erivelton.canchafut.model.TimeCamp;

public class tes {

	@Test
	public void test() {
		DaoJogador d = new DaoJogador();
		Dao<TimeCamp> dao = new Dao<TimeCamp>();
		Jogador j = new Jogador();
		j.setId(10L);
		j = d.retEnt(Jogador.class, j.getId());
		j.setTime(dao.retEnt(TimeCamp.class, 2L));
		Long qdt = d.verificarNumeroCamisa(j);
		
		System.out.println(qdt);

	}
	
	@Test
	public void testConsSimples() {
		Dao<Grupo> d = new Dao<Grupo>();
		Grupo g = new Grupo();
		g.setDescricao("Grupo 4");
		
		Grupo ga = d.returnEntQuery(g);
		
		System.out.println(ga.getId() + " " + ga.getDescricao());
	}
	
	@Test
	public void testCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		System.out.println(cal.getTime().toString());
	}
	
	@Test    //importante
	public void testAgendaJpa() throws Exception {
		Dao<AgendamentoTimes> dao = new Dao<AgendamentoTimes>();
		//List<AgendamentoTimes> list1 = daoe.listarAgenda();
		List<AgendamentoTimes> list = dao.listarAgenda(AgendamentoTimes.class);
		
		for (AgendamentoTimes ag : list) {
			System.out.println(ag.getId());
			System.out.print(ag.getMandante().getHorario() + ", ");
			System.out.print(ag.getMandante().getDia() + ", ");
			System.out.print(ag.getCliente().getNomeTime() + ", ");
			System.out.print("X  ");
			System.out.print(ag.getVisitante().getNomeTimeVisit() + ", ");
			System.out.println(ag.getStatusHorario());
		
			System.out.println("-------");
		}
	}

	@Test    //importante
	public void testLong() throws Exception {
		DaoConfronto dao = new DaoConfronto();
		TimeCamp mand = new TimeCamp();
		TimeCamp visit = new TimeCamp();
		mand.setNome("Tsunami");
		visit.setNome("ferrugem");
		System.out.println(dao.verificarConfrontosRepetidos(mand, visit));
		//List<AgendamentoTimes> list1 = daoe.listarAgenda();
	}
	
//	@Test    //importante
//	public void testPendJdbc() throws Exception {
//		DaoJdbc dao = new DaoJdbc();
//		Cliente cliente = new Cliente();
//		cliente.setIdCliente(1L);
//		//List<AgendamentoTimes> list1 = daoe.listarAgenda();
//		List<PagMandante> list = dao.listaPendencias(cliente);
//		
//		for (PagMandante ag : list) {
//			System.out.println(ag.getIdPagMandante());
//			System.out.print(ag.getValorPago() + ", ");
//			System.out.print(ag.getValorPendente() + ", ");
//		
//			System.out.println("-------");
//		}
//	}
}
