package Interfaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

import Model.Grupo;
import Model.TimeCamp;

public interface InterfaceGrupoTimes extends Serializable{
	void addTimesNoGrupo(TimeCamp time, Grupo grupo);
	Grupo returnObjGrupo(String descricao);
	List<SelectItem> listarItemGrupos();
}
