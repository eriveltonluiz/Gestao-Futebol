package Model.enums;

public enum FaseMataMata {
	OITAVAS("Oitavas de Final"),
	QUARTAS("Quartas de Final"),
	SEMI("Semifinal"),
	FINAL("Final");
	
	private String palavra;
	
	private FaseMataMata(String palavra) {
		this.palavra = palavra;
	}

	public String getPalavra() {
		return palavra;
	}

	public static FaseMataMata valor(String palavra) {
		
		for (FaseMataMata rc : FaseMataMata.values()) {
			if(rc.getPalavra() == palavra) {
				return rc;
			}
		}
		
		throw new IllegalArgumentException("Palavra inválida");

	}
	
	
}
