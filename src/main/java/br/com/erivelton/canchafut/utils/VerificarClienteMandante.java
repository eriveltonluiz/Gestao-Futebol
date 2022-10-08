package br.com.erivelton.canchafut.utils;

import java.util.Date;

import br.com.erivelton.canchafut.dao.controller.DaoControllerVisitanteAgenda;
import br.com.erivelton.canchafut.geral.Mensagens;
import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.TimeVisitante;

public class VerificarClienteMandante extends ValidacaoVisitante{

	private DaoControllerVisitanteAgenda daoControllerVisitanteAgenda;
	private AgendamentoTimes agendamentoTimeNaLista;
	private TimeVisitante visitante;
	private boolean visitNaoInserido;
	private boolean verificarMandanteHorario;
	private Date data;
	

	public VerificarClienteMandante(ValidacaoVisitante proximo, DaoControllerVisitanteAgenda daoControllerVisitanteAgenda,
			AgendamentoTimes agendamentoTimeNaLista, AgendamentoTimes agendamentoTimes, TimeVisitante visitante,
			boolean visitNaoInserido, boolean verificarMandanteHorario, Date data) {
		super(proximo);
		this.daoControllerVisitanteAgenda = daoControllerVisitanteAgenda;
		this.agendamentoTimeNaLista = agendamentoTimeNaLista;
		this.visitante = visitante;
		this.visitNaoInserido = visitNaoInserido;
		this.verificarMandanteHorario = verificarMandanteHorario;
		this.data = data;
	}

	public void validar(AgendamentoTimes agendaTime) {
		if (verificarMandanteHorario) {
			agendaTime.setarDataVisitante(data);
			if (agendaTime.clienteVisitante() == null) {
				agendaTime.setarNomeTimeVisitante(null);
			} else {
				agendaTime.setarTimeVisitanteComODoCLiente();
			}
			visitante = daoControllerVisitanteAgenda.atualizar(agendaTime.getVisitante());

			visitNaoInserido = false;
			Mensagens.msgInfo("salvo com sucesso!!");
		} else {
			agendamentoTimeNaLista.setarClienteMandante(null);
			Mensagens.msgError("Necessário o cadastro do time mandante!!");
		}
	}
	
	@Override
	public boolean condicao(AgendamentoTimes agendaTime) {
		if(agendaTime.clienteMandante() == null) {
			agendamentoTimeNaLista.setarClienteMandante(null);
			Mensagens.msgError("Necessário o cadastro do time mandante!!");
			return false;
		}
		return true;
	}
	
	public boolean retornarFlagVisitNaoInserido() {
		return visitNaoInserido;
	}
	
	public TimeVisitante retornarVisitanteSalvo() {
		return visitante;
	}

}
