package Model;

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
public class CampVisitante implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVisitante;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer golsFeitos;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer golsTomados;
	
	@Transient
	private Integer qtdconf;
	
	@ManyToOne
	private TimeCamp time;
	
	@OneToMany(mappedBy = "id.visitante")
	private List<Confrontos> confrontos = new ArrayList<>();

	@OneToMany(mappedBy = "visitante")
	private List<ConfrontosMataMata> confrontosMata = new ArrayList<>();
	
	public Long getIdVisitante() {
		return idVisitante;
	}

	public void setIdVisitante(Long idVisitante) {
		this.idVisitante = idVisitante;
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
	
	public Integer getQtdconf() {
		return qtdconf;
	}
	
	public void setQtdconf(Integer qtdconf) {
		this.qtdconf = qtdconf;
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
		CampVisitante other = (CampVisitante) obj;
		if (idVisitante == null) {
			if (other.idVisitante != null)
				return false;
		} else if (!idVisitante.equals(other.idVisitante))
			return false;
		return true;
	}
}
