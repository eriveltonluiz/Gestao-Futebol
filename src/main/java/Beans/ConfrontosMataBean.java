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

import org.primefaces.PrimeFaces;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import Daos.controller.DaoControllerAmarelosMand;
import Daos.controller.DaoControllerAmarelosVisit;
import Daos.controller.DaoControllerConfrontosMataMata;
import Daos.controller.DaoControllerEtapaFinal;
import Daos.controller.DaoControllerGolsMand;
import Daos.controller.DaoControllerGolsVisit;
import Daos.controller.DaoControllerJogadores;
import Daos.controller.DaoControllerMandantesCamp;
import Daos.controller.DaoControllerTimesCamp;
import Daos.controller.DaoControllerVermelhosMand;
import Daos.controller.DaoControllerVermelhosVisit;
import Daos.controller.DaoControllerVisitantesCamp;
import Geral.Mensagens;
import Model.AmarelosPartidaMand;
import Model.AmarelosPartidaVisit;
import Model.CampMandante;
import Model.CampVisitante;
import Model.ConfrontosMataMata;
import Model.EtapaMataMata;
import Model.GolsPartidaMand;
import Model.GolsPartidaVisit;
import Model.Jogador;
import Model.Rodada;
import Model.TimeCamp;
import Model.VermelhosPartidaMand;
import Model.VermelhosPartidaVisit;
import Model.enums.RodadaConfirmada;

