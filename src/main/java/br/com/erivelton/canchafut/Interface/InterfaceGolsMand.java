package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.CampMandante;
import br.com.erivelton.canchafut.model.GolsPartidaMand;

public interface InterfaceGolsMand extends Serializable{
	List<GolsPartidaMand> autoresPartida(CampMandante mand);
}
