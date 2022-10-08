package testeTCC;

import java.util.List;

import org.junit.Test;

import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.dao.DaoJdbc;
import br.com.erivelton.canchafut.dao.DaoJogador;
import br.com.erivelton.canchafut.model.Agenda;
import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.CampMandante;
import br.com.erivelton.canchafut.model.CampVisitante;
import br.com.erivelton.canchafut.model.Cliente;
import br.com.erivelton.canchafut.model.Confrontos;
import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.Jogador;
import br.com.erivelton.canchafut.model.Rodada;
import br.com.erivelton.canchafut.model.TimeCamp;
import br.com.erivelton.canchafut.model.TimeMandante;
import br.com.erivelton.canchafut.model.TimeVisitante;
import junit.framework.TestCase;

public class testMain extends TestCase{

	@Test
	public void testCria() {
		//EntityManager em = UtilBD.getEntityMangaer();
		//Dao<Cliente> dao = new Dao<Cliente>();
	}
	
	@Test
	public void test() {
		Dao<Cliente> dao = new Dao<Cliente>();
		List<Cliente> list = dao.listar(Cliente.class);
		
		for (Cliente cliente : list) {
			System.out.println(cliente.getNomeTime());
			System.out.println(cliente.getNomeUser());
			System.out.println("-------");
		}
	}
	
	@Test    //importante
	public void testAgendaJdbc() throws Exception {
		DaoJdbc dao = new DaoJdbc();
		Agenda age = new Agenda();
		//List<AgendamentoTimes> list1 = daoe.listarAgenda();
		List<AgendamentoTimes> list = dao.listarJdbcAg(age);
		
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
		
		AgendamentoTimes ag = list.get(4);
		
		//System.out.print(ag.getCliente().getNomeTime() + ", ");
		//System.out.print(ag.getVisitante().getNomeTimeVisit() + ", ");
		
		TimeVisitante tv = ag.getVisitante();
		TimeMandante tm = ag.getMandante();
		//tv.setMandante(tm);;
		System.out.println("Cliente visitante: " + tv.getCliente().getNomeTime());
		System.out.println(tv.getCliente().getNomeUser());

		System.out.println("Cliente mandante:" + tv.getMandante().getCliente().getNomeTime());
		System.out.println(tm.getCliente().getNomeUser());
	}
	
	@Test
	public void testInsereCamp() {
		//Dao<TimeCamp> dao = new Dao<TimeCamp>();
		/*TimeCamp camp = new TimeCamp("teste");
		TimeCamp camp1 = new TimeCamp("teste1");
		TimeCamp camp2 = new TimeCamp("teste2");
		TimeCamp camp3 = new TimeCamp("teste3");
		TimeCamp camp4 = new TimeCamp("teste4");
		TimeCamp camp5 = new TimeCamp("teste5");
		
		TimeCamp time = dao.atualizar(camp5);
		
		System.out.println(time.getNome());*/
	}
	
	@Test
	public void testInsereVisit() {
		Dao<CampVisitante> dao = new Dao<CampVisitante>();
		Dao<TimeCamp> dao1 = new Dao<TimeCamp>();
		
		CampVisitante v = new CampVisitante();
		TimeCamp time = dao1.retEnt(TimeCamp.class, 1L);
		v.setTime(time);
		
		CampVisitante v1 = new CampVisitante();
		TimeCamp time1 = dao1.retEnt(TimeCamp.class, 2L);
		v1.setTime(time1);
		
		CampVisitante v2 = new CampVisitante();
		TimeCamp time2 = dao1.retEnt(TimeCamp.class, 3L);
		v2.setTime(time2);
		v2.setIdVisitante(3L);
		
		dao.atualizar(v2);
		System.out.println(v2.getTime().getNome());
	}
	
