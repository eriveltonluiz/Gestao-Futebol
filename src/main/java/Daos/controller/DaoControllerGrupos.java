package Daos.controller;


import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import Daos.Dao;
import Interfaces.InterfaceGrupoTimes;
import Model.Grupo;
import Model.TimeCamp;

@Named
public class DaoControllerGrupos extends Dao<Grupo>{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private InterfaceGrupoTimes interfaceGrupo;
	
	public Grupo returnObjGrupo(String descricao) {
		return interfaceGrupo.returnObjGrupo(descricao);
	}
	
	public void addTimesNoGrupo(TimeCamp time, Grupo grupo) {
		interfaceGrupo.addTimesNoGrupo(time, grupo);
	}

	public List<SelectItem> listarItemGrupos() {
		return interfaceGrupo.listarItemGrupos();
	}
}
