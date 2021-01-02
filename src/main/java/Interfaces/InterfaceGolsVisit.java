package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.CampVisitante;
import Model.GolsPartidaVisit;

public interface InterfaceGolsVisit extends Serializable{
	List<GolsPartidaVisit> autoresPartida(CampVisitante visit);
}
