package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import Daos.controller.DaoControllerConfrontosCamp;
import Daos.controller.DaoControllerConfrontosMataMata;
import Daos.controller.DaoControllerEtapaFinal;
import Daos.controller.DaoControllerGrupos;
import Daos.controller.DaoControllerJogadores;
import Daos.controller.DaoControllerMandantesCamp;
import Daos.controller.DaoControllerRodada;
import Daos.controller.DaoControllerTimesCamp;
import Daos.controller.DaoControllerVisitantesCamp;
import Geral.Mensagens;
import Model.CampMandante;
import Model.CampVisitante;
import Model.Confrontos;
import Model.ConfrontosMataMata;
import Model.EtapaMataMata;
import Model.Grupo;
import Model.Jogador;
import Model.Rodada;
import Model.TimeCamp;
import Model.enums.RodadaConfirmada;

@Named(value = "principalCampBean")
@ViewScoped
public class PrincipalCampBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String etapaInicial;
	private List<Grupo> tabelas = new ArrayList<Grupo>();
	private List<Jogador> artilharia = new ArrayList<Jogador>();
	private List<Jogador> amarelados = new ArrayList<Jogador>();
	private List<Jogador> vermelhos = new ArrayList<Jogador>();
	private List<TimeCamp> list = new ArrayList<TimeCamp>();
	List<TimeCamp> timesCad = new ArrayList<TimeCamp>();
	private List<TimeCamp> timesCampeonato = new ArrayList<TimeCamp>();
	private List<SelectItem> grupos = new ArrayList<SelectItem>();
	private List<SelectItem> times = new ArrayList<SelectItem>();
	private List<SelectItem> timesVisitantes = new ArrayList<SelectItem>();
	private List<SelectItem> timesAux = new ArrayList<SelectItem>();
	private List<ConfrontosMataMata> oitavas = new ArrayList<ConfrontosMataMata>();
	private List<ConfrontosMataMata> quartas = new ArrayList<ConfrontosMataMata>();
	private List<ConfrontosMataMata> semifinais = new ArrayList<ConfrontosMataMata>();
	private List<ConfrontosMataMata> finais = new ArrayList<ConfrontosMataMata>();
	private Grupo grupo = new Grupo();
	private TimeCamp timeCamp = new TimeCamp();
	private TimeCamp mandante = new TimeCamp();
	private TimeCamp visitante = new TimeCamp();
	private Confrontos confronto = new Confrontos();
	private Rodada rodada = new Rodada();

	@Inject
	private DaoControllerGrupos daoContollerGrupos;

	@Inject
	private DaoControllerJogadores daoControllerJogadores;

	@Inject
	private DaoControllerMandantesCamp daoContollerMandantesCamp;

	@Inject
	private DaoControllerVisitantesCamp daoContollerVisitantesCamp;

	@Inject
	private DaoControllerConfrontosCamp daoContollerConfrontosCamp;

	@Inject
	private DaoControllerConfrontosMataMata daoContollerConfrontosMataMata;

	@Inject
	private DaoControllerRodada daoControllerRodada;

	@Inject
	private DaoControllerTimesCamp daoControllerTimesCamp;

	@Inject
	private DaoControllerEtapaFinal daoControllerEtapaFinal;

	@PostConstruct
	public void listarTabelas() {
		tabelas = daoContollerGrupos.listar(Grupo.class);
		listarClassificacaoTimes();
		listarIndices();
	}

	public String listarTimes() {
		if (timesCampeonato.isEmpty() || timesCampeonato == null) {
			timesCampeonato = daoControllerTimesCamp.listar(TimeCamp.class);
		}
		return "times?faces-redirect=true";
	}

	private void listarClassificacaoTimes() {

		for (int i = 0; i < tabelas.size(); i++) {
			Collections.sort(tabelas.get(i).getTimes(), Comparator.comparing(TimeCamp::getPontos)
					.thenComparingInt(TimeCamp::getSaldoDeGols).thenComparingInt(TimeCamp::getGolsPro).reversed());
			/*
			 * Collections.sort(tabelas.get(i).getTimes(), new Comparator<TimeCamp>() {
			 * 
			 * @Override public int compare(TimeCamp o1, TimeCamp o2) { int comp =
			 * o1.getPontos().compareTo(o2.getPontos()); if (comp != 0) { // names are
			 * different int compSaldoGols =
			 * o1.getSaldoDeGols().compareTo(o2.getSaldoDeGols()); if(compSaldoGols != 0) {
			 * return compSaldoGols; } return o1.getGolsPro() - o2.getGolsPro(); } return
			 * t.getAge() - t1.getAge(); return 0; } });
			 */
		}
	}

	private void listarIndices() {
		for (int i = 0; i < tabelas.size(); i++) {
			for (int j = 0; j < tabelas.get(i).getTimes().size(); j++) {
				tabelas.get(i).getTimes().get(j).setIndice(j + 1);
			}
		}
	}

	public void carregarArtilharia() {
		artilharia = daoControllerJogadores.listarArtilharia();
	}

	public void carregarAmarelados() {
		amarelados = daoControllerJogadores.listarAmarelados();
	}

	public void carregarVermelhos() {
		vermelhos = daoControllerJogadores.listarVermelhos();
	}

	public void carregaTimes(AjaxBehaviorEvent event) {
		novo();
		Grupo grupoEscolhido = (Grupo) ((HtmlSelectOneMenu) event.getSource()).getValue();

		if (grupoEscolhido != null) {
			List<SelectItem> selectItemsTimes = new ArrayList<SelectItem>();
			List<SelectItem> selectItemsTimesVisitantes = new ArrayList<SelectItem>();
			List<SelectItem> selectItemsTimesAux = new ArrayList<SelectItem>();

			if (list == null || list.isEmpty()) {
				List<Confrontos> confRodada = new ArrayList<Confrontos>();
				int ind = tabelas.indexOf(grupoEscolhido);
				for (TimeCamp timeCamp : tabelas.get(ind).getTimes()) {
					list.add(timeCamp);
				}
				confRodada = daoContollerConfrontosCamp.confrontosRodada(rodada, grupoEscolhido);
				for (Confrontos c : confRodada) {
					timesCad.add(c.getMandante().getTime());
					timesCad.add(c.getVisitante().getTime());
				}

				for (int i = 0; i < timesCad.size(); i++) {
					for (int j = 0; j < list.size(); j++) {
						if (timesCad.get(i).getId() == list.get(j).getId()) {
							list.remove(j);
							break;
						}
					}
				}
			}

			for (TimeCamp tc : list) {
				selectItemsTimes.add(new SelectItem(tc, tc.getNome()));
				selectItemsTimesVisitantes.add(new SelectItem(tc, tc.getNome()));
				selectItemsTimesAux.add(new SelectItem(tc, tc.getNome()));
			}
			setTimes(selectItemsTimes);
			setTimesAux(selectItemsTimesAux);
			setTimesVisitantes(selectItemsTimesVisitantes);
		}
	}

	public void removerItem(AjaxBehaviorEvent event) {
		TimeCamp camp = (TimeCamp) ((HtmlSelectOneMenu) event.getSource()).getValue();

		if (camp != null) {
			SelectItem aux = new SelectItem(camp, camp.getNome());
			timesVisitantes = new ArrayList<SelectItem>(timesAux);

			for (int i = 0; i < timesVisitantes.size(); i++) {
				if (timesVisitantes.get(i).getLabel() == aux.getLabel()) {
					timesVisitantes.remove(i);
					break;
				}
			}
		} else if (camp == null && visitante != null) {
			times = new ArrayList<SelectItem>(timesAux);
			timesVisitantes = new ArrayList<SelectItem>();
			visitante = new TimeCamp();
			PrimeFaces.current().ajax().update("formPrinc:comboMandantes");
			PrimeFaces.current().resetInputs("formPrinc:comboVisitantes");
		}
	}

	public void removerItemMandante(AjaxBehaviorEvent event) {
		TimeCamp camp = (TimeCamp) ((HtmlSelectOneMenu) event.getSource()).getValue();

		if (camp != null) {
			SelectItem aux = new SelectItem(camp, camp.getNome());
			times = new ArrayList<SelectItem>(timesAux);

			for (int i = 0; i < times.size(); i++) {
				if (times.get(i).getLabel() == aux.getLabel()) {
					times.remove(i);
					break;
				}
			}
		} else if (camp == null && (mandante != null || mandante == null)) {
			times = new ArrayList<SelectItem>(timesAux);
			timesVisitantes = new ArrayList<SelectItem>();
			PrimeFaces.current().ajax().update("formPrinc:comboVisitantes");
		}
	}

	public void adicionarConfrontos() {
		confronto.setRodada(rodada);
		if (daoContollerConfrontosCamp.verificarHorario(confronto)) {
			if (daoContollerConfrontosCamp.verificarConfrontosRepetidos(mandante, visitante)) {
				CampMandante campMandante = adicionarMandante();
				CampVisitante campVisitante = adicionarVisitante();
				confronto.setVisitante(campVisitante);
				confronto.setCampMandante(campMandante);
				confronto.setRodadaConfirmada(RodadaConfirmada.NAO);
				confronto = daoContollerConfrontosCamp.atualizar(confronto);

				// inserir automaticamente aqui o placar de 0 a 0 do confronto seja com trigger
				// ou manualmente

				visitante = new TimeCamp();
				mandante = new TimeCamp();
				grupo = new Grupo();
				confronto = new Confrontos();
				times = new ArrayList<SelectItem>();
				timesVisitantes = new ArrayList<SelectItem>();
				Mensagens.msgInfo("Confronto confirmado com sucesso.");
			} else {
				Mensagens.msgError("Confronto já duelado!!");
			}
		} else {
			Mensagens.msgError("Horário escolhido já está marcado para outro confronto!!");
		}
	}

	private CampVisitante adicionarVisitante() {
		CampVisitante campVisitante = new CampVisitante();
		campVisitante.setTime(visitante);
		campVisitante.setGolsFeitos(0);
		campVisitante.setGolsTomados(0);
		campVisitante = daoContollerVisitantesCamp.atualizar(campVisitante);
		return campVisitante;
	}

	private CampMandante adicionarMandante() {
		CampMandante campMandante = new CampMandante();
		campMandante.setTime(mandante);
		campMandante.setGolsFeitos(0);
		campMandante.setGolsTomados(0);
		campMandante = daoContollerMandantesCamp.atualizar(campMandante);
		return campMandante;
	}

	public String anularRegistrosCamp() {
		daoContollerConfrontosCamp.zerarCampeonato();
		return "/cadastro-times-camp.jsf?faces-redirect=true";
	}

	public void listaItensGrupos() {
		if (daoControllerEtapaFinal.qtdRegistrosGeral(EtapaMataMata.class) == 0) {
			if (daoControllerRodada.qtdRegistrosGeral(Rodada.class) == 0) {
				rodada.setDescricao("1º Rodada");
				rodada.setConfirmacao(RodadaConfirmada.NAO);
				rodada.setIdentificacao(1);
				rodada = daoControllerRodada.atualizar(rodada);
			} else {
				rodada = daoControllerRodada.rodadaAtual(RodadaConfirmada.NAO);
			}

			if (grupos.isEmpty() || grupos == null) {
				for (Grupo grupo : tabelas) {
					grupos.add(new SelectItem(grupo, grupo.getDescricao()));
				}
			}
			PrimeFaces.current().executeScript("PF('dlgRodada').show()");
		} else {
			Mensagens.msgError("Jogos da fase de grupo foram encerrados!!");
		}
	}

	public void confirmarRodada() {
		rodada.setConfirmacao(RodadaConfirmada.SIM);
		rodada = daoControllerRodada.atualizar(rodada);
		int identificacao = rodada.getIdentificacao() + 1;
		daoControllerRodada.inserirProximaRodada(identificacao, Integer.toString(identificacao));
		Mensagens.msgInfo("Rodada confirmada com sucesso!!");
	}

	public void novo() {
		timesVisitantes = new ArrayList<SelectItem>();
		times = new ArrayList<SelectItem>();
		timesAux = new ArrayList<SelectItem>();
		mandante = new TimeCamp();
		visitante = new TimeCamp();
		list = null;
		list = new ArrayList<TimeCamp>();
	}

	public void OrganizarConfMataMata() {
		if (daoContollerConfrontosCamp.verificarConfrontosPendentes()) {
			if (daoControllerEtapaFinal.qtdRegistrosGeral(EtapaMataMata.class) == 0) {
				EtapaMataMata oitavas = new EtapaMataMata();
				EtapaMataMata quartas = new EtapaMataMata();
				EtapaMataMata semi = new EtapaMataMata();
				EtapaMataMata finall = new EtapaMataMata();
				List<Grupo> gruposAux = new ArrayList<Grupo>(tabelas);
				List<EtapaMataMata> etapas = new ArrayList<EtapaMataMata>();
				daoContollerConfrontosMataMata.inserirEtapas(etapaInicial);
				etapas = daoControllerEtapaFinal.listar(EtapaMataMata.class);

				for (EtapaMataMata em : etapas) {
					switch (em.getDescricao()) {
					case "Oitavas de final":
						oitavas = em;
						break;

					case "Quartas de final":
						quartas = em;
						break;

					case "Semifinal":
						semi = em;
						break;

					case "Final":
						finall = em;
						break;

					}
				}

				Collections.shuffle(gruposAux);

				int qtdClassificados = 0;
				List<ConfrontosMataMata> confs = new ArrayList<ConfrontosMataMata>();
				switch (etapaInicial) {
				case "Oitavas de final":

					if (16 % gruposAux.size() == 0) {
						qtdClassificados = 16 / gruposAux.size();
						inicioMataMata(qtdClassificados, gruposAux, confs, oitavas);
						sequenciaMataMata(4, gruposAux, confs, quartas);
						sequenciaMataMata(2, gruposAux, confs, semi);
						sequenciaMataMata(2, gruposAux, confs, finall);
					} else {
						Mensagens.msgError("Não é possível mata mata à partir das oitavas!!");
					}
					break;

				case "Quartas de final":
					if (8 % gruposAux.size() == 0) {
						qtdClassificados = 8 / gruposAux.size();
						inicioMataMata(qtdClassificados, gruposAux, confs, quartas);
					} else {
						Mensagens.msgError("Não é possível mata mata à partir das quartas!!");
					}
					break;
				}
			} else {
				Mensagens.msgWarn("Fase final já acrescentada!!");
			}
		} else {
			Mensagens.msgWarn("Confrontos da fase de grupos ainda pendentes!!");
		}
	}

	private void inicioMataMata(int qtdClassificados, List<Grupo> gruposAux, List<ConfrontosMataMata> confs,
			EtapaMataMata etapa) {
		int cont = 1;
		for (int i = 0; i < gruposAux.size(); i += 2) {
			for (int j = 0, k = qtdClassificados - 1; j < qtdClassificados; j++, k--) {
				CampMandante mand = new CampMandante();
				CampVisitante visit = new CampVisitante();
				mand.setGolsFeitos(0);
				mand.setGolsTomados(0);
				visit.setGolsFeitos(0);
				visit.setGolsTomados(0);

				if (k < j) {
					mand.setTime(gruposAux.get(i + 1).getTimes().get(k));
					visit.setTime(gruposAux.get(i).getTimes().get(j));
				} else {
					mand.setTime(gruposAux.get(i).getTimes().get(j));
					visit.setTime(gruposAux.get(i + 1).getTimes().get(k));
				}
				daoContollerMandantesCamp.salvar(mand);
				daoContollerVisitantesCamp.salvar(visit);

				ConfrontosMataMata conf = new ConfrontosMataMata();
				conf.setConfrontoConfirmado(RodadaConfirmada.NAO);
				conf.setEtapa(etapa);
				conf.setMandante(mand);
				conf.setVisitante(visit);
				conf.setIdentificador(cont);
				confs.add(conf);
				daoContollerConfrontosMataMata.salvar(conf);
				cont++;
			}
		}
	}

	private void sequenciaMataMata(int qtdClassificados, List<Grupo> gruposAux, List<ConfrontosMataMata> confs,
			EtapaMataMata etapa) {
		int cont = 1;
		for (int j = 0; j < qtdClassificados; j++) {
			/*
			 * CampMandante mand = new CampMandante(); CampVisitante visit = new
			 * CampVisitante(); mand.setGolsFeitos(0); mand.setGolsTomados(0);
			 * visit.setGolsFeitos(0); visit.setGolsTomados(0);
			 */

			ConfrontosMataMata conf = new ConfrontosMataMata();
			conf.setConfrontoConfirmado(RodadaConfirmada.NAO);
			conf.setIdentificador(cont);
			conf.setEtapa(etapa);
			confs.add(conf);
			daoContollerConfrontosMataMata.salvar(conf);
			if (etapa.getDescricao().equals("Final") && cont == 1) {
				cont += 2;
			} else {
				cont++;
			}
		}
	}

	public TimeCamp getMandante() {
		return mandante;
	}

	public void setMandante(TimeCamp mandante) {
		this.mandante = mandante;
	}

	public TimeCamp getVisitante() {
		return visitante;
	}

	public void setVisitante(TimeCamp visitante) {
		this.visitante = visitante;
	}

	public List<Grupo> getTabelas() {
		return tabelas;
	}

	public List<SelectItem> getTimes() {
		return times;
	}

	public void setTimes(List<SelectItem> times) {
		this.times = times;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<SelectItem> getGrupos() {
		return grupos;
	}

	public TimeCamp getTimeCamp() {
		return timeCamp;
	}

	public void setTimeCamp(TimeCamp timeCamp) {
		this.timeCamp = timeCamp;
	}

	public List<SelectItem> getTimesVisitantes() {
		return timesVisitantes;
	}

	public void setTimesVisitantes(List<SelectItem> timesVisitantes) {
		this.timesVisitantes = timesVisitantes;
	}

	public Confrontos getConfronto() {
		return confronto;
	}

	public void setConfronto(Confrontos confronto) {
		this.confronto = confronto;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public List<TimeCamp> getList() {
		return list;
	}

	public void setTimesAux(List<SelectItem> timesAux) {
		this.timesAux = timesAux;
	}

	public List<Jogador> getArtilharia() {
		return artilharia;
	}

	public List<Jogador> getAmarelados() {
		return amarelados;
	}

	public List<Jogador> getVermelhos() {
		return vermelhos;
	}

	public List<TimeCamp> getTimesCampeonato() {
		return timesCampeonato;
	}

	public String getEtapaInicial() {
		return etapaInicial;
	}

	public void setEtapaInicial(String etapaInicial) {
		this.etapaInicial = etapaInicial;
	}

	public List<ConfrontosMataMata> getOitavas() {
		return oitavas;
	}

	public List<ConfrontosMataMata> getQuartas() {
		return quartas;
	}

	public List<ConfrontosMataMata> getSemifinais() {
		return semifinais;
	}

	public List<ConfrontosMataMata> getFinais() {
		return finais;
	}

}
