package br.com.erivelton.canchafut.utils;

import br.com.erivelton.canchafut.model.AgendamentoTimes;

public class FimValidacaoVisit extends ValidacaoVisitante{

	public FimValidacaoVisit() {
		super(null);
	}

	@Override
	public void validar(AgendamentoTimes agendaTime) {
	}

	@Override
	public boolean condicao(AgendamentoTimes agendaTime) {
		return true;
	}

}
