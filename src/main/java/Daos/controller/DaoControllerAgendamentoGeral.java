package Daos.controller;

import java.io.Serializable;

import javax.inject.Named;

import Daos.Dao;
import Model.AgendamentoTimes;

@Named
public class DaoControllerAgendamentoGeral extends Dao<AgendamentoTimes> implements Serializable{

	private static final long serialVersionUID = 1L;

}
