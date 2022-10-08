package br.com.erivelton.canchafut.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.erivelton.canchafut.model.enums.RodadaConfirmada;

@Entity
public class Confrontos implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Mand_Visit_TimePK id = new Mand_Visit_TimePK();
	
	@Temporal(TemporalType.DATE)
	private Date dataJogo;
	
	@Temporal(TemporalType.TIME)
	private Date horario;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('SIM','NAO') default 'NAO'")
	private RodadaConfirmada rodadaConfirmada;

	public String nomeTimeMandante() {
		return id.getMandante().getTime().getNome();
	}
	
	public int golsMandante() {
		return id.golsMandante();
	}
	
	public Mand_Visit_TimePK getId() {
		return id;
	}

	public void setId(Mand_Visit_TimePK id) {
		this.id = id;
	}

	public Date getDataJogo() {
		return dataJogo;
	}

	public void setDataJogo(Date dataJogo) {
		this.dataJogo = dataJogo;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}
	
	public Rodada getRodada() {
		return id.getRodada();
	}
	
	public void setRodada(Rodada rodada) {
		id.setRodada(rodada);
	}
	
	public CampMandante getMandante() {
		return id.getMandante();
	}
	
	public void setCampMandante(CampMandante mandante) {
		id.setMandante(mandante);
	}
	
	public CampVisitante getVisitante() {
		return id.getVisitante();
	}
	
	public void setVisitante(CampVisitante visitante) {
		id.setVisitante(visitante);
	}

	public RodadaConfirmada getRodadaConfirmada() {
		return rodadaConfirmada;
	}

	public void setRodadaConfirmada(RodadaConfirmada rodadaConfirmada) {
		this.rodadaConfirmada = rodadaConfirmada;
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
		Confrontos other = (Confrontos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
