package br.com.erivelton.canchafut.Interface;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.TimeCamp;

public interface InterfaceGrupoTimes extends Serializable{
	void addTimesNoGrupo(TimeCamp time, Grupo grupo);
	Grupo returnObjGrupo(String descricao);
	List<SelectItem> listarItemGrupos();
}
