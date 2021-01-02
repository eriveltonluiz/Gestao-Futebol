package Interfaces;

import java.io.Serializable;
import java.util.List;

import Model.AmarelosPartidaMand;
import Model.CampMandante;

public interface InterfaceAmarelosMand extends Serializable{
	List<AmarelosPartidaMand> amarelosPartida(CampMandante mand);
}
