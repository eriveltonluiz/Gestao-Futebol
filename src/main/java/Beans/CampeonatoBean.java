package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import Daos.controller.DaoControllerAmarelosMand;
import Daos.controller.DaoControllerAmarelosVisit;
import Daos.controller.DaoControllerConfrontosCamp;
import Daos.controller.DaoControllerGolsMand;
import Daos.controller.DaoControllerGolsVisit;
import Daos.controller.DaoControllerJogadores;
import Daos.controller.DaoControllerMandantesCamp;
import Daos.controller.DaoControllerRodada;
import Daos.controller.DaoControllerTimesCamp;
import Daos.controller.DaoControllerVermelhosMand;
import Daos.controller.DaoControllerVermelhosVisit;
import Daos.controller.DaoControllerVisitantesCamp;
import Geral.Mensagens;
import Model.AmarelosPartidaMand;
import Model.AmarelosPartidaVisit;
import Model.CampMandante;
import Model.CampVisitante;
import Model.Confrontos;
import Model.GolsPartidaMand;
import Model.GolsPartidaVisit;
import Model.Jogador;
import Model.Rodada;
import Model.TimeCamp;
import Model.VermelhosPartidaMand;
import Model.VermelhosPartidaVisit;
import Model.enums.RodadaConfirmada;

