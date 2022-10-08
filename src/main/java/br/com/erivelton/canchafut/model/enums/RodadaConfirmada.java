package br.com.erivelton.canchafut.model.enums;

public enum RodadaConfirmada {
	SIM("sim"),
	NAO("não");
	
	private String palavra;
	
	private RodadaConfirmada(String palavra) {
		this.palavra = palavra;
	}

	public String getPalavra() {
		return palavra;
	}

	public static RodadaConfirmada valor(String palavra) {
		
		for (RodadaConfirmada rc : RodadaConfirmada.values()) {
			if(rc.getPalavra() == palavra) {
				return rc;
			}
		}
		
		throw new IllegalArgumentException("Palavra inválida");

	}
	
	
}
