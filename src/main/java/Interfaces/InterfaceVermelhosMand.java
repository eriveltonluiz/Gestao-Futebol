package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.CampMandante;
import Model.VermelhosPartidaMand;

public interface InterfaceVermelhosMand extends Serializable{
	List<VermelhosPartidaMand> vermelhosPartida(CampMandante mand);
}