	@Test
	public void testInsereMand() {
		Dao<CampMandante> dao = new Dao<CampMandante>();
		Dao<TimeCamp> dao1 = new Dao<TimeCamp>();
		
		CampMandante v = new CampMandante();
		TimeCamp time = dao1.retEnt(TimeCamp.class, 4L);
		v.setTime(time);
		
		CampMandante v1 = new CampMandante();
		TimeCamp time1 = dao1.retEnt(TimeCamp.class, 5L);
		v1.setTime(time1);
		v1.setIdMandante(2L);
		
		CampMandante v2 = new CampMandante();
		TimeCamp time2 = dao1.retEnt(TimeCamp.class, 6L);
		v2.setTime(time2);
		
		dao.atualizar(v1);
		System.out.println(v1.getTime().getNome());
	}
	
	@Test
	public void testInsereRodada() {
		Dao<Rodada> dao = new Dao<Rodada>();
		
		Rodada r = new Rodada();
		r.setDescricao("1º Rodada");
		dao.atualizar(r);
		System.out.println(r.getDescricao());
	}
	
	@Test
	public void testInsereConfrontos() {
		Dao<CampMandante> daom = new Dao<CampMandante>();
		Dao<CampVisitante> daov = new Dao<CampVisitante>();
		Dao<Rodada> daor = new Dao<Rodada>();
		Dao<Confrontos> dao = new Dao<Confrontos>();
		
		CampMandante m = daom.retEnt(CampMandante.class, 3L);
		CampVisitante v = daov.retEnt(CampVisitante.class, 3L);
		Rodada r = daor.retEnt(Rodada.class, 1L);

		Confrontos c = new Confrontos();
		c.getId().setMandante(m);
		c.getId().setVisitante(v);
		c.getId().setRodada(r);
		
		Confrontos cd = dao.atualizar(c);
		System.out.println(cd.getId().getRodada().getDescricao());
		System.out.print(cd.getId().getMandante().getTime().getNome());
		System.out.print(" X ");
		System.out.println(cd.getId().getVisitante().getTime().getNome());
	}
	
	@Test
	public void testListaConf() {    //importate
		Dao<Confrontos> dao = new Dao<Confrontos>();
		
		List<Confrontos> list = dao.listaConf();
		int desc = 0;
		for (Confrontos cd : list) {
			
			if(desc == 0) {
				System.out.println(cd.getId().getRodada().getDescricao());
				desc = 1;
			}
			
			System.out.print(cd.getId().getMandante().getTime().getNome());
			System.out.print(" X ");
			System.out.println(cd.getId().getVisitante().getTime().getNome());
		}
	}
	
	@Test    //importante
	public void testTimesAleat() {
		Dao<TimeCamp> dao = new Dao<TimeCamp>();
		List<TimeCamp> list = dao.timesAleatorios();
		
		for (TimeCamp tc : list) {
			System.out.println(tc.getNome());
			System.out.println("--------");
		}
		
	}

	@Test
	public void testTimesFetch() {
		Dao<TimeCamp> dao = new Dao<TimeCamp>();
		TimeCamp time = new TimeCamp();
		time.setNome("teste");
		time.setId(5L);
		
		TimeCamp times = dao.listarTotal(time);
		//time.getJogadores().addAll(times.getJogadores());
		for (Jogador tc : times.getJogadores()) {
			System.out.println(times.getNome());
			System.out.println(tc.getNome());
		}
		
	}
	
	@Test
	public void testQtdNum() {
		DaoJogador d = new DaoJogador();
		Dao<TimeCamp> dao = new Dao<TimeCamp>();
		Jogador j = new Jogador();
		j.setId(3L);
		j = d.retEnt(Jogador.class, j.getId());
		j.setTime(dao.retEnt(TimeCamp.class, 1L));
		
		
		System.out.println(d.verificarNumeroCamisa(j));
	}
	
	@Test
	public void testlistarTodos() {
		Dao<Grupo> dao = new Dao<Grupo>();
		List<Grupo> list = dao.listar(Grupo.class);
		int i = 0;
		for (Grupo grupo : list) {
			System.out.println(grupo.getDescricao());
			
			for (TimeCamp tc : list.get(i).getTimes()) {
				System.out.print(tc.getId() + "  ");
			}
			System.out.println("=========");
			i++;
		}
 	}
}
