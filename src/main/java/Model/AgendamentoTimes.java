package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class AgendamentoTimes{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) not null default 'em aberto'")
	private String statusHorario;
	
	@ManyToOne
	private TimeMandante mandante;
	
	@ManyToOne
	private Agenda agenda;
	
	@Transient
	private Cliente cliente;
	
	@Transient
	private TimeVisitante visitante;
	
	@Transient
	private String imagem;
	
	@Transient
	private Boolean desabilitado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusHorario() {
		return statusHorario;
	}

	public void setStatusHorario(String statusHorario) {
		this.statusHorario = statusHorario;
	}

	public TimeMandante getMandante() {
		return mandante;
	}

	public void setMandante(TimeMandante mandante) {
		this.mandante = mandante;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TimeVisitante getVisitante() {
		return visitante;
	}

	public void setVisitante(TimeVisitante visitante) {
		this.visitante = visitante;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public Boolean getDesabilitado() {
		return desabilitado;
	}
	
	public void setDesabilitado(Boolean desabilitado) {
		this.desabilitado = desabilitado;
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
		AgendamentoTimes other = (AgendamentoTimes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