@Named(value = "confrontosBean")
@ViewScoped
public class ConfrontosMataBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String escolha = new String();
	private String timeVencedor;
	private DualListModel<Jogador> jogadores = new DualListModel<Jogador>();
	private ConfrontosMataMata proxConf = new ConfrontosMataMata();
	private List<Jogador> jogadoresSource = new ArrayList<Jogador>();
	private List<Jogador> jogadoresTarget = new ArrayList<Jogador>();
	private List<Rodada> rodadas = new ArrayList<Rodada>();
	private List<ConfrontosMataMata> confrontosMata = new ArrayList<ConfrontosMataMata>();
	ConfrontosMataMata ConfTerceiroLugar = new ConfrontosMataMata();
	private List<SelectItem> listEtapas = new ArrayList<SelectItem>();
	private EtapaMataMata etapa = new EtapaMataMata();
	private ConfrontosMataMata confronto = new ConfrontosMataMata();
	private TimeCamp timeCamp = new TimeCamp();
	TimeCamp derrotadoMand = new TimeCamp();
	TimeCamp derrotadoVisit = new TimeCamp();
	private GolsPartidaMand gpm = new GolsPartidaMand();
	private GolsPartidaVisit gpv = new GolsPartidaVisit();
	private AmarelosPartidaMand apm = new AmarelosPartidaMand();
	private AmarelosPartidaVisit apv = new AmarelosPartidaVisit();
	private VermelhosPartidaMand vpm = new VermelhosPartidaMand();
	private VermelhosPartidaVisit vpv = new VermelhosPartidaVisit();

	@Inject
	private DaoControllerJogadores daoControllerJogadores;

	@Inject
	private DaoControllerMandantesCamp daoControllerMandantesCamp;

	@Inject
	private DaoControllerVisitantesCamp daoControllerVisitantesCamp;

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

	@Inject
	private DaoControllerConfrontosMataMata daoControllerConfrontosMataMata;

	@Inject
	private DaoControllerEtapaFinal daoControllerEtapaFinal;

	@PostConstruct
	public void selecionarConfrontosMataMata() {

		if (listEtapas == null || listEtapas.isEmpty()) {
			List<EtapaMataMata> list = daoControllerEtapaFinal.listar(EtapaMataMata.class);
			for (EtapaMataMata r : list) {
				listEtapas.add(new SelectItem(r, r.getDescricao()));
			}
		}
		etapa.setDescricao("Oitavas de final");
		confrontosMata = daoControllerConfrontosMataMata.confrontosEtapa("Oitavas de final", "NAO");
	}

	public void carregarEtapas(AjaxBehaviorEvent event) {
		EtapaMataMata etapa = (EtapaMataMata) ((HtmlSelectOneMenu) event.getSource()).getValue();
		if (etapa != null) {
			confrontosMata = daoControllerConfrontosMataMata.confrontosEtapa(etapa.getDescricao(), "NAO");
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
		if (!jogadoresTarget.isEmpty() || jogadoresTarget != null) {
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
		if (!jogadoresTarget.isEmpty() || jogadoresTarget != null) {
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
			Mensagens.msgInfo("Punição confirmada com sucesso");
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
		} else {
			Mensagens.msgError("Nenhum jogador adicionado!!!");
		}
	}

	private void adicionarPlacar() {
		String tipo;
		if (timeCamp.getNome() == confronto.getMandante().getTime().getNome()) {

			CampMandante mand = confronto.getMandante();
			daoControllerMandantesCamp.atualizarResultadoFavor(mand, jogadoresTarget.size(), escolha);

			CampVisitante visit = confronto.getVisitante();
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

		else if (timeCamp.getNome() == confronto.getVisitante().getTime().getNome()) {

			CampVisitante visit = confronto.getVisitante();
			daoControllerVisitantesCamp.atualizarResultadoFavor(visit, jogadoresTarget.size(), escolha);

			CampMandante mand = confronto.getMandante();
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

		int ind = confrontosMata.indexOf(confronto);

		switch (escolha) {
		case "+":

			if (tipo == "mandante") {
				confrontosMata.get(ind).getMandante()
						.setGolsFeitos(confrontosMata.get(ind).getMandante().getGolsFeitos() + jogadoresTarget.size());

				confrontosMata.get(ind).getVisitante().setGolsTomados(
						confrontosMata.get(ind).getVisitante().getGolsTomados() + jogadoresTarget.size());
			}

			else if (tipo == "visitante") {
				confrontosMata.get(ind).getVisitante()
						.setGolsFeitos(confrontosMata.get(ind).getVisitante().getGolsFeitos() + jogadoresTarget.size());

				confrontosMata.get(ind).getMandante()
						.setGolsTomados(confrontosMata.get(ind).getMandante().getGolsTomados() + jogadoresTarget.size());
			}

			break;

		case "-":

			if (tipo == "mandante") {
				confrontosMata.get(ind).getMandante()
						.setGolsFeitos(confrontosMata.get(ind).getMandante().getGolsFeitos() - jogadoresTarget.size());

				confrontosMata.get(ind).getVisitante()
						.setGolsTomados(confrontosMata.get(ind).getVisitante().getGolsTomados() - jogadoresTarget.size());
			}

			else if (tipo == "visitante") {
				confrontosMata.get(ind).getVisitante()
						.setGolsFeitos(confrontosMata.get(ind).getVisitante().getGolsFeitos() - jogadoresTarget.size());

				confrontosMata.get(ind).getMandante()
						.setGolsTomados(confrontosMata.get(ind).getMandante().getGolsTomados() - jogadoresTarget.size());
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

	public void confirmarPlacar() {
		if (confronto.getHorario() != null && confronto.getDataJogo() != null) {
			if (daoControllerConfrontosMataMata.verificarHorario(confronto)) {
				String proxEtapa = "";
				int iden = 0;
				int ident = confronto.getIdentificador();
				TimeCamp mand = confronto.getMandante().getTime();
				TimeCamp visit = confronto.getVisitante().getTime();
				daoControllerJogadores.zerarDadosJogo(mand, visit);

				switch (confronto.getEtapa().getDescricao()) {
				case "Oitavas de final":

					if (ident == 1 || ident == 2) {
						iden = 1;
					} else if (ident == 3 || ident == 4) {
						iden = 2;
					} else if (ident == 5 || ident == 6) {
						iden = 3;
					} else {
						iden = 4;
					}

					proxEtapa = "Quartas de final";
					break;

				case "Quartas de final":
					if (ident == 1 || ident == 2) {
						iden = 1;
					} else {
						iden = 2;
					}
					proxEtapa = "Semifinal";
					break;

				case "Semifinal":
					ConfTerceiroLugar = daoControllerConfrontosMataMata.returnProxConfronto("Final", 3);
					iden = 1;
					proxEtapa = "Final";
					break;
				}

				proxConf = daoControllerConfrontosMataMata.returnProxConfronto(proxEtapa, iden);

				if (!(confronto.getMandante().getGolsFeitos() == confronto.getVisitante().getGolsFeitos())) {
					if (confronto.getEtapa().getDescricao() != "Final") {
						if ((confronto.getMandante().getGolsFeitos() > confronto.getVisitante().getGolsFeitos())
								&& confronto.getIdentificador() % 2 == 1) {

							CampMandante man = new CampMandante();
							man.setGolsFeitos(0);
							man.setGolsTomados(0);
							man.setTime(mand);
							man = daoControllerMandantesCamp.atualizar(man);

							proxConf.setMandante(man);
							daoControllerConfrontosMataMata.atualizar(proxConf);
							derrotadoMand = visit;
						}

						else if ((confronto.getMandante().getGolsFeitos() > confronto.getVisitante().getGolsFeitos())
								&& confronto.getIdentificador() % 2 == 0) {

							CampVisitante man = new CampVisitante();
							man.setGolsFeitos(0);
							man.setGolsTomados(0);
							man.setTime(mand);
							man = daoControllerVisitantesCamp.atualizar(man);

							proxConf.setVisitante(man);
							daoControllerConfrontosMataMata.atualizar(proxConf);
							derrotadoVisit = visit;
						}

						else if ((confronto.getMandante().getGolsFeitos() < confronto.getVisitante().getGolsFeitos())
								&& confronto.getIdentificador() % 2 == 0) {

							CampVisitante man = new CampVisitante();
							man.setGolsFeitos(0);
							man.setGolsTomados(0);
							man.setTime(visit);
							man = daoControllerVisitantesCamp.atualizar(man);

							proxConf.setVisitante(man);
							daoControllerConfrontosMataMata.atualizar(proxConf);
							derrotadoVisit = mand;
						}

						else if ((confronto.getMandante().getGolsFeitos() < confronto.getVisitante().getGolsFeitos())
								&& confronto.getIdentificador() % 2 == 1) {

							CampMandante man = new CampMandante();
							man.setGolsFeitos(0);
							man.setGolsTomados(0);
							man.setTime(visit);
							man = daoControllerMandantesCamp.atualizar(man);

							proxConf.setMandante(man);
							daoControllerConfrontosMataMata.atualizar(proxConf);
							derrotadoMand = mand;
						}

						if (confronto.getEtapa().getDescricao().equals("Semifinal")) {
							confirmarConfronto3Lugar(derrotadoMand, derrotadoVisit, ConfTerceiroLugar);
						}
					}

					confronto.setConfrontoConfirmado(RodadaConfirmada.SIM);
					daoControllerMandantesCamp.atualizarDadosMataMata(confronto.getMandante());
					daoControllerVisitantesCamp.atualizarDadosMataMata(confronto.getVisitante());
					daoControllerConfrontosMataMata.atualizar(confronto);
					confrontosMata.remove(confronto);
					Mensagens.msgInfo("Resultado confirmado no confronto " + confronto.getMandante().getTime().getNome()
							+ " X " + confronto.getVisitante().getTime().getNome());
				} else {
					PrimeFaces.current().executeScript("PF('cd').hide()");
					PrimeFaces.current().ajax().update("carForm:dialogVencedor");
					PrimeFaces.current().executeScript("PF('dlgEscVencedor').show()");
				}
			} else {
				Mensagens.msgError("Horário escolhido já está marcado para outro confronto");
			}
		} else {
			Mensagens.msgError("Horário ou data devem ser preenchidos");
		}
	}

	private void confirmarConfronto3Lugar(TimeCamp derrotadoMand, TimeCamp derrotadoVisit,
			ConfrontosMataMata confTerceiroLugar) {
		if (derrotadoMand.getNome() == null) {
			CampVisitante man = new CampVisitante();
			man.setGolsFeitos(0);
			man.setGolsTomados(0);
			man.setTime(derrotadoVisit);
			man = daoControllerVisitantesCamp.atualizar(man);
			confTerceiroLugar.setVisitante(man);
			daoControllerConfrontosMataMata.atualizar(confTerceiroLugar);
		} else {
			CampMandante man = new CampMandante();
			man.setGolsFeitos(0);
			man.setGolsTomados(0);
			man.setTime(derrotadoMand);
			man = daoControllerMandantesCamp.atualizar(man);
			confTerceiroLugar.setMandante(man);
			daoControllerConfrontosMataMata.atualizar(confTerceiroLugar);
		}
	}

	public void escolhaVencedor() {
		if (confronto.getMandante().getTime().getNome().equals(timeVencedor)) {
			if (confronto.getIdentificador() % 2 == 1) {
				CampMandante man = new CampMandante();
				man.setGolsFeitos(0);
				man.setGolsTomados(0);
				man.setTime(confronto.getMandante().getTime());
				man = daoControllerMandantesCamp.atualizar(man);

				proxConf.setMandante(man);
				daoControllerConfrontosMataMata.atualizar(proxConf);
				derrotadoMand = confronto.getVisitante().getTime();
			}

			else if (confronto.getIdentificador() % 2 == 0) {

				CampVisitante man = new CampVisitante();
				man.setGolsFeitos(0);
				man.setGolsTomados(0);
				man.setTime(confronto.getVisitante().getTime());
				man = daoControllerVisitantesCamp.atualizar(man);

				proxConf.setVisitante(man);
				daoControllerConfrontosMataMata.atualizar(proxConf);
				derrotadoVisit = confronto.getVisitante().getTime();
			}
		} else if (confronto.getVisitante().getTime().getNome().equals(timeVencedor)) {
			if (confronto.getIdentificador() % 2 == 0) {

				CampVisitante man = new CampVisitante();
				man.setGolsFeitos(0);
				man.setGolsTomados(0);
				man.setTime(confronto.getVisitante().getTime());
				man = daoControllerVisitantesCamp.atualizar(man);

				proxConf.setVisitante(man);
				daoControllerConfrontosMataMata.atualizar(proxConf);
				derrotadoVisit = confronto.getMandante().getTime();
			}

			else if (confronto.getIdentificador() % 2 == 1) {

				CampMandante man = new CampMandante();
				man.setGolsFeitos(0);
				man.setGolsTomados(0);
				man.setTime(confronto.getVisitante().getTime());
				man = daoControllerMandantesCamp.atualizar(man);

				proxConf.setMandante(man);
				daoControllerConfrontosMataMata.atualizar(proxConf);
				derrotadoMand = confronto.getMandante().getTime();
			}
		}

		confronto.setConfrontoConfirmado(RodadaConfirmada.SIM);
		daoControllerMandantesCamp.atualizarDadosMataMata(confronto.getMandante());
		daoControllerVisitantesCamp.atualizarDadosMataMata(confronto.getVisitante());
		daoControllerConfrontosMataMata.atualizar(confronto);
		confrontosMata.remove(confronto);
		Mensagens.msgInfo("Resultado confirmado no confronto " + confronto.getMandante().getTime().getNome() + " X "
				+ confronto.getVisitante().getTime().getNome());

		if (confronto.getEtapa().getDescricao() == "Semifinal") {
			confirmarConfronto3Lugar(derrotadoMand, derrotadoVisit, ConfTerceiroLugar);
		}
	}

	public void teste() {
		System.out.println("vsdfs");
		System.out.println("dsfcd");
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

	public ConfrontosMataMata getConfronto() {
		return confronto;
	}

	public void setConfronto(ConfrontosMataMata confronto) {
		this.confronto = confronto;
	}

	public TimeCamp getTimeCamp() {
		return timeCamp;
	}

	public void setTimeCamp(TimeCamp timeCamp) {
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

	public List<SelectItem> getListEtapas() {
		return listEtapas;
	}

	public List<ConfrontosMataMata> getConfrontosMata() {
		return confrontosMata;
	}

	public EtapaMataMata getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaMataMata etapa) {
		this.etapa = etapa;
	}

	public String getTimeVencedor() {
		return timeVencedor;
	}

	public void setTimeVencedor(String timeVencedor) {
		this.timeVencedor = timeVencedor;
	}
}
