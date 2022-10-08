package br.com.erivelton.canchafut.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.erivelton.canchafut.dao.controller.DaoControllerMandanteAgenda;
import br.com.erivelton.canchafut.dao.controller.DaoControllerVisitanteAgenda;

@Entity
public class AgendamentoTimes{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) not null default 'em aberto'")
	private String statusHorario;

	@Column(columnDefinition = "ENUM('em_aberto', 'confirmado', 'parcialmente_confirmado', 'cancelado') default 'em_aberto'")
	@Enumerated(EnumType.STRING)
	private TipoStatusHorario tipoStatus;
	
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
	
	public TipoStatusHorario getTipoStatus() {
		return tipoStatus;
	}
	
	public void setTipoStatus(TipoStatusHorario tipoStatus) {
		this.tipoStatus = tipoStatus;
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

	public void atualizarStatusHorarioConfirmado(Double valorPendenteAtual) {
		if (valorPendenteAtual == 0 && visitante.getValorPendente() == 0) {
			this.atualizarStatusHorario("confirmado", "confirm.png", true);
		} else {
			this.atualizarStatusHorario("parcialmente confirmado", "parcial.png", true);
		}
	}

	public void atualizarStatusHorario(String statusHorario, String imagem, boolean desabilitado) {
		this.statusHorario = statusHorario;
		this.imagem = imagem;
		this.desabilitado = desabilitado;
	}

	public Long idVisitante() {
		return visitante.getIdVisitante();
	}

	public void atualizarVisitanteESeusValores(DaoControllerVisitanteAgenda daoControllerVisitanteAgenda,
			Double valorPago, Double valorPendente) {
		visitante = daoControllerVisitanteAgenda.returnVisitante(visitante.getData(), visitante.getNomeTimeVisit());
		visitante.atualizarValoresGerais(valorPago, valorPendente);
	}

	public void atualizarValorPendenteVisit(Double valorPorHorario) {
		visitante.atualizarValorPendente(valorPorHorario);
	}

	public void removerVisitante(DaoControllerVisitanteAgenda daoControllerVisitanteAgenda, Date data) {
		visitante.removerComData(data);
		daoControllerVisitanteAgenda.removerPorId(visitante.getIdVisitante(), TimeVisitante.class);
	}

	public void removerMandante(DaoControllerMandanteAgenda daoControllerMandanteAgenda) {
		cliente = null;
		mandante.remover(daoControllerMandanteAgenda);
		visitante.remover();
	}

	public void AdicionarMandante(DaoControllerMandanteAgenda daoControllerMandanteAgenda) {
		mandante.adicionar(daoControllerMandanteAgenda);
	}

	public String nomeTimeVisitante() {
		return visitante.getNomeTimeVisit();
	}

	public void setarClienteMandante(Cliente cliente) {
		mandante.setCliente(cliente);
	}

	public Cliente clienteMandante() {
		return mandante.getCliente();
	}

	public void setarNomeTimeMandante(String nomeTime) {
		mandante.setarNomeTime(nomeTime);
	}

	public Cliente clienteVisitante() {
		return visitante.getCliente();
	}
	
	public void setarNomeTimeVisitante(String nomeTime) {
		visitante.setNomeTimeVisit(nomeTime);
	}

	public Long idClienteMandante() {
		return mandante.idCliente();
	}

	public void setarDataVisitante(Date data) {
		visitante.setData(data);
	}

	public void setarTimeVisitanteComODoCLiente() {
		visitante.setarTimeComODoCLiente();
	}

	public String nomeTimeClienteMandante() {
		return mandante.nomeTimeCliente();
	}
	
	public void setarImagensPorTipoStatusHorario() {
		tipoStatus.setarImagem(this);
	}

}
