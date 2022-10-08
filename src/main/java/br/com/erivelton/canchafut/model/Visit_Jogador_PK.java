package br.com.erivelton.canchafut.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Embeddable
public class Visit_Jogador_PK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "partida_id")
	private Long idPartida;
	
	@ManyToOne
	@JoinColumn(name = "visitante_id")
	private CampVisitante visitante;
	
	@ManyToOne
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;

	public CampVisitante getVisitante() {
		return visitante;
	}

	public void setVisitante(CampVisitante visitante) {
		this.visitante = visitante;
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
		result = prime * result + ((visitante == null) ? 0 : visitante.hashCode());
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
		Visit_Jogador_PK other = (Visit_Jogador_PK) obj;
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
		if (visitante == null) {
			if (other.visitante != null)
				return false;
		} else if (!visitante.equals(other.visitante))
			return false;
		return true;
	}

	
}
