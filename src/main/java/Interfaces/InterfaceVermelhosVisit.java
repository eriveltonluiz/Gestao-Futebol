package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.CampVisitante;
import Model.VermelhosPartidaVisit;

public interface InterfaceVermelhosVisit extends Serializable{
	List<VermelhosPartidaVisit> vermelhosPartida(CampVisitante visit);
}
