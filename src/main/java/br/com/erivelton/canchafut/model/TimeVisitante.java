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

import br.com.erivelton.canchafut.geral.Mensagens;

@Entity
public class TimeVisitante implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVisitante;

	private String nomeTimeVisit;

	private Double valorPago;

	private Double valorPendente;

	@Temporal(TemporalType.DATE)
	private Date data;

	@ManyToOne
	private TimeMandante mandante;

	@ManyToOne
	private Cliente cliente;

	public void ajustarValorPago() {
		valorPago += valorPendente;
		valorPendente = 0.0;
	}
	
	public Long getIdVisitante() {
		return idVisitante;
	}

	public void setIdVisitante(Long idVisitante) {
		this.idVisitante = idVisitante;
	}

	public String getNomeTimeVisit() {
		return nomeTimeVisit;
	}

	public void setNomeTimeVisit(String nomeTimeVisit) {
		this.nomeTimeVisit = nomeTimeVisit;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TimeMandante getMandante() {
		return mandante;
	}

	public void setMandante(TimeMandante mandante) {
		this.mandante = mandante;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVisitante == null) ? 0 : idVisitante.hashCode());
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
		TimeVisitante other = (TimeVisitante) obj;
		if (idVisitante == null) {
			if (other.idVisitante != null)
				return false;
		} else if (!idVisitante.equals(other.idVisitante))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TimeVisitante [idVisitante=" + idVisitante + ", nomeTimeVisit=" + nomeTimeVisit + ", valorPago="
				+ valorPago + ", valorPendente=" + valorPendente + ", data=" + data + ", mandante=" + mandante
				+ ", cliente=" + cliente + "]";
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

	public void atualizarValorPendente(Double valorPorHorario) {
		if (valorPago <= valorPorHorario) {
			valorPendente = valorPorHorario - valorPago;
		} else {
			valorPago = null;
			valorPendente = null;
			Mensagens.msgError("ERRO!! valor pago não pode ultrapassar o valor do horário!!");
		}
	}

	public void atualizarValoresGerais(Double valorPago,
			Double valorPendente) {
		this.valorPago = valorPago;
		this.valorPendente = valorPendente;
	}

	public void removerComData(Date data) {
		this.data = data;
		this.remover();
	}
	
	public void remover() {
		this.cliente = null;
		this.nomeTimeVisit = null;
	}

	public void setarTimeComODoCLiente() {
		nomeTimeVisit = cliente.getNomeTime();
	}

}
