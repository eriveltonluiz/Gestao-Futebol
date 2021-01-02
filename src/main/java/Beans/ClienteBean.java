package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import Beans.arquivos.LoadImage;
import Daos.DaoJdbc;
import Daos.controller.DaoControllerCliente;
import Daos.controller.DaoControllerPagMandante;
import Daos.controller.DaoControllerTarifas;
import Daos.controller.DaoControllerVisitanteAgenda;
import Geral.Mensagens;
import Model.Cliente;
import Model.PagMandante;
import Model.Tarifa;
import Model.TimeVisitante;

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
	private Tarifa tarifa = new Tarifa();
	private PagMandante pagMandante = new PagMandante();
	private List<Cliente> clientes = new ArrayList<>();
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
		clientes = daoControllerCliente.listar(Cliente.class);
		novoUsuario = true;
	}

	public void handleFileUpload(FileUploadEvent file) {
		upf = file.getFile();
		imagemByte = file.getFile().getContent();
	}

	public void salvar() {
		if (upf != null) {
			String[] imagens = LoadImage.getImagem(upf, imagemByte);
			cliente.setFotoIconBase64Original(imagemByte);
			cliente.setFotoIconBase64(imagens[0]);
			cliente.setExtensao(imagens[1]);
		}

		cliente = daoControllerCliente.atualizar(cliente);

		if (novoUsuario) {
			novoUsuario = false;
			clientes.add(cliente);
		} else {
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getIdCliente() == cliente.getIdCliente()) {
					clientes.set(i, cliente);
					break;
				}
			}
		}

		if (cliente != null) {
			upf = null;
			imagemByte = null;
			Mensagens.msgInfo("Salvo com sucesso!!");
		} else {
			Mensagens.msgError("Nome digitado já existe!!");
		}
	}

	public void deletar() {
		daoControllerCliente.remover(cliente);
		cliente = new Cliente();
		clientes.remove(cliente);
		Mensagens.msgInfo("Cliente excluído com sucesso!!");
	}

	public void acrescentarDados() {
		tarifa = daoControllerTarifas.listarResultSimples(Tarifa.class);
		pendencias = dao.listaPendencias(cliente);
		confirmados = dao.listaHorariosConfirmados(cliente);
		totalPend = pendencias.stream().mapToDouble(i -> i.getValorPendente()).sum();
		totalConfirmados = confirmados.stream().mapToDouble(i -> i.getValorPago()).sum();
		totalConfirmados = totalConfirmados + pendencias.stream().mapToDouble(i -> i.getValorPago()).sum();
		
		for (int j = 0; j < pendencias.size(); j++) {
			pendencias.get(j).setStatus("icon-editar.png");
		}
	}
	
	public void manipularPendencia() {
		if(pagMandante.getSituacao() != null) {
			TimeVisitante time = new TimeVisitante();
			time.setData(pagMandante.getDataPag());
			time.setNomeTimeVisit(pagMandante.getSituacao());
			time = daoControllerVisitanteAgenda.returnVisitante(time);
			time.setValorPago(time.getValorPago() + time.getValorPendente());
			time.setValorPendente(0D);
			daoControllerVisitanteAgenda.atualizar(time);
			acrescentarDados();
		} else {
			pagMandante.setValorPago(pagMandante.getValorPago() + pagMandante.getValorPendente());
			pagMandante.setValorPendente(0D);
			daoControllerPagMandante.atualizar(pagMandante);
			acrescentarDados();
		}
		Mensagens.msgInfo("Pendência confirmada com sucesso!!");
	}

	public void editar() {
		novoUsuario = false;
	}

	public void limparCampos() {
		cliente = new Cliente();
		tarifa = new Tarifa();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
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
}
