package br.com.erivelton.canchafut.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.erivelton.canchafut.model.enums.RodadaConfirmada;

@Entity
public class ConfrontosMataMata implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dataJogo;
	
	@Temporal(TemporalType.TIME)
	private Date horario;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('SIM','NAO') default 'NAO'")
	private RodadaConfirmada confrontoConfirmado;
	
	private Integer identificador;
	
	@ManyToOne
	@JoinColumn(name = "mandante_id")
	private CampMandante mandante;
	
	@ManyToOne
	@JoinColumn(name = "visitante_id")
	private CampVisitante visitante;
	
	@ManyToOne
	@JoinColumn(name = "etapa_id")
	private EtapaMataMata etapa;

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

	public EtapaMataMata getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaMataMata etapa) {
		this.etapa = etapa;
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
	
	public RodadaConfirmada getConfrontoConfirmado() {
		return confrontoConfirmado;
	}

	public void setConfrontoConfirmado(RodadaConfirmada confrontoConfirmado) {
		this.confrontoConfirmado = confrontoConfirmado;
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
		ConfrontosMataMata other = (ConfrontosMataMata) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	
	
}
