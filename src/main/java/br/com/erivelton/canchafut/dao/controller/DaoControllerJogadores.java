package br.com.erivelton.canchafut.dao.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.erivelton.canchafut.Interface.InterfaceJogadores;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.Jogador;
import br.com.erivelton.canchafut.model.TimeCamp;

@Named
public class DaoControllerJogadores extends Dao<Jogador> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceJogadores interfaceJogadores;
	
	public void zerarDadosJogo(TimeCamp mand, TimeCamp visit) {
		interfaceJogadores.zerarDadosJogo(mand, visit);
	}
	
	public List<Jogador> listarArtilharia(){
		return interfaceJogadores.listarArtilharia();
	}
	
	public List<Jogador> listarAmarelados(){
		return interfaceJogadores.listarAmarelados();
	}
	
	public List<Jogador> listarVermelhos(){
		return interfaceJogadores.listarVermelhos();
	}
	
	public Long verificarNumeroJogador(Jogador jogador) {
		return interfaceJogadores.verificarNumeroCamisa(jogador);
	}
	
	public void atualizarQtdGols(Jogador jog, int qtd, String escolha) {
		interfaceJogadores.atualizarQtdGols(jog, qtd, escolha);
	}
	
	public void atualizarQtdCartoesAmarelos(Jogador jog, int qtd) {
		interfaceJogadores.atualizarQtdCartoesAmarelos(jog, qtd);
	}
	
	public void atualizarQtdCartoesVermelhos(Jogador jog, int qtd) {
		interfaceJogadores.atualizarQtdCartoesVermelhos(jog, qtd);
	}
	
	public List<Jogador> listarJogadoresTime(TimeCamp time){
		return interfaceJogadores.listarJogadoresTime(time);
	}
	
	public List<Jogador> listarJogadoresAmarelados(TimeCamp time){
		return interfaceJogadores.listarJogadoresAmarelados(time);
	}
	
	public List<Jogador> listarJogadoresVermelhos(TimeCamp time){
		return interfaceJogadores.listarJogadoresVermelhos(time);
	}

	public List<Jogador> jogadoresComGols(TimeCamp timeCamp) {
		return interfaceJogadores.jogadoresComGols(timeCamp);
	}

}
