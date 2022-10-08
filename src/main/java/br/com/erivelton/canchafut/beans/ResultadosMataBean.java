package br.com.erivelton.canchafut.beans;

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

import br.com.erivelton.canchafut.dao.controller.DaoControllerAmarelosMand;
import br.com.erivelton.canchafut.dao.controller.DaoControllerAmarelosVisit;
import br.com.erivelton.canchafut.dao.controller.DaoControllerConfrontosMataMata;
import br.com.erivelton.canchafut.dao.controller.DaoControllerEtapaFinal;
import br.com.erivelton.canchafut.dao.controller.DaoControllerGolsMand;
import br.com.erivelton.canchafut.dao.controller.DaoControllerGolsVisit;
import br.com.erivelton.canchafut.dao.controller.DaoControllerVermelhosMand;
import br.com.erivelton.canchafut.dao.controller.DaoControllerVermelhosVisit;
import br.com.erivelton.canchafut.model.AmarelosPartidaMand;
import br.com.erivelton.canchafut.model.AmarelosPartidaVisit;
import br.com.erivelton.canchafut.model.ConfrontosMataMata;
import br.com.erivelton.canchafut.model.EtapaMataMata;
import br.com.erivelton.canchafut.model.GolsPartidaMand;
import br.com.erivelton.canchafut.model.GolsPartidaVisit;
import br.com.erivelton.canchafut.model.TimeCamp;
import br.com.erivelton.canchafut.model.VermelhosPartidaMand;
import br.com.erivelton.canchafut.model.VermelhosPartidaVisit;

@Named(value = "resultadosMataBean")
@ViewScoped
public class ResultadosMataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String escolha = new String();
	private List<ConfrontosMataMata> confrontos = new ArrayList<ConfrontosMataMata>();
	private List<EtapaMataMata> etapas = new ArrayList<EtapaMataMata>();
	private List<GolsPartidaMand> golsMand = new ArrayList<GolsPartidaMand>();
	private List<GolsPartidaVisit> golsVisit = new ArrayList<GolsPartidaVisit>();
	private List<AmarelosPartidaVisit> amarelosVisit = new ArrayList<AmarelosPartidaVisit>();
	private List<AmarelosPartidaMand> amarelosMand = new ArrayList<AmarelosPartidaMand>();
	private List<VermelhosPartidaMand> vermelhosMand = new ArrayList<VermelhosPartidaMand>();
	private List<VermelhosPartidaVisit> vermelhosVisit = new ArrayList<VermelhosPartidaVisit>();
	private List<SelectItem> listEtapas = new ArrayList<SelectItem>();
	private ConfrontosMataMata confronto = new ConfrontosMataMata();
	private EtapaMataMata etapa = new EtapaMataMata();
	private TimeCamp timeCamp = new TimeCamp();

	@Inject
	private DaoControllerEtapaFinal daoControllerEtapaFinal;

	@Inject
	private DaoControllerConfrontosMataMata daoControllerConfrontosMata;

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
		if(listEtapas == null || listEtapas.isEmpty()) {
			etapas = daoControllerEtapaFinal.listar(EtapaMataMata.class);
			for (EtapaMataMata r : etapas) {
				listEtapas.add(new SelectItem(r, r.getDescricao()));
			}
		}
		etapa.setDescricao("Oitavas de final");
		confrontos = daoControllerConfrontosMata.resultadosConfrontosEtapa("Oitavas de final", "SIM");
	}
	
	public void carregarEtapas(AjaxBehaviorEvent event) {
		EtapaMataMata etapaSelec = (EtapaMataMata) ((HtmlSelectOneMenu) event.getSource()).getValue();
		if(etapaSelec != null) {
			confrontos = daoControllerConfrontosMata.resultadosConfrontosEtapa(etapaSelec.getDescricao(), "SIM");
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

	public List<ConfrontosMataMata> getConfrontos() {
		return confrontos;
	}
	
	public EtapaMataMata getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaMataMata etapa) {
		this.etapa = etapa;
	}
	
	public ConfrontosMataMata getConfronto() {
		return confronto;
	}

	public void setConfronto(ConfrontosMataMata confronto) {
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

	public List<EtapaMataMata> getEtapas() {
		return etapas;
	}
	
	public List<SelectItem> getListEtapas() {
		return listEtapas;
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
