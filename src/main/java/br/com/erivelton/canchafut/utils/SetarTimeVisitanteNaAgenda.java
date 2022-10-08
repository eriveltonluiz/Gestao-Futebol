package br.com.erivelton.canchafut.utils;

import br.com.erivelton.canchafut.model.AgendamentoTimes;

public class SetarTimeVisitanteNaAgenda extends ValidacaoVisitante{

	private String nomeTimeVisitante;
	private boolean validarVisit;
	
	public SetarTimeVisitanteNaAgenda(ValidacaoVisitante proximo, String nomeTimeVisitante, boolean validarVisit) {
		super(proximo);
		this.nomeTimeVisitante = nomeTimeVisitante;
		this.validarVisit = validarVisit;
	}

	public void validar(AgendamentoTimes agendaTime) {
		agendaTime.setarNomeTimeVisitante(nomeTimeVisitante);
	}

	@Override
	public boolean condicao(AgendamentoTimes agendaTime) {
		return validarVisit;
	}

}
