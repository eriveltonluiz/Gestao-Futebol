package br.com.erivelton.canchafut.dao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.erivelton.canchafut.Interface.InterfaceGrupoTimes;
import br.com.erivelton.canchafut.model.Grupo;
import br.com.erivelton.canchafut.model.TimeCamp;

@Named
public class DaoGrupo extends Dao<Grupo> implements InterfaceGrupoTimes {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	@Override
	public void addTimesNoGrupo(TimeCamp time, Grupo grupo) {
		
	}
	

	@Override
	public Grupo returnObjGrupo(String descricao) {
		return (Grupo) em.createQuery("from Grupo where descricao = '" + descricao + "'").getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItem> listarItemGrupos() {
		List<SelectItem> itemsGrupos = new ArrayList<SelectItem>();
		List<Grupo> Grupos = em.createQuery("from Grupo").getResultList();
		
		for (Grupo estado : Grupos) {
			itemsGrupos.add(new SelectItem(estado, estado.getDescricao()));
		}
		return itemsGrupos;
	}

}