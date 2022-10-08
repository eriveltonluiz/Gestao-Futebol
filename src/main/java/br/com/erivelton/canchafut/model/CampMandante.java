package br.com.erivelton.canchafut.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class CampMandante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMandante;

	@Column(columnDefinition = "int(11) default 0")
	private Integer golsFeitos;

	@Column(columnDefinition = "int(11) default 0")
	private Integer golsTomados;

	@Transient
	private Integer qtdConfrontos;
	
	@ManyToOne
	private TimeCamp time;
	
	@OneToMany(mappedBy = "id.mandante")
	private List<Confrontos> confrontos = new ArrayList<>();
	
	@OneToMany(mappedBy = "mandante")
	private List<ConfrontosMataMata> confrontosMata = new ArrayList<>();
	
	public void acrescentarGols(int golsMarcadosNaPartida) {
		golsFeitos += golsMarcadosNaPartida;
	}
	
	public Long getIdMandante() {
		return idMandante;
	}

	public void setIdMandante(Long idMandante) {
		this.idMandante = idMandante;
	}

	public Integer getGolsFeitos() {
		return golsFeitos;
	}

	public void setGolsFeitos(Integer golsFeitos) {
		this.golsFeitos = golsFeitos;
	}

	public Integer getGolsTomados() {
		return golsTomados;
	}

	public void setGolsTomados(Integer golsTomados) {
		this.golsTomados = golsTomados;
	}
	
	public Integer getQtdConfrontos() {
		return qtdConfrontos;
	}

	public void setQtdConfrontos(Integer qtdConfrontos) {
		this.qtdConfrontos = qtdConfrontos;
	}
	
	public TimeCamp getTime() {
		return time;
	}

	public void setTime(TimeCamp time) {
		this.time = time;
	}

	public List<Confrontos> getConfrontos() {
		return confrontos;
	}
	
	public List<ConfrontosMataMata> getConfrontosMata() {
		return confrontosMata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMandante == null) ? 0 : idMandante.hashCode());
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
		CampMandante other = (CampMandante) obj;
		if (idMandante == null) {
			if (other.idMandante != null)
				return false;
		} else if (!idMandante.equals(other.idMandante))
			return false;
		return true;
	}

}
