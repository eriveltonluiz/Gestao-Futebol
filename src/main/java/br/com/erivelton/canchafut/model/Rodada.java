package br.com.erivelton.canchafut.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.erivelton.canchafut.model.enums.RodadaConfirmada;

@Entity
public class Rodada implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer identificacao;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 3)
	private RodadaConfirmada confirmacao;
	
	@OneToMany(mappedBy = "id.rodada")
	private List<Confrontos> confrontos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Confrontos> getConfrontos() {
		return confrontos;
	}

	public void setConfrontos(List<Confrontos> confrontos) {
		this.confrontos = confrontos;
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
		Rodada other = (Rodada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public RodadaConfirmada getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(RodadaConfirmada confirmacao) {
		this.confirmacao = confirmacao;
	}

	public Integer getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Integer identificacao) {
		this.identificacao = identificacao;
	}
	
	
}
