package br.com.erivelton.canchafut.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Mand_Visit_TimePK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "mandante_id")
	private CampMandante mandante;
	
	@ManyToOne
	@JoinColumn(name = "visitante_id")
	private CampVisitante visitante;
	
	@ManyToOne
	@JoinColumn(name = "rodada_id")
	private Rodada rodada;

	public CampMandante getMandante() {
		return mandante;
	}

	public void setMandante(CampMandante mandante) {
		this.mandante = mandante;
	}

	public CampVisitante getVisitante() {
		return visitante;
	}

	public void setVisitante(CampVisitante visitante) {
		this.visitante = visitante;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mandante == null) ? 0 : mandante.hashCode());
		result = prime * result + ((rodada == null) ? 0 : rodada.hashCode());
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
		Mand_Visit_TimePK other = (Mand_Visit_TimePK) obj;
		if (mandante == null) {
			if (other.mandante != null)
				return false;
		} else if (!mandante.equals(other.mandante))
			return false;
		if (rodada == null) {
			if (other.rodada != null)
				return false;
		} else if (!rodada.equals(other.rodada))
			return false;
		if (visitante == null) {
			if (other.visitante != null)
				return false;
		} else if (!visitante.equals(other.visitante))
			return false;
		return true;
	}

	public int golsMandante() {
		return this.getMandante().getGolsFeitos();
	}

	
}
