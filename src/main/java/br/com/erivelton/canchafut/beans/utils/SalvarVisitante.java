package br.com.erivelton.canchafut.beans.utils;

import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.erivelton.canchafut.dao.controller.DaoControllerVisitanteAgenda;
import br.com.erivelton.canchafut.geral.Mensagens;
import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.TimeVisitante;
import br.com.erivelton.canchafut.utils.FimValidacaoVisit;
import br.com.erivelton.canchafut.utils.SetarTimeVisitanteNaAgenda;
import br.com.erivelton.canchafut.utils.ValidacaoVisitante;
import br.com.erivelton.canchafut.utils.VerificarClienteMandante;
import br.com.erivelton.canchafut.utils.VerificarIdDoClienteMandante;

public class SalvarVisitante extends TemplateSalvarVisitanteEMandante{

	private List<SelectItem> visitanteItens;
	private AgendamentoTimes agendaTimeNaLista;
	private boolean validarVisit;
	private boolean visitNaoInserido;
	private boolean verificarMandanteHorario;
	private TimeVisitante visitante;
	private DaoControllerVisitanteAgenda daoControllerVisitanteAgenda; 
	private Date data;
	
	public SalvarVisitante(List<SelectItem> visitanteItens, AgendamentoTimes agendaTimeNaLista, boolean validarVisit, boolean visitNaoInserido,
			boolean verificarMandanteHorario, TimeVisitante visitante,
			DaoControllerVisitanteAgenda daoControllerVisitanteAgenda, Date data) {
		this.visitanteItens = visitanteItens;
		this.agendaTimeNaLista = agendaTimeNaLista;
		this.validarVisit = validarVisit;
		this.visitNaoInserido = visitNaoInserido;
		this.verificarMandanteHorario = verificarMandanteHorario;
		this.visitante = visitante;
		this.daoControllerVisitanteAgenda = daoControllerVisitanteAgenda;
		this.data = data;
	}

	@Override
	public boolean selecaoDeHorarios() {
		return visitanteItens == null || visitanteItens.isEmpty();
	}

	@Override
	public void mensagemHorariosNaoEscolhidos() {
		Mensagens.msgError("ERRO!! Necessário selecionar os horários disponíveis para agenda!!");
	}

	@Override
	protected void salvar(AgendamentoTimes agendaTime) {
		VerificarClienteMandante validacaoClienteMandante = 
				new VerificarClienteMandante(
					new FimValidacaoVisit(), 
					daoControllerVisitanteAgenda, agendaTime, agendaTimeNaLista, visitante, visitNaoInserido, 
					verificarMandanteHorario, data
				);

		//Implementação do padrão chain of responsability, porém deve ser corrigido o FimValidacao pois gerá NullPointerException
		ValidacaoVisitante validacaoVisitante = new SetarTimeVisitanteNaAgenda(
				new VerificarIdDoClienteMandante(validacaoClienteMandante), 
				visitante.getNomeTimeVisit(),
				validarVisit
		);
		
		validacaoVisitante.validarVisitante(agendaTime);
		visitNaoInserido = validacaoClienteMandante.retornarFlagVisitNaoInserido();
		visitante = validacaoClienteMandante.retornarVisitanteSalvo();
	}

	@Override
	public boolean verificarCliente(AgendamentoTimes agendaTime) {
		return agendaTime.clienteVisitante() != null;
	}

	@Override
	public void mensagemClienteNulo() {
		Mensagens.msgError("Não é possível cadastrar visitante nulo!!");
	}
	
	public boolean getVisitNaoInserido() {
		return visitNaoInserido;
	}
}
