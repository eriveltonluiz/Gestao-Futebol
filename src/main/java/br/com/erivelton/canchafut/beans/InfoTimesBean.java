package br.com.erivelton.canchafut.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.erivelton.canchafut.dao.controller.DaoControllerJogadores;
import br.com.erivelton.canchafut.dao.controller.DaoControllerTimesCamp;
import br.com.erivelton.canchafut.model.Jogador;
import br.com.erivelton.canchafut.model.TimeCamp;

@Named(value = "infoBean")
@ViewScoped
public class InfoTimesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Comparator<Jogador> maisGols;
	private Comparator<Jogador> maisAmarelos;
	private Comparator<Jogador> maisVermelhos;
	private TimeCamp time = new TimeCamp();
	private Jogador jogadorMaisGols = new Jogador();
	private Jogador jogadorMaisAmarelos = new Jogador();
	private Jogador jogadorMaisVermelhos = new Jogador();
	private List<TimeCamp> times = new ArrayList<TimeCamp>();
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	private List<Jogador> listJogadores = new ArrayList<Jogador>();

	@Inject
	private DaoControllerTimesCamp daoControllerTimes;

	@Inject
	private DaoControllerJogadores daoControllerJogadores;

	@PostConstruct
	public void listaTimes() {
		if (times.isEmpty() || times == null)
			times = daoControllerTimes.listar(TimeCamp.class);
	}

	public void jogadoresTime() {
		jogadores = daoControllerJogadores.listarJogadoresTime(time);
		listJogadores = new ArrayList<Jogador>(jogadores);
		maisGols = new Comparator<Jogador>() {
			@Override
			public int compare(Jogador o1, Jogador o2) {
				return o2.getGols() - o1.getGols();
			}
		};

		maisAmarelos = new Comparator<Jogador>() {
			@Override
			public int compare(Jogador o1, Jogador o2) {
				return o2.getQtdCartoesAmarelos() - o1.getQtdCartoesAmarelos();
			}
		};
		
		maisVermelhos = new Comparator<Jogador>() {
			@Override
			public int compare(Jogador o1, Jogador o2) {
				return o2.getQtdCartoesVermelhos() - o1.getQtdCartoesVermelhos();
			}
		};

		buscarJogadores(maisGols, maisAmarelos, maisVermelhos);
	}

	private void buscarJogadores(Comparator<Jogador> comparador1, Comparator<Jogador> comparador2,
			Comparator<Jogador> comparador3) {
		Collections.sort(jogadores, comparador1);
		jogadorMaisGols = jogadores.get(0);

		Collections.sort(jogadores, comparador2);
		jogadorMaisAmarelos = jogadores.get(0);

		Collections.sort(jogadores, comparador3);
		jogadorMaisVermelhos = jogadores.get(0);
	}

	public List<TimeCamp> getTimes() {
		return times;
	}

	public TimeCamp getTime() {
		return time;
	}

	public void setTime(TimeCamp time) {
		this.time = time;
	}

	public Jogador getJogadorMaisGols() {
		return jogadorMaisGols;
	}

	public void setJogadorMaisGols(Jogador jogadorMaisGols) {
		this.jogadorMaisGols = jogadorMaisGols;
	}

	public Jogador getJogadorMaisAmarelos() {
		return jogadorMaisAmarelos;
	}

	public void setJogadorMaisAmarelos(Jogador jogadorMaisAmarelos) {
		this.jogadorMaisAmarelos = jogadorMaisAmarelos;
	}

	public Jogador getJogadorMaisVermelhos() {
		return jogadorMaisVermelhos;
	}

	public void setJogadorMaisVermelhos(Jogador jogadorMaisVermelhos) {
		this.jogadorMaisVermelhos = jogadorMaisVermelhos;
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}
	
	public List<Jogador> getListJogadores() {
		return listJogadores;
	}
}
