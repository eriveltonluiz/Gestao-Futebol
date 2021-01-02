package Beans.RowCounter;

import java.io.Serializable;

public class Counter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private transient int linha = 0;
	
	public int getLinha() {
		return ++linha;
	}
	
}
