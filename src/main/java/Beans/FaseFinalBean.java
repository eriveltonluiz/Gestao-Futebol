package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Daos.controller.DaoControllerConfrontosMataMata;
import Model.ConfrontosMataMata;

@Named(value = "faseFinalBean")
@ViewScoped
public class FaseFinalBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<ConfrontosMataMata> oitavas = new ArrayList<ConfrontosMataMata>();
	private List<ConfrontosMataMata> quartas = new ArrayList<ConfrontosMataMata>();
	private List<ConfrontosMataMata> semifinais = new ArrayList<ConfrontosMataMata>();
	private List<ConfrontosMataMata> finais = new ArrayList<ConfrontosMataMata>();
	
	@Inject
	private DaoControllerConfrontosMataMata daoContollerConfrontosMataMata;
	
	@PostConstruct
	public void selecionarConfrontosMataMata() {
		List<ConfrontosMataMata> confs = daoContollerConfrontosMataMata.listar(ConfrontosMataMata.class);
		for (ConfrontosMataMata cm : confs) {
			switch (cm.getEtapa().getDescricao()) {
			case "Oitavas de final":
				oitavas.add(cm);
				break;

			case "Quartas de final":
				quartas.add(cm);
				break;

			case "Semifinal":
				semifinais.add(cm);
				break;

			case "Final":
				finais.add(cm);
				break;
			}
		}
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
