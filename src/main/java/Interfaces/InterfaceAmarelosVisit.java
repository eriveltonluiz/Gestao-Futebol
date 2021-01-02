package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.AmarelosPartidaVisit;
import Model.CampVisitante;

public interface InterfaceAmarelosVisit extends Serializable{
	List<AmarelosPartidaVisit> amarelosPartida(CampVisitante visit);
}
