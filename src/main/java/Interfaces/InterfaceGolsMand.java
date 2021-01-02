package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.CampMandante;
import Model.GolsPartidaMand;

public interface InterfaceGolsMand extends Serializable{
	List<GolsPartidaMand> autoresPartida(CampMandante mand);
}
