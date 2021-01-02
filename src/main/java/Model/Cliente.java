package Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	private String nomeUser;
	
	private String nomeTime;
	
	private String planoHorario;
	
	@Column(columnDefinition = "LONGTEXT")
	private String fotoIconBase64;
	
	private String extensao;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] fotoIconBase64Original; 

	@OneToMany(mappedBy = "cliente")
	private Set<TimeMandante> mandantes = new HashSet<TimeMandante>();
	
	@OneToMany(mappedBy = "cliente")
	private Set<TimeVisitante> visitantes = new HashSet<TimeVisitante>();
	
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

	public String getPlanoHorario() {
		return planoHorario;
	}

	public void setPlanoHorario(String planoHorario) {
		this.planoHorario = planoHorario;
	}

	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getFotoIconBase64Original() {
		return fotoIconBase64Original;
	}

	public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
		this.fotoIconBase64Original = fotoIconBase64Original;
	}

	public Set<TimeMandante> getMandantes() {
		return mandantes;
	}

	public void setMandantes(Set<TimeMandante> mandantes) {
		this.mandantes = mandantes;
	}

	public Set<TimeVisitante> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(Set<TimeVisitante> visitantes) {
		this.visitantes = visitantes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
