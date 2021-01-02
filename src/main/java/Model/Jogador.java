package Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Jogador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Integer rg;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer qtdCartoesAmarelos;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer qtdCartoesVermelhos;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer qtdAmarelosJogo;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer qtdVermelhosJogo;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer golsPorJogo;
	
	private Integer numCamisa;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer gols;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@ManyToOne
	private TimeCamp time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public Integer getQtdCartoesAmarelos() {
		return qtdCartoesAmarelos;
	}

	public void setQtdCartoesAmarelos(Integer qtdCartoesAmarelos) {
		this.qtdCartoesAmarelos = qtdCartoesAmarelos;
	}

	public Integer getQtdCartoesVermelhos() {
		return qtdCartoesVermelhos;
	}

	public void setQtdCartoesVermelhos(Integer qtdCartoesVermelhos) {
		this.qtdCartoesVermelhos = qtdCartoesVermelhos;
	}

	public Integer getQtdAmarelosJogo() {
		return qtdAmarelosJogo;
	}

	public void setQtdAmarelosJogo(Integer qtdAmarelosJogo) {
		this.qtdAmarelosJogo = qtdAmarelosJogo;
	}

	public Integer getQtdVermelhosJogo() {
		return qtdVermelhosJogo;
	}

	public void setQtdVermelhosJogo(Integer qtdVermelhosJogo) {
		this.qtdVermelhosJogo = qtdVermelhosJogo;
	}
	
	public Integer getGolsPorJogo() {
		return golsPorJogo;
	}

	public void setGolsPorJogo(Integer golsPorJogo) {
		this.golsPorJogo = golsPorJogo;
	}

	public Integer getNumCamisa() {
		return numCamisa;
	}

	public void setNumCamisa(Integer numCamisa) {
		this.numCamisa = numCamisa;
	}

	public TimeCamp getTime() {
		return time;
	}

	public void setTime(TimeCamp time) {
		this.time = time;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Integer getGols() {
		return gols;
	}

	public void setGols(Integer gols) {
		this.gols = gols;
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
		Jogador other = (Jogador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
