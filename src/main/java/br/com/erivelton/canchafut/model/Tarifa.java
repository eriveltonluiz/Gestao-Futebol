package br.com.erivelton.canchafut.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tarifa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double valorHoursDiurno;
	private Double valorHoursNoturno;
	private Double valorMensalDiurno;
	private Double valorMensalNoturno;
	
	@Temporal(TemporalType.TIME)
	private Date horarioInicDiurno;

	@Temporal(TemporalType.TIME)
	private Date horarioTerminoDiurno;
	
	@Temporal(TemporalType.TIME)
	private Date horarioInicNoturno;
	
	@Temporal(TemporalType.TIME)
	private Date horarioTerminoNoturno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValorHoursDiurno() {
		return valorHoursDiurno;
	}

	public void setValorHoursDiurno(Double valorHoursDiurno) {
		this.valorHoursDiurno = valorHoursDiurno;
	}

	public Double getValorHoursNoturno() {
		return valorHoursNoturno;
	}

	public void setValorHoursNoturno(Double valorHoursNoturno) {
		this.valorHoursNoturno = valorHoursNoturno;
	}

	public Double getValorMensalDiurno() {
		return valorMensalDiurno;
	}

	public void setValorMensalDiurno(Double valorMensalDiurno) {
		this.valorMensalDiurno = valorMensalDiurno;
	}

	public Double getValorMensalNoturno() {
		return valorMensalNoturno;
	}

	public void setValorMensalNoturno(Double valorMensalNoturno) {
		this.valorMensalNoturno = valorMensalNoturno;
	}

	public Date getHorarioInicDiurno() {
		return horarioInicDiurno;
	}

	public void setHorarioInicDiurno(Date horarioInicDiurno) {
		this.horarioInicDiurno = horarioInicDiurno;
	}

	public Date getHorarioTerminoDiurno() {
		return horarioTerminoDiurno;
	}

	public void setHorarioTerminoDiurno(Date horarioTerminoDiurno) {
		this.horarioTerminoDiurno = horarioTerminoDiurno;
	}

	public Date getHorarioInicNoturno() {
		return horarioInicNoturno;
	}

	public void setHorarioInicNoturno(Date horarioInicNoturno) {
		this.horarioInicNoturno = horarioInicNoturno;
	}

	public Date getHorarioTerminoNoturno() {
		return horarioTerminoNoturno;
	}

	public void setHorarioTerminoNoturno(Date horarioTerminoNoturno) {
		this.horarioTerminoNoturno = horarioTerminoNoturno;
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
		Tarifa other = (Tarifa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
