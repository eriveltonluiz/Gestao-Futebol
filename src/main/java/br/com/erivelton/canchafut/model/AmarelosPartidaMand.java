package br.com.erivelton.canchafut.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AmarelosPartidaMand implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "mandante_id")
	private CampMandante mandante;
	
	@ManyToOne
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AmarelosPartidaMand other = (AmarelosPartidaMand) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void incluirAmareloAoJogadorTimeMandante(Jogador jogador, Confrontos confronto) {
		this.jogador = jogador;
		this.mandante = confronto.getMandante();
	}

}
