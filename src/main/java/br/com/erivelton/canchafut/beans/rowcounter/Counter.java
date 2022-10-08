package br.com.erivelton.canchafut.beans.rowcounter;

import java.io.Serializable;

public class Counter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private transient int linha = 0;
	
	public int getLinha() {
		return ++linha;
	}
	
}
