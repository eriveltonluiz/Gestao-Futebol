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

import Daos.controller.DaoControllerAmarelosMand;
import Daos.controller.DaoControllerAmarelosVisit;
import Daos.controller.DaoControllerConfrontosCamp;
import Daos.controller.DaoControllerGolsMand;
import Daos.controller.DaoControllerGolsVisit;
import Daos.controller.DaoControllerRodada;
import Daos.controller.DaoControllerVermelhosMand;
import Daos.controller.DaoControllerVermelhosVisit;
import Model.AmarelosPartidaMand;
import Model.AmarelosPartidaVisit;
import Model.Confrontos;
import Model.GolsPartidaMand;
import Model.GolsPartidaVisit;
import Model.Rodada;
import Model.TimeCamp;
import Model.VermelhosPartidaMand;
import Model.VermelhosPartidaVisit;

@Named(value = "resultadosBean")
@ViewScoped
public class ResultadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String escolha = new String();
	private List<Confrontos> confrontos = new ArrayList<Confrontos>();
	private List<Rodada> rodadas = new ArrayList<Rodada>();
	private List<GolsPartidaMand> golsMand = new ArrayList<GolsPartidaMand>();
	private List<GolsPartidaVisit> golsVisit = new ArrayList<GolsPartidaVisit>();
	private List<AmarelosPartidaVisit> amarelosVisit = new ArrayList<AmarelosPartidaVisit>();
	private List<AmarelosPartidaMand> amarelosMand = new ArrayList<AmarelosPartidaMand>();
	private List<VermelhosPartidaMand> vermelhosMand = new ArrayList<VermelhosPartidaMand>();
	private List<VermelhosPartidaVisit> vermelhosVisit = new ArrayList<VermelhosPartidaVisit>();
	private List<SelectItem> listRodadas = new ArrayList<SelectItem>();
	private Confrontos confronto = new Confrontos();
	private Rodada rodada = new Rodada();
	private TimeCamp timeCamp = new TimeCamp();

	@Inject
	private DaoControllerRodada daoControllerRodada;

	@Inject
	private DaoControllerConfrontosCamp daoControllerConfrontosCamp;

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
	public void listaConfrontos() {
		if(listRodadas == null || listRodadas.isEmpty()) {
			rodadas = daoControllerRodada.listar(Rodada.class);
			for (Rodada r : rodadas) {
				listRodadas.add(new SelectItem(r, r.getDescricao()));
			}
		}
		rodada.setDescricao("1º Rodada");
		confrontos = daoControllerConfrontosCamp.resultadosRodada(1L);
	}
	
	public void carregarRodadas(AjaxBehaviorEvent event) {
		Rodada rodadaSelec = (Rodada) ((HtmlSelectOneMenu) event.getSource()).getValue();
		if(rodadaSelec != null) {
			confrontos = daoControllerConfrontosCamp.resultadosRodada(rodadaSelec.getId());
		}
	}
	
	public void selecionarDados() {
		golsMand = daoControllerGolsMand.autoresPartida(confronto.getMandante());
		golsVisit = daoControllerGolsVisit.autoresPartida(confronto.getVisitante());
		
		amarelosMand = daoControllerAmarelosMand.amarelosPartida(confronto.getMandante());
		amarelosVisit = daoControllerAmarelosVisit.amarelosPartida(confronto.getVisitante());
		
		vermelhosMand = daoControllerVermelhosMand.vermelhosPartida(confronto.getMandante());
		vermelhosVisit = daoControllerVermelhosVisit.vermelhosPartida(confronto.getVisitante());
	}

	public void novo() {
		timeCamp = new TimeCamp();
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

	public List<GolsPartidaMand> getGolsMand() {
		return golsMand;
	}

	public List<GolsPartidaVisit> getGolsVisit() {
		return golsVisit;
	}

	public List<AmarelosPartidaVisit> getAmarelosVisit() {
		return amarelosVisit;
	}

	public List<AmarelosPartidaMand> getAmarelosMand() {
		return amarelosMand;
	}

	public List<VermelhosPartidaMand> getVermelhosMand() {
		return vermelhosMand;
	}

	public List<VermelhosPartidaVisit> getVermelhosVisit() {
		return vermelhosVisit;
	}

}
