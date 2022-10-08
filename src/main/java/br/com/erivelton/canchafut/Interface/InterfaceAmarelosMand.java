package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import br.com.erivelton.canchafut.model.AmarelosPartidaMand;
import br.com.erivelton.canchafut.model.CampMandante;

public interface InterfaceAmarelosMand extends Serializable{
	List<AmarelosPartidaMand> amarelosPartida(CampMandante mand);
}
