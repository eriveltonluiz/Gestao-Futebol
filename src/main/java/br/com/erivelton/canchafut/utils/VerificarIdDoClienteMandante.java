package br.com.erivelton.canchafut.utils;

import br.com.erivelton.canchafut.model.AgendamentoTimes;

public class VerificarIdDoClienteMandante extends ValidacaoVisitante{

	public VerificarIdDoClienteMandante(ValidacaoVisitante proximo) {
		super(proximo);
	}

	public void validar(AgendamentoTimes agendaTime) {
		agendaTime.setarClienteMandante(null);
	}

	@Override
	public boolean condicao(AgendamentoTimes agendaTime) {
		return agendaTime.idClienteMandante() == 0L;
	}

}