@Named(value = "campeonatoBean")
@ViewScoped
public class CampeonatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String escolha = new String();
	private DualListModel<Jogador> jogadores = new DualListModel<Jogador>();
	private List<Jogador> jogadoresSource = new ArrayList<Jogador>();
	private List<Jogador> jogadoresTarget = new ArrayList<Jogador>();
	private List<Confrontos> confrontos = new ArrayList<Confrontos>();
	private List<Rodada> rodadas = new ArrayList<Rodada>();
	private List<SelectItem> listRodadas = new ArrayList<SelectItem>();
	private Confrontos confronto = new Confrontos();
	private Rodada rodada = new Rodada();
	private TimeCamp timeCamp = new TimeCamp();
	private GolsPartidaMand gpm = new GolsPartidaMand();
	private GolsPartidaVisit gpv = new GolsPartidaVisit();
	private AmarelosPartidaMand apm = new AmarelosPartidaMand();
	private AmarelosPartidaVisit apv = new AmarelosPartidaVisit();
	private VermelhosPartidaMand vpm = new VermelhosPartidaMand();
	private VermelhosPartidaVisit vpv = new VermelhosPartidaVisit();

	@Inject
	private DaoControllerRodada daoControllerRodada;

	@Inject
	private DaoControllerJogadores daoControllerJogadores;

	@Inject
	private DaoControllerMandantesCamp daoControllerMandantesCamp;

	@Inject
	private DaoControllerVisitantesCamp daoControllerVisitantesCamp;

	@Inject
	private DaoControllerConfrontosCamp daoControllerConfrontosCamp;

	@Inject
	private DaoControllerTimesCamp daoControllerTimesCamp;
	
	@Inject
	private DaoControllerGolsMand daoControllerGolsMand;
	
	@Inject
	private DaoControllerGolsVisit daoControllerGolsVisit;
	
	@Inject
	private DaoControllerAmarelosMand daoControllerAmarelosMand;
	
	@Inject
	private DaoControllerAmarelosVisit daoControllerAmarelosVisit;
	
	@Inject
	private DaoControllerVermelhosMand daoControllerVermelhosMand;
	
	@Inject
	private DaoControllerVermelhosVisit daoControllerVermelhosVisit;
	
	@PostConstruct
	public void listaDeConfrontos() {
		if(listRodadas == null || listRodadas.isEmpty()) {
			rodadas = daoControllerRodada.listar(Rodada.class);
			for (Rodada r : rodadas) {
				listRodadas.add(new SelectItem(r, r.getDescricao()));
			}
		}
		rodada.setDescricao("1º Rodada");
		confrontos = daoControllerRodada.confrontosRodada("1º Rodada");
	}
	
	public void carregarRodadas(AjaxBehaviorEvent event) {
		Rodada rodadaSelec = (Rodada) ((HtmlSelectOneMenu) event.getSource()).getValue();
		if(rodadaSelec != null) {
			confrontos = daoControllerRodada.confrontosRodada(rodadaSelec.getDescricao());
		}
	}

	public void listarJogadores() {
		jogadoresSource = daoControllerJogadores.listarJogadoresTime(timeCamp);
		jogadores = new DualListModel<Jogador>(jogadoresSource, jogadoresTarget);
		setEscolha("+");
	}

	public void listarAmarelados() {
		jogadoresSource = daoControllerJogadores.listarJogadoresAmarelados(timeCamp);
		jogadores = new DualListModel<Jogador>(jogadoresSource, jogadoresTarget);
	}
	
	public void listarVermelhos() {
		jogadoresSource = daoControllerJogadores.listarJogadoresVermelhos(timeCamp);
		jogadores = new DualListModel<Jogador>(jogadoresSource, jogadoresTarget);
	}
	
	public void incluirCartoesAmarelos() {
		int qtd = 0;
		if(!jogadoresTarget.isEmpty() || jogadoresTarget != null) {
			for (Jogador j : jogadoresTarget) {
				daoControllerJogadores.atualizarQtdCartoesAmarelos(j, 1);
				qtd++;
				
				if (timeCamp.getNome() == confronto.getMandante().getTime().getNome()) {
					apm.setJogador(j);
					apm.setMandante(confronto.getMandante());
					daoControllerAmarelosMand.atualizar(apm);
				} else {
					apv.setJogador(j);
					apv.setVisitante(confronto.getVisitante());
					daoControllerAmarelosVisit.atualizar(apv);
				}
			}
			daoControllerTimesCamp.atualizarCartoesAmarelosTime(timeCamp, qtd);
			Mensagens.msgInfo("Punição confirmada com sucesso.");
		} else {
			Mensagens.msgError("Nenhum jogador adicionado!!!");
		}
	}
	
	public void incluirCartoesVermelhos() {
		int qtd = 0;
		if(!jogadoresTarget.isEmpty() || jogadoresTarget != null) {
			for (Jogador j : jogadoresTarget) {
				daoControllerJogadores.atualizarQtdCartoesVermelhos(j, 1);
				qtd++;
				if (timeCamp.getNome() == confronto.getMandante().getTime().getNome()) {
					vpm.setJogador(j);
					vpm.setMandante(confronto.getMandante());
					daoControllerVermelhosMand.atualizar(vpm);
				} else {
					vpv.setJogador(j);
					vpv.setVisitante(confronto.getVisitante());
					daoControllerVermelhosVisit.atualizar(vpv);
				}
					
			}
			daoControllerTimesCamp.atualizarCartoesAmarelosTime(timeCamp, qtd);
			Mensagens.msgInfo("Punição confirmada com sucesso.");
		} else {
			Mensagens.msgError("Nenhum jogador adicionado!!!");
		}
	}

	public void adicionarJog(TransferEvent event) {
		for (Object obj : jogadores.getTarget()) {
			jogadoresTarget.add((Jogador) obj);
		}
	}

	public void manipularResultados() {
		if (!(jogadoresTarget.isEmpty()) || jogadoresTarget != null) {
			for (Jogador j : jogadoresTarget) {
				daoControllerJogadores.atualizarQtdGols(j, 1, escolha);
			}
			adicionarPlacar();
			Mensagens.msgInfo("Resultado alterado com sucesso.");
			novo();
		} else {
			Mensagens.msgError("Nenhum jogador adicionado!!!");
		}
	}

	private void adicionarPlacar() {
		String tipo;
		if (timeCamp.getNome() == confronto.getMandante().getTime().getNome()) {

			CampMandante mand = confronto.getId().getMandante();
			daoControllerMandantesCamp.atualizarResultadoFavor(mand, jogadoresTarget.size(), escolha);

			CampVisitante visit = confronto.getId().getVisitante();
			daoControllerVisitantesCamp.atualizarResultadoContra(visit, jogadoresTarget.size(), escolha);
			tipo = "mandante";
			for (Jogador j : jogadoresTarget) {
				gpm.setMandante(mand);
				gpm.setJogador(j);
				switch (escolha) {
				case "+":
					daoControllerGolsMand.atualizar(gpm);
					break;
				
				case "-":
					gpm = daoControllerGolsMand.returnUmaLinhaMand(gpm);
					daoControllerGolsMand.remover(gpm);
					break;
				}
				gpm = new GolsPartidaMand();
			}
			atualizarResultado(tipo);
		}

		else if (timeCamp.getNome() == confronto.getId().getVisitante().getTime().getNome()) {

			CampVisitante visit = confronto.getId().getVisitante();
			daoControllerVisitantesCamp.atualizarResultadoFavor(visit, jogadoresTarget.size(), escolha);

			CampMandante mand = confronto.getId().getMandante();
			daoControllerMandantesCamp.atualizarResultadoContra(mand, jogadoresTarget.size(), escolha);
			tipo = "visitante";
			for (Jogador j : jogadoresTarget) {
				gpv.setVisitante(visit);
				gpv.setJogador(j);
				
				switch (escolha) {
				case "+":
					daoControllerGolsVisit.atualizar(gpv);
					break;
				
				case "-":
					gpv = daoControllerGolsVisit.returnUmaLinhaVisit(gpv);
					daoControllerGolsVisit.remover(gpv);
					break;
				}
				
				gpv = new GolsPartidaVisit();
			}
			atualizarResultado(tipo);
		}
		Mensagens.msgInfo("Resultado alterado com sucesso.");
	}

	private void atualizarResultado(String tipo) {

		int ind = confrontos.indexOf(confronto);

		switch (escolha) {
		case "+":

			if (tipo == "mandante") {
				confrontos.get(ind).getMandante()
						.setGolsFeitos(confrontos.get(ind).getMandante().getGolsFeitos() + jogadoresTarget.size());
				
				confrontos.get(ind).getVisitante()
					.setGolsTomados(confrontos.get(ind).getVisitante().getGolsTomados() + jogadoresTarget.size());
			}

			else if (tipo == "visitante") {
				confrontos.get(ind).getVisitante()
						.setGolsFeitos(confrontos.get(ind).getVisitante().getGolsFeitos() + jogadoresTarget.size());
				
				confrontos.get(ind).getMandante()
					.setGolsTomados(confrontos.get(ind).getMandante().getGolsTomados() + jogadoresTarget.size());
			}

			break;

		case "-":

			if (tipo == "mandante") {
				confrontos.get(ind).getMandante()
						.setGolsFeitos(confrontos.get(ind).getMandante().getGolsFeitos() - jogadoresTarget.size());
				
				confrontos.get(ind).getVisitante()
				.setGolsTomados(confrontos.get(ind).getVisitante().getGolsTomados() - jogadoresTarget.size());
			}

			else if (tipo == "visitante") {
				confrontos.get(ind).getVisitante()
						.setGolsFeitos(confrontos.get(ind).getVisitante().getGolsFeitos() - jogadoresTarget.size());
				
				confrontos.get(ind).getMandante()
						.setGolsTomados(confrontos.get(ind).getMandante().getGolsTomados() - jogadoresTarget.size());
			}

			break;
		}
	}

	public void listarjogadGols() {
		jogadoresSource = daoControllerJogadores.jogadoresComGols(timeCamp);
		jogadores = new DualListModel<Jogador>(jogadoresSource, jogadoresTarget);
		setEscolha("-");
	}

	public void novo() {
		timeCamp = new TimeCamp();
		jogadoresSource = new ArrayList<Jogador>();
		jogadoresTarget = new ArrayList<Jogador>();
		jogadores = new DualListModel<Jogador>();
	}

	public void confirmarPlacarJogo() {

		TimeCamp mand = confronto.getMandante().getTime();
		TimeCamp visit = confronto.getVisitante().getTime();
		daoControllerJogadores.zerarDadosJogo(mand, visit);
		
		if (confronto.getMandante().getGolsFeitos() > confronto.getVisitante().getGolsFeitos()) {
			mand.setPontos(3);
			mand.setVitorias(1);
			mand.setDerrotas(0);
			mand.setEmpates(0);
			visit.setDerrotas(1);
			visit.setPontos(0);
			visit.setVitorias(0);
			visit.setEmpates(0);
		}

		else if (confronto.getMandante().getGolsFeitos() < confronto.getVisitante().getGolsFeitos()) {
			mand.setPontos(0);
			mand.setVitorias(0);
			mand.setDerrotas(1);
			mand.setEmpates(0);
			visit.setDerrotas(0);
			visit.setPontos(3);
			visit.setVitorias(1);
			visit.setEmpates(0);
		}

		else if (confronto.getMandante().getGolsFeitos() == confronto.getVisitante().getGolsFeitos()) {
			mand.setPontos(1);
			mand.setVitorias(0);
			mand.setDerrotas(0);
			mand.setEmpates(1);
			visit.setDerrotas(0);
			visit.setPontos(1);
			visit.setVitorias(0);
			visit.setEmpates(1);
		}

		confronto.getMandante().setTime(mand);
		confronto.getVisitante().setTime(visit);
		confronto.setRodadaConfirmada(RodadaConfirmada.SIM);
		daoControllerMandantesCamp.atualizarSaldoDeGols(confronto.getMandante());
		daoControllerVisitantesCamp.atualizarSaldoDeGols(confronto.getVisitante());
		daoControllerConfrontosCamp.atualizar(confronto);
		confrontos.remove(confronto);
		Mensagens.msgInfo("Resultado confirmado no confronto " + confronto.getMandante().getTime().getNome() + " X "
				+ confronto.getVisitante().getTime().getNome() + ".");
	}
	
	public void novoConf() {
		confronto = new Confrontos();
	}

	public List<Confrontos> getConfrontos() {
		return confrontos;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public DualListModel<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(DualListModel<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public List<Jogador> getJogadoresSource() {
		return jogadoresSource;
	}

	public void setJogadoresSource(List<Jogador> jogadoresSource) {
		this.jogadoresSource = jogadoresSource;
	}

	public List<Jogador> getJogadoresTarget() {
		return jogadoresTarget;
	}

	public void setJogadoresTarget(List<Jogador> jogadoresTarget) {
		this.jogadoresTarget = jogadoresTarget;
	}

	public Confrontos getConfronto() {
		return confronto;
	}

	public void setConfronto(Confrontos confronto) {
		this.confronto = confronto;
	}

	public TimeCamp gettimeCamp() {
		return timeCamp;
	}

	public void settimeCamp(TimeCamp timeCamp) {
		this.timeCamp = timeCamp;
	}

	public String getEscolha() {
		return escolha;
	}

	public void setEscolha(String escolha) {
		this.escolha = escolha;
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}
	
	public List<SelectItem> getListRodadas() {
		return listRodadas;
	}

	public VermelhosPartidaMand getVpm() {
		return vpm;
	}

	public void setVpm(VermelhosPartidaMand vpm) {
		this.vpm = vpm;
	}

	public VermelhosPartidaVisit getVpv() {
		return vpv;
	}

	public void setVpv(VermelhosPartidaVisit vpv) {
		this.vpv = vpv;
	}

}
