package Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAgenda;

	@Temporal(TemporalType.DATE)
	private Date data;

	@OneToMany(mappedBy = "agenda")
	private List<AgendamentoTimes> agendamentos;

	public Long getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<AgendamentoTimes> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoTimes> agendamentos) {
		this.agendamentos = agendamentos;
	}

}
