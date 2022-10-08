package br.com.erivelton.canchafut.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.erivelton.canchafut.dao.controller.DaoControllerGrupos;
import br.com.erivelton.canchafut.dao.controller.DaoControllerTimesCamp;
import br.com.erivelton.canchafut.geral.Mensagens;
import br.com.erivelton.canchafut.model.CampMandante;
import br.com.erivelton.canchafut.model.CampVisitante;
import br.com.erivelton.canchafut.model.ConfrontosTorn;
import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.TimeCamp;

@Named(value = "gruposBean")
@ViewScoped
public class GruposBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Grupo> armazenarGrupos = new ArrayList<Grupo>();
	private List<Grupo> grupos = new ArrayList<Grupo>();
	private List<TimeCamp> times = new ArrayList<TimeCamp>();
	private List<TimeCamp> timesTorn = new ArrayList<TimeCamp>();
	private List<ConfrontosTorn> confrontos = new ArrayList<ConfrontosTorn>();
	private int contadorTimes = 0;
	int inserido;
	boolean mandVisit = false;
	private Grupo grupo = new Grupo();

	@Inject
	private DaoControllerGrupos daoContollerGrupos;

	@Inject
	private DaoControllerTimesCamp daoControllerTimes;

	@PostConstruct
	public void listaGrupos() {
		grupos = daoContollerGrupos.listar(Grupo.class);
		times = daoControllerTimes.timesAleatorios();
	}

	public void listarFormatoTorneio() {
		List<Integer> removidos = new ArrayList<Integer>();
		Random random = new Random();
		timesTorn = daoControllerTimes.timesAleatorios();
		for (int i = 0; i < 20; i++) {

			for (int j = 0; j < 2; j++) {
				int randM = random.nextInt(19);
				int randV = random.nextInt(19);

				inserido = 0;

				while (randM == randV) {
					randM = random.nextInt(19);
					randV = random.nextInt(19);
				}

				for (int k = 0; k < removidos.size(); k++) {
					if (removidos.get(k).equals(randM)) {
						while (randM == removidos.get(k)) {
							randM = random.nextInt(19);
						}
					} else if (removidos.get(k).equals(randV)) {
						while (randV == removidos.get(k)) {
							randV = random.nextInt(19);
						}
					}
				}

				TimeCamp tcAux = timesTorn.get(randM);
				TimeCamp tc = timesTorn.get(randV);

				CampMandante mand = new CampMandante();
				CampVisitante visit = new CampVisitante();
				ConfrontosTorn conf = new ConfrontosTorn();

				if (confrontos.size() > 0) {
					for (int k = 0; k < confrontos.size(); k++) {
						/*
						 * if(confrontos.get(k).getMandante().getQtdConfrontos() == 2 &&
						 * confrontos.get(k).getVisitante().getQtdconf() == 2) { j = 2; inserido = 2;
						 * break; }
						 */

						if (confrontos.get(k).getVisitante().getTime().getNome().equals(tcAux.getNome())) {
							inserido++;
						}

						else if (confrontos.get(k).getMandante().getTime().getNome().equals(tcAux.getNome())) {
							inserido++;
						}

						if (inserido == 2) {
							j = 2;
							break;
						}
					}
				}

				if (inserido < 2) {
					if (inserido == 0) {
						visit.setQtdconf(1);
						mand.setQtdConfrontos(1);
					} else if (inserido == 1) {
						visit.setQtdconf(2);
						mand.setQtdConfrontos(2);
						removidos.add(randM);
						removidos.add(randV);
					}
					visit.setTime(tcAux);
					mand.setTime(tc);
					conf.setCampMandante(mand);
					conf.setVisitante(visit);
					confrontos.add(conf);
				}

			}
		}
	}

	public String inserirTimesNoGrupo() {

		if (armazenarGrupos.size() == grupos.size()) {
			
			IntStream.range(0, armazenarGrupos.size()).forEach(indice -> {
				Grupo grupoAux = armazenarGrupos.get(indice);
					
				IntStream.range(0, grupoAux.quantidadeTimesDoGrupo()).forEach(indiceTimes -> {
						TimeCamp inserirTime = grupoAux.timeASerInserido(indiceTimes);
						Grupo entGrupo = new Grupo(grupoAux.getId(), grupoAux.getDescricao());
						inserirTime.setGrupo(entGrupo);
						daoControllerTimes.atualizar(inserirTime);
				});
			});

			
			
//			for (int i = 0; i < armazenarGrupos.size(); i++) {
//				Grupo grupoAux = armazenarGrupos.get(i);
//
//				for (int j = 0; j < grupoAux.getTimes().size(); j++) {
//					TimeCamp inserirTime = grupoAux.timeASerInserido(j);
//					Grupo entGrupo = new Grupo(grupoAux.getId(), grupoAux.getDescricao());
//					inserirTime.setGrupo(entGrupo);
//					daoControllerTimes.atualizar(inserirTime);
//				}
//			}
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext context = facesContext.getExternalContext();
			context.getSessionMap().remove("sorteio");
			return "principalCamp.jsf?faces-redirect=true";
		} else {
			Mensagens.msgWarn("Necessário selecionar todos os grupos!!");
		}
		return "";
		// int qtdTimes = times.size();
		// int qtdTimesNoGrupo = qtdTimes / grupos.size();

		// int timesRestantes = (qtdTimes % grupos.size());

		/*
		 * for(int i = 1; i <= grupos.size(); i++) { grupo = new Grupo();
		 * grupo.setDescricao("Grupo " + i); grupo =
		 * daoContollerGrupos.returnObjGrupo(grupo.getDescricao());
		 * 
		 * for(j = 1; j < qtdTimesNoGrupo; j++) { TimeCamp time = new TimeCamp(); time =
		 * times.get(j); time.setGrupo(grupo);
		 * 
		 * time = daoControllerTimes.atualizar(time); } }
		 */
	}

	public void listarTimesAleatorios() {
		times = daoControllerTimes.timesAleatorios();
	}

	public void alinharTimesNoGrupo() {
		int qtdTimes = times.size();
		int qtdTimesNoGrupo = qtdTimes / grupos.size();

		Iterator<TimeCamp> iter = grupo.iterador();

		if (iter.hasNext() == false) {

			int indiceAtual = qtdTimesNoGrupo + contadorTimes;

			for (; contadorTimes < indiceAtual; contadorTimes++) {
				grupo.adicionarTimes(times.get(contadorTimes));
			}

			contadorTimes = indiceAtual;
			armazenarGrupos.add(grupo);
		}
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<ConfrontosTorn> getConfrontos() {
		return confrontos;
	}
}
