package br.com.erivelton.canchafut.dao.controller;


import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.erivelton.canchafut.Interface.InterfaceGrupoTimes;
import br.com.erivelton.canchafut.dao.Dao;
import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.TimeCamp;

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
