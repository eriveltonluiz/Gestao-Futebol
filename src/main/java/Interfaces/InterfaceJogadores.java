package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.Jogador;
import Model.TimeCamp;

public interface InterfaceJogadores extends Serializable{
	void zerarDadosJogo(TimeCamp mand, TimeCamp visit);
	void atualizarQtdGols(Jogador jog, int qtd, String escolha);
	void atualizarQtdCartoesAmarelos(Jogador jog, int qtd);
	void atualizarQtdCartoesVermelhos(Jogador jog, int qtd);
	Long verificarNumeroCamisa(Jogador jogador);
	List<Jogador> listarJogadoresTime(TimeCamp time);
	List<Jogador> listarJogadoresAmarelados(TimeCamp time);
	List<Jogador> listarJogadoresVermelhos(TimeCamp time);
	List<Jogador> jogadoresComGols(TimeCamp timeCamp);
	List<Jogador> listarArtilharia();
	List<Jogador> listarAmarelados();
	List<Jogador> listarVermelhos();
}
