package br.com.erivelton.canchafut.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.erivelton.canchafut.dao.controller.DaoControllerMandanteAgenda;

@Entity
public class TimeMandante implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMandante;

	@Temporal(TemporalType.TIME)
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

	public void remover(DaoControllerMandanteAgenda daoControllerMandanteAgenda) {
		cliente = null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(horario);
		cal.add(Calendar.HOUR_OF_DAY, 3);

		horario = cal.getTime();
		daoControllerMandanteAgenda.atualizar(this);

		cal.add(Calendar.HOUR_OF_DAY, -3);
		horario = cal.getTime();
	}

	public void adicionar(DaoControllerMandanteAgenda daoControllerMandanteAgenda) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(horario);
		cal.add(Calendar.HOUR_OF_DAY, 3);

		horario = cal.getTime();
		daoControllerMandanteAgenda.atualizar(this);

		cal.add(Calendar.HOUR_OF_DAY, -3);
		horario = cal.getTime();
	}

	public void setarNomeTime(String nomeTime) {
		cliente.setNomeTime(nomeTime);
	}

	public Long idCliente() {
		return cliente.getIdCliente();
	}

	public String nomeTimeCliente() {
		return cliente.getNomeTime();
	}

}
