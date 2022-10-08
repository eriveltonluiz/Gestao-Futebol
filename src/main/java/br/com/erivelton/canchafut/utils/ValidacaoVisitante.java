package br.com.erivelton.canchafut.utils;

import br.com.erivelton.canchafut.model.AgendamentoTimes;

public abstract class ValidacaoVisitante {

	protected ValidacaoVisitante proximo;
	
	public ValidacaoVisitante(ValidacaoVisitante proximo) {
		this.proximo = proximo;
	}
	
	public final void validarVisitante(AgendamentoTimes agendaTime) {
		if(condicao(agendaTime)) {
			validar(agendaTime);
		}
		if(proximo != null)
			proximo.validarVisitante(agendaTime);
	}
	
	protected abstract void validar(AgendamentoTimes agendaTime);
	public abstract boolean condicao(AgendamentoTimes agendaTime);
}