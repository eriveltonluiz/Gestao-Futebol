package br.com.erivelton.canchafut.dto;

import br.com.erivelton.canchafut.model.Cliente;

public class NovoCliente {

	private Long id;
	
private String nomeUser;
	
	private String nomeTime;
	
	private String planoHorario;

	private String fotoIconBase64;
	
	public NovoCliente() {
	}
	
	public NovoCliente(Cliente cliente) {
		this.id = cliente.getIdCliente();
		this.nomeUser = cliente.getNomeUser();
		this.nomeTime = cliente.getNomeTime();
		this.planoHorario = cliente.getPlanoHorario();
		this.fotoIconBase64 = cliente.getFotoIconBase64();
	}

	public NovoCliente(String nomeUser, String nomeTime, String planoHorario) {
		this.nomeUser = nomeUser;
		this.nomeTime = nomeTime;
		this.planoHorario = planoHorario;
	}
	
	public String getNomeUser() {
		return nomeUser;
	}
	
	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	
	public String getNomeTime() {
		return nomeTime;
	}
	
	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	
	public String getPlanoHorario() {
		return planoHorario;
	}

	public void setPlanoHorario(String planoHorario) {
		this.planoHorario = planoHorario;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFotoIconBase64() {
		return fotoIconBase64;
	}
	
	public Cliente paraEntidadeComImagem(byte[] imagemByte, String fotoIcon, String extensao) {
		return new Cliente(extensao, extensao, extensao, fotoIcon, extensao, imagemByte);
	}
}
