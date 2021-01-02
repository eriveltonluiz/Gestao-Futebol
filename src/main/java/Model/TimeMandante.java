package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TimeMandante implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMandante;

	private Date horario;

	private String dia;

	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "mandante")
	private List<TimeVisitante> visitantes = new ArrayList<>();

	@OneToMany(mappedBy = "mandante")
	private List<AgendamentoTimes> agendamentos = new ArrayList<>();
	
	@OneToMany(mappedBy = "mandante")
	private List<PagMandante> pagamentos = new ArrayList<PagMandante>();

	public Long getIdMandante() {
		return idMandante;
	}

	public void setIdMandante(Long idMandante) {
		this.idMandante = idMandante;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<TimeVisitante> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(List<TimeVisitante> visitantes) {
		this.visitantes = visitantes;
	}

	public List<AgendamentoTimes> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoTimes> agendamentos) {
		this.agendamentos = agendamentos;
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
		TimeMandante other = (TimeMandante) obj;
		if (idMandante == null) {
			if (other.idMandante != null)
				return false;
		} else if (!idMandante.equals(other.idMandante))
			return false;
		return true;
	}

}