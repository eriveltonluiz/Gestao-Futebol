package br.com.erivelton.canchafut.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Embeddable
public class Mand_Jogador_PK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "partida_id")
	private Long idPartida;
	
	@ManyToOne
	@JoinColumn(name = "mandante_id")
	private CampMandante mandante;
	
	@ManyToOne
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;

	public CampMandante getMandante() {
		return mandante;
	}

	public void setMandante(CampMandante mandante) {
		this.mandante = mandante;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Long getIdPartida() {
		return idPartida;
	}
	
	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPartida == null) ? 0 : idPartida.hashCode());
		result = prime * result + ((jogador == null) ? 0 : jogador.hashCode());
		result = prime * result + ((mandante == null) ? 0 : mandante.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mand_Jogador_PK other = (Mand_Jogador_PK) obj;
		if (idPartida == null) {
			if (other.idPartida != null)
				return false;
		} else if (!idPartida.equals(other.idPartida))
			return false;
		if (jogador == null) {
			if (other.jogador != null)
				return false;
		} else if (!jogador.equals(other.jogador))
			return false;
		if (mandante == null) {
			if (other.mandante != null)
				return false;
		} else if (!mandante.equals(other.mandante))
			return false;
		return true;
	}
	
}
