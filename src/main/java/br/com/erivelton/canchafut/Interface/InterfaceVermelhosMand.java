package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.CampMandante;
import br.com.erivelton.canchafut.model.VermelhosPartidaMand;

public interface InterfaceVermelhosMand extends Serializable{
	List<VermelhosPartidaMand> vermelhosPartida(CampMandante mand);
}
