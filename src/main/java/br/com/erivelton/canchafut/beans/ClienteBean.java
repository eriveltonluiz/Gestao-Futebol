package br.com.erivelton.canchafut.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import br.com.erivelton.canchafut.beans.arquivos.LoadImage;
import br.com.erivelton.canchafut.dao.DaoJdbc;
import br.com.erivelton.canchafut.dao.controller.DaoControllerCliente;
import br.com.erivelton.canchafut.dao.controller.DaoControllerPagMandante;
import br.com.erivelton.canchafut.dao.controller.DaoControllerTarifas;
import br.com.erivelton.canchafut.dao.controller.DaoControllerVisitanteAgenda;
import br.com.erivelton.canchafut.dto.NovoCliente;
import br.com.erivelton.canchafut.geral.Mensagens;
import br.com.erivelton.canchafut.model.Cliente;
import br.com.erivelton.canchafut.model.PagMandante;
import br.com.erivelton.canchafut.model.Tarifa;
import br.com.erivelton.canchafut.model.TimeVisitante;

@Named(value = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double totalPend;
	private Double totalConfirmados;
	private byte[] imagemByte;
	private UploadedFile upf;
	boolean novoUsuario;
	private Cliente cliente = new Cliente();
	private NovoCliente clienteRequest = new NovoCliente();
	private Tarifa tarifa = new Tarifa();
	private PagMandante pagMandante = new PagMandante();
	private List<NovoCliente> clientes = new ArrayList<>();
	private List<PagMandante> pendencias = new ArrayList<PagMandante>();
	private List<PagMandante> confirmados = new ArrayList<PagMandante>();

	private DaoJdbc dao = new DaoJdbc();

	@Inject
	private DaoControllerCliente daoControllerCliente;

	@Inject
	private DaoControllerTarifas daoControllerTarifas;

	@Inject
	private DaoControllerPagMandante daoControllerPagMandante;

	@Inject
	private DaoControllerVisitanteAgenda daoControllerVisitanteAgenda;

	@PostConstruct
	public void listarClientes() {
		clientes = daoControllerCliente.listar(Cliente.class).stream().map(c -> new NovoCliente(c))
				.collect(Collectors.toList());
		novoUsuario = true;
	}

	public void handleFileUpload(FileUploadEvent file) {
		upf = file.getFile();
		imagemByte = file.getFile().getContent();
	}

	public void salvar() {
		int indiceCliente = clientes.indexOf(clienteRequest);
		Cliente clienteASerSalvo = null;

		if (upf != null) {
			String[] imagens = LoadImage.getImagem(upf, imagemByte);
			clienteASerSalvo = clienteRequest.paraEntidadeComImagem(imagemByte, imagens[0], imagens[1]);
		} else {
			clienteASerSalvo = new Cliente(clienteRequest.getNomeUser(), clienteRequest.getNomeTime(),
					clienteRequest.getPlanoHorario());
		}


		if (novoUsuario) {
			novoUsuario = false;
			clienteASerSalvo = daoControllerCliente.atualizar(clienteASerSalvo);
			clienteRequest.setId(clienteASerSalvo.getIdCliente());
			clientes.add(clienteRequest);
		} else {
			clienteASerSalvo.setIdCliente(clienteRequest.getId());
			clienteASerSalvo = daoControllerCliente.atualizar(clienteASerSalvo);
			clientes.set(indiceCliente, clienteRequest);
		}


		if (clienteRequest != null) {
			upf = null;
			imagemByte = null;
			Mensagens.msgInfo("Salvo com sucesso!!");
		} else {
			Mensagens.msgError("Nome digitado já existe!!");
		}
	}

	public void deletar() {
		daoControllerCliente.removerPorRegistro(Cliente.class, "nomeTime", clienteRequest.getNomeTime());
		clientes.remove(clienteRequest);
		clienteRequest = new NovoCliente();
		Mensagens.msgInfo("Cliente excluído com sucesso!!");
	}

	public void acrescentarDados() {
		// Concluir ainda se usa id ou não na classe de dto de requisição do cliente
		tarifa = daoControllerTarifas.listarResultSimples(Tarifa.class);
		
		pendencias = dao.listaPendencias(clienteRequest.getId());
		confirmados = dao.listaHorariosConfirmados(clienteRequest.getId());
		
		totalPend = pendencias.stream().mapToDouble(i -> i.getValorPendente()).sum();
		totalConfirmados = confirmados.stream().mapToDouble(i -> i.getValorPago()).sum()
				+ pendencias.stream().mapToDouble(i -> i.getValorPago()).sum();

		//esse modo funcionava com lambda abaixo é a nova modificação
//		for (int j = 0; j < pendencias.size(); j++) {
//			pendencias.get(j).setStatus("icon-editar.png");
//		}
		
		pendencias.forEach(pendencia -> pendencia.setStatus("icon-editar.png"));
	}

	public void manipularPendencia() {
		if (pagMandante.getSituacao() != null) {
			TimeVisitante time = daoControllerVisitanteAgenda.returnVisitante(pagMandante.getDataPag(),
					pagMandante.getSituacao());
			time.ajustarValorPago();
//			time.setValorPendente(0D);
			daoControllerVisitanteAgenda.atualizar(time);
		} else {
			pagMandante.ajustarValorPago();
//			pagMandante.setValorPendente(0D);
			daoControllerPagMandante.atualizar(pagMandante);
		}
		acrescentarDados();
		Mensagens.msgInfo("Pendência confirmada com sucesso!!");
	}

	public void editar() {
		novoUsuario = false;
	}

	public void limparCampos() {
		clienteRequest = new NovoCliente();
		tarifa = new Tarifa();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<NovoCliente> getClientes() {
		return clientes;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public List<PagMandante> getPendencias() {
		return pendencias;
	}

	public Double getTotalPend() {
		return totalPend;
	}

	public void setTotalPend(Double totalPend) {
		this.totalPend = totalPend;
	}

	public Double getTotalConfirmados() {
		return totalConfirmados;
	}

	public void setTotalConfirmados(Double totalConfirmados) {
		this.totalConfirmados = totalConfirmados;
	}

	public List<PagMandante> getConfirmados() {
		return confirmados;
	}

	public PagMandante getPagMandante() {
		return pagMandante;
	}

	public void setPagMandante(PagMandante pagMandante) {
		this.pagMandante = pagMandante;
	}

	public NovoCliente getClienteRequest() {
		return clienteRequest;
	}

	public void setClienteRequest(NovoCliente clienteRequest) {
		this.clienteRequest = clienteRequest;
	}
}
