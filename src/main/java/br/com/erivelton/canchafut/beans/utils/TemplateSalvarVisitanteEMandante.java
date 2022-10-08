package br.com.erivelton.canchafut.beans.utils;

import br.com.erivelton.canchafut.model.AgendamentoTimes;

public abstract class TemplateSalvarVisitanteEMandante {

	public void esqueleto(AgendamentoTimes agendaTime) {
		if(selecaoDeHorarios()) {
			mensagemHorariosNaoEscolhidos();
		} else {
			if(verificarCliente(agendaTime)) {
				salvar(agendaTime);
			} else {
				mensagemClienteNulo();
			}
		}
	}
	
	protected abstract void salvar(AgendamentoTimes agendaTime);
	public abstract void mensagemHorariosNaoEscolhidos();
	public abstract void mensagemClienteNulo();
	public abstract boolean selecaoDeHorarios();
	public abstract boolean verificarCliente(AgendamentoTimes agendaTime);
}
