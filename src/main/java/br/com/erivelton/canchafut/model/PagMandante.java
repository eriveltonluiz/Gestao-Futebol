package br.com.erivelton.canchafut.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.erivelton.canchafut.geral.Mensagens;

@Entity
public class PagMandante implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPagMandante;
	
	private Double valorPago;
	
	private Double valorPendente;
	
	@Transient
	private String status;
	
	@Transient
	private String situacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataPag;
	
	@ManyToOne
	private TimeMandante mandante;
	
	@Transient
	private TimeVisitante visitante;

	public void ajustarValorPago() {
		valorPago += valorPendente;
		valorPendente = 0.0;
	}
	
	public Long getIdPagMandante() {
		return idPagMandante;
	}

	public void setIdPagMandante(Long idPagMandante) {
		this.idPagMandante = idPagMandante;
	}

	public Double getValorPago() {
		return valorPago;
	}
	
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Double getValorPendente() {
		return valorPendente;
	}

	public void setValorPendente(Double valorPendente) {
		this.valorPendente = valorPendente;
	}

	public TimeMandante getMandante() {
		return mandante;
	}

	public void setMandante(TimeMandante mandante) {
		this.mandante = mandante;
	}

	public Date getDataPag() {
		return dataPag;
	}

	public void setDataPag(Date dataPag) {
		this.dataPag = dataPag;
	}

	public TimeVisitante getVisitante() {
		return visitante;
	}
	
	public void setVisitante(TimeVisitante visitante) {
		this.visitante = visitante;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSituacao() {
		return situacao;
	}
	
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPagMandante == null) ? 0 : idPagMandante.hashCode());
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
		PagMandante other = (PagMandante) obj;
		if (idPagMandante == null) {
			if (other.idPagMandante != null)
				return false;
		} else if (!idPagMandante.equals(other.idPagMandante))
			return false;
		return true;
	}

	public void atualizarValorPendenteMand(Double valorPorHorario, Date data, TimeMandante mandante) {
		if (valorPago <= valorPorHorario) {
			valorPendente = valorPorHorario - valorPago;
			dataPag = data;
			this.mandante = mandante;
		} else {
			valorPago = null;
			valorPendente = null;
			Mensagens.msgError("ERRO!! valor pago não pode ultrapassar o valor do horário!!");
		}
	}
	
	
}
