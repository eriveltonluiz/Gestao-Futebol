package br.com.erivelton.canchafut.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.erivelton.canchafut.beans.utils.SalvarVisitante;
import br.com.erivelton.canchafut.beans.utils.TemplateSalvarVisitanteEMandante;
import br.com.erivelton.canchafut.dao.DaoJdbc;
import br.com.erivelton.canchafut.dao.controller.DaoControllerAgenda;
import br.com.erivelton.canchafut.dao.controller.DaoControllerAgendamentoGeral;
import br.com.erivelton.canchafut.dao.controller.DaoControllerCliente;
import br.com.erivelton.canchafut.dao.controller.DaoControllerMandanteAgenda;
import br.com.erivelton.canchafut.dao.controller.DaoControllerPagMandante;
import br.com.erivelton.canchafut.dao.controller.DaoControllerTarifas;
import br.com.erivelton.canchafut.dao.controller.DaoControllerVisitanteAgenda;
import br.com.erivelton.canchafut.geral.Mensagens;
import br.com.erivelton.canchafut.model.Agenda;
import br.com.erivelton.canchafut.model.AgendamentoTimes;
import br.com.erivelton.canchafut.model.Cliente;
import br.com.erivelton.canchafut.model.PagMandante;
import br.com.erivelton.canchafut.model.Tarifa;
import br.com.erivelton.canchafut.model.TimeMandante;
import br.com.erivelton.canchafut.model.TimeVisitante;
import br.com.erivelton.canchafut.model.TipoStatusHorario;

@Named(value = "advBean")
@ViewScoped
public class AgendaAdvBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean inicio = false;
	private boolean mandNaoInserido = false;
	private boolean visitNaoInserido = false;
	private boolean validar = false;
	private boolean validarVisit = false;
	private Double valorPorHorario;
	private String alteracaoHorario;
	private String salvarPag;
	private List<AgendamentoTimes> agenda = new ArrayList<AgendamentoTimes>();
	private List<Cliente> times = new ArrayList<Cliente>();
	private List<SelectItem> aux = new ArrayList<SelectItem>();
	private List<SelectItem> mandanteItens = new ArrayList<SelectItem>();
	private List<SelectItem> visitanteItens = new ArrayList<SelectItem>();
	private Tarifa tarifa = new Tarifa();
	private Cliente cliente = new Cliente();
	private PagMandante pagMandante = new PagMandante();
	private AgendamentoTimes agendaTime = new AgendamentoTimes();
	private Agenda agendaEsc = new Agenda();
	private Cliente clienteAux = new Cliente();
	private TimeMandante mandante = new TimeMandante();
	private TimeVisitante visitante = new TimeVisitante();

	private DaoJdbc jdbc = new DaoJdbc();

	@Inject
	private DaoControllerCliente daoControllerCliente;

	@Inject
	private DaoControllerAgendamentoGeral daoControllerAg;

	@Inject
	private DaoControllerTarifas daoControllerTarifas;

	@Inject
	private DaoControllerMandanteAgenda daoControllerMandanteAgenda;

	@Inject
	private DaoControllerVisitanteAgenda daoControllerVisitanteAgenda;

	@Inject
	private DaoControllerPagMandante daoControllerPagMandante;

	@Inject
	private DaoControllerAgenda daoControllerAgenda;

	@PostConstruct
	public void init() {
		if (agendaEsc.getData() == null) {
			try {
				manipularAgendaData(new Date());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		agendaTime = new AgendamentoTimes();
		tarifa = daoControllerTarifas.listarResultSimples(Tarifa.class);
		agenda = jdbc.listarJdbcAg(agendaEsc);

		for (int i = 0; i < agenda.size(); i++) {
			agenda.get(i).setDesabilitado(true);

			agenda.get(i).setarImagensPorTipoStatusHorario();
//			switch (agenda.get(i).getStatusHorario()) {
//
//			case "em aberto":
//				agenda.get(i).setImagem("aberto.png");
//				agenda.get(i).setDesabilitado(false);
//				break;
//
//			case "confirmado":
//				agenda.get(i).setImagem("confirm.png");
//				break;
//
//			case "parcialmente confirmado":
//				agenda.get(i).setImagem("parcial.png");
//				break;
//
//			case "cancelado":
//				agenda.get(i).setImagem("cancel-icon.png");
//				break;
//			}
		}
	}

	public void parametroAgendaData(@SuppressWarnings("rawtypes") SelectEvent event) throws ParseException {
		inicio = true;
		agendaEsc = new Agenda();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String d = sdf.format(event.getObject());
		Date dataEscolhida = sdf.parse(d);
		manipularAgendaData(dataEscolhida);
	}

	private void manipularAgendaData(Date dataEscolhida) throws ParseException {
		Calendar cal = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAtual = format.parse(format.format(new Date()));

		cal.setTime(dataEscolhida);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		if (dataAtual.equals(cal.getTime()) || dataAtual.before(cal.getTime())) {
			if (!(daoControllerAgenda.verificarAgendaExistente(dataEscolhida))) {
				agendaEsc.setData(cal.getTime());
				agendaEsc = daoControllerAgenda.atualizar(agendaEsc);
				daoControllerAgenda.criarAgendaPorData(agendaEsc, cal);
			}

			if (!inicio) {
				agendaEsc = daoControllerAgenda.retornarRegistroPorData(new Date());
				agendaEsc.setData(dataEscolhida);
			} else if (inicio) {
				agendaEsc = daoControllerAgenda.retornarRegistroPorData(dataEscolhida);
				agendaEsc.setData(dataEscolhida);
				init();
			}
			Mensagens.msgInfo("Agenda para data: " + new SimpleDateFormat("dd/MM/yyyy").format(dataEscolhida)
					+ "carregada com sucesso");

		} else {
			Mensagens.msgError("Data escolhida já foi efetuada!!");
		}

		System.out.println(dataEscolhida);
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
	}

	public void salvarMandante() throws CloneNotSupportedException {
		if (mandanteItens == null || mandanteItens.isEmpty()) {
			Mensagens.msgError("ERRO!! Necessário selecionar os horários disponíveis para agenda.");
		} else {
			if (agendaTime.clienteMandante() != null) {
				int ind = agenda.indexOf(agendaTime);
				if (validar) {
					agenda.get(ind).setarNomeTimeMandante(clienteAux.getNomeTime());
//					getMandante().getCliente().setNomeTime(clienteAux.getNomeTime());
				}
				if (agendaTime != null && agendaTime.nomeTimeClienteMandante() != null) {
					clienteAux = (Cliente) agendaTime.clienteMandante().clone();
//					mandante = agendaTime.getMandante();
//
//					Calendar cal = Calendar.getInstance();
//					cal.setTime(mandante.getHorario());
//					cal.add(Calendar.HOUR_OF_DAY, 3);
//
//					mandante.setHorario(cal.getTime());
//					mandante = daoControllerMandanteAgenda.atualizar(mandante);
//
//					cal.add(Calendar.HOUR_OF_DAY, -3);
//					mandante.setHorario(cal.getTime());
//
//					agendaTime.getMandante().setHorario(cal.getTime());
					agendaTime.AdicionarMandante(daoControllerMandanteAgenda);
					mandNaoInserido = false;
					Mensagens.msgInfo("salvo com sucesso!!");
				} else {
					if (agenda.get(ind).nomeTimeVisitante() != null) {
						agenda.get(ind).setarClienteMandante(clienteAux);
					}
					Mensagens.msgError("Cadastro na agenda inválido!!");
				}
			} else {
				agendaTime.setarClienteMandante(agendaTime.getCliente());
				Mensagens.msgError("Não é possível cadastrar mandante nulo!!");
			}
		}
	}

	//Reajuste Funcionou
	public void salvarVisitante() {
		int indice = agenda.indexOf(agendaTime);
		
		SalvarVisitante salvarVisitante = new SalvarVisitante(
				visitanteItens, agenda.get(indice), validarVisit, visitNaoInserido, 
				daoControllerMandanteAgenda.verificarMandanteHorario(agendaTime), visitante, 
				daoControllerVisitanteAgenda, agendaEsc.getData()
		);
		
		TemplateSalvarVisitanteEMandante templateVisitante = salvarVisitante;
		
		visitNaoInserido = salvarVisitante.getVisitNaoInserido();
		templateVisitante.esqueleto(agendaTime);
//		if (visitanteItens == null || visitanteItens.isEmpty()) {
//			Mensagens.msgError("ERRO!! Necessário selecionar os horários disponíveis para agenda!!");
//		} else {
//			if (agendaTime.clienteVisitante() != null) {
//				int ind = agenda.indexOf(agendaTime);
//				VerificarClienteMandante validacaoClienteMandante = 
//						new VerificarClienteMandante(
//							new FimValidacaoVisit(), 
//							daoControllerVisitanteAgenda, agendaTime, agenda.get(ind), visitante, visitNaoInserido, 
//							daoControllerMandanteAgenda.verificarMandanteHorario(agendaTime), agendaEsc.getData()
//						);
//
//				//Implementação do padrão chain of responsability, porém deve ser corrigido o FimValidacao pois gerá NullPointerException
//				ValidacaoVisitante validacaoVisitante = new SetarTimeVisitanteNaAgenda(
//						new VerificarIdDoClienteMandante(validacaoClienteMandante), 
//						visitante.getNomeTimeVisit(),
//						validarVisit
//				);
//				
//				validacaoVisitante.validarVisitante(agendaTime);
//				visitNaoInserido = validacaoClienteMandante.retornarFlagVisitNaoInserido();
//				visitante = validacaoClienteMandante.retornarVisitanteSalvo();
//			} else {
//				Mensagens.msgError("Não é possível cadastrar visitante nulo!!");
//			}
				
//			if (agendaTime.clienteVisitante() != null) {
//				int ind = agenda.indexOf(agendaTime);
//
//				if (validarVisit) {
//					agenda.get(ind).setarNomeTimeVisitante(visitante.getNomeTimeVisit());
//				}
//
//				if (agendaTime.getMandante().getCliente().getIdCliente() == 0) {
//					agendaTime.getMandante().setCliente(null);
//				}
//
//				if (agendaTime.getMandante()
//						.getCliente() != null/* daoControllerMandanteAgenda.verificarMandanteHorario(agendaTime) */) {
//					if (daoControllerMandanteAgenda.verificarMandanteHorario(agendaTime)) {
//						agendaTime.getVisitante().setData(agendaEsc.getData());
//						if (agendaTime.getVisitante().getCliente() == null) {
//							agendaTime.getVisitante().setNomeTimeVisit(null);
//						} else {
//							agendaTime.getVisitante()
//									.setNomeTimeVisit(agendaTime.getVisitante().getCliente().getNomeTime());
//						}
//						visitante = daoControllerVisitanteAgenda.atualizar(agendaTime.getVisitante());
//
//						// agenda.set(ind, agendaTime);
//						// agenda.get(ind).getVisitante().setNomeTimeVisit(agendaTime.getVisitante().getCliente().getNomeTime());
//						visitNaoInserido = false;
//						Mensagens.msgInfo("salvo com sucesso!!");
//					} else {
//						agenda.get(ind).getMandante().setCliente(null);
//						Mensagens.msgError("Necessário o cadastro do time mandante!!");
//					}
//				} else {
//					agenda.get(ind).getMandante().setCliente(null);
//					Mensagens.msgError("Necessário o cadastro do time mandante!!");
//				}
//			} else {
//				Mensagens.msgError("Não é possível cadastrar visitante nulo!!");
//			}
//		}
	}
	
//reajuste funcionou
	public void deletarMandante() throws CloneNotSupportedException {
		if (visitanteItens == null || visitanteItens.isEmpty()) {
			Mensagens.msgError("ERRO!! Necessário selecionar os horários disponíveis para agenda!!");
		} else {
			int ind = agenda.indexOf(agendaTime);

			if (mandNaoInserido) {
				validar = true;
				clienteAux = (Cliente) agendaTime.getMandante().getCliente().clone();
				agenda.get(ind).getMandante().getCliente().setNomeTime(null);
			}

			if (daoControllerMandanteAgenda.verificarMandanteHorario(agendaTime)) {
				agendaTime.removerMandante(daoControllerMandanteAgenda);
				daoControllerVisitanteAgenda.remover(agendaTime.getVisitante());
				
				Mensagens.msgInfo("Mandante removido com sucesso da agenda");
			} else {
				Mensagens.msgError("ERRO!! Necessário inserir um mandante para remoção!!");
			}
		}
	}

	public void deletarVisitante() throws CloneNotSupportedException {

		if (visitanteItens == null || visitanteItens.isEmpty()) {
			Mensagens.msgError("ERRO!! Necessário selecionar os horários disponíveis para agenda!!");
		} else {

			int ind = agenda.indexOf(agendaTime);
			// agendaTime.setVisitante(visitante);
			if (visitNaoInserido) {
				validarVisit = true;
				visitante = (TimeVisitante) agendaTime.getVisitante().clone();
				agenda.get(ind).getVisitante().setNomeTimeVisit(null);
			}

			if ((agenda.get(ind).getMandante().getCliente().getNomeTime() != null
					|| agenda.get(ind).getMandante().getCliente() != null)
					&& agendaTime.getVisitante().getNomeTimeVisit() != null && !visitNaoInserido) {
				agendaTime.removerVisitante(daoControllerVisitanteAgenda, agendaEsc.getData());
//				agendaTime.getVisitante().setData(agendaEsc.getData());
//				agendaTime.getVisitante().setCliente(null);
//				agendaTime.getVisitante().setNomeTimeVisit(null);
//				daoControllerVisitanteAgenda.remover(agendaTime.getVisitante());
				Mensagens.msgInfo("Visitante removido com sucesso da agenda!!");
			} else {
				Mensagens.msgError("ERRO!! Necessário a remoção do mandante!!");
			}
		}
	}

	public void atualizarValorPendenteVisit() {
		agendaTime.atualizarValorPendenteVisit(valorPorHorario);
//		if (agendaTime.getVisitante().getValorPago() <= valorPorHorario) {
//			agendaTime.getVisitante().setValorPendente(valorPorHorario - agendaTime.getVisitante().getValorPago());
//		} else {
//			agendaTime.getVisitante().setValorPendente(null);
//			agendaTime.getVisitante().setValorPago(null);
//			Mensagens.msgError("ERRO!! valor pago não pode ultrapassar o valor do horário!!");
//		}
	}

	public void atualizarValorPendenteMand() {
		pagMandante.atualizarValorPendenteMand(valorPorHorario, agendaEsc.getData(), agendaTime.getMandante());
//		if (pagMandante.getValorPago() <= valorPorHorario) {
//			pagMandante.setValorPendente(valorPorHorario - pagMandante.getValorPago());
//			pagMandante.setDataPag(agendaEsc.getData());
//			pagMandante.setMandante(agendaTime.getMandante());
//		} else {
//			pagMandante.setValorPago(null);
//			pagMandante.setValorPendente(null);
//			Mensagens.msgError("ERRO!! valor pago não pode ultrapassar o valor do horário!!");
//		}
	}

	public void manipularHorario() {
		@SuppressWarnings("unused")
		int ind = agenda.indexOf(agendaTime);
		Double valorPago = agendaTime.getVisitante().getValorPago();
		Double valorPendente = agendaTime.getVisitante().getValorPendente();

		if (agendaTime.idVisitante() == 0) {
			agendaTime.atualizarVisitanteESeusValores(daoControllerVisitanteAgenda, valorPago, valorPendente);
//			agendaTime.setVisitante(daoControllerVisitanteAgenda.returnVisitante(agendaTime.getVisitante().getData(),
//					agendaTime.getVisitante().getNomeTimeVisit()));
//			agendaTime.getVisitante().setValorPago(valorPago);
//			agendaTime.getVisitante().setValorPendente(valorPendente);
		}

		switch (alteracaoHorario) {
		case "cancelar":
			agendaTime.atualizarStatusHorario("cancelado", "cancel-icon.png", true);
//			agendaTime.setStatusHorario("cancelado");
//			agendaTime.setImagem("cancel-icon.png");
//			agendaTime.setDesabilitado(true);
			Mensagens.msgInfo("Horário cancelado com sucesso!!");
			break;

		case "confirmar":
			pagMandante = daoControllerPagMandante.atualizar(pagMandante);
			TimeVisitante visit = agendaTime.getVisitante();
			visit.setData(agendaEsc.getData());
			visit = daoControllerVisitanteAgenda.atualizar(visit);

			agendaTime.atualizarStatusHorarioConfirmado(pagMandante.getValorPendente());
//			if (pagMandante.getValorPendente() == 0 && agendaTime.getVisitante().getValorPendente() == 0) {
//				agendaTime.setStatusHorario("confirmado");
//				agendaTime.setImagem("confirm.png");
//				agendaTime.setDesabilitado(true);
//			} else {
//				agendaTime.setStatusHorario("parcialmente confirmado");
//				agendaTime.setImagem("parcial.png");
//				agendaTime.setDesabilitado(true);
//			}
			pagMandante = new PagMandante();
			Mensagens.msgInfo("Horário confirmado com sucesso");
			break;
		}
		agendaTime = daoControllerAg.atualizar(agendaTime);
	}

	public void atualizarTarifas() {
		tarifa = daoControllerTarifas.atualizar(tarifa);
	}

	public void verificarHorario() {
		if (agendaTime.getMandante().getCliente().getNomeTime() == null
				|| agendaTime.getVisitante().getNomeTimeVisit() == null) {
			Mensagens.msgError("ERRO!! Necessário o cadastro completo de times do horário.");
		} else {
			PrimeFaces.current().executeScript("PF('dialogCanc').show()");
		}
	}

	public void dadosHorario() {
		if (agendaTime.getMandante().getCliente().getNomeTime() == null
				|| agendaTime.getVisitante().getNomeTimeVisit() == null) {
			Mensagens.msgError("ERRO!! Necessário o cadastro completo de times do horário.");
		} else {
			TimeMandante tm = agendaTime.getMandante();

			if ((tm.getHorario().after(tarifa.getHorarioInicDiurno())
					|| tm.getHorario().equals(tarifa.getHorarioInicDiurno()))
					&& (tm.getHorario().before(tarifa.getHorarioInicNoturno()))) {
				valorPorHorario = tarifa.getValorHoursDiurno();
			} else {
				valorPorHorario = tarifa.getValorHoursNoturno();
			}
			PrimeFaces.current().executeScript("PF('dialogConfirm').show()");
		}
	}

	public void carregarTimes() {
		Mensagens.msgInfo("Times carregados com sucesso!!");
		if (times.isEmpty() || times == null) {
			times = daoControllerCliente.listar(Cliente.class);

			for (Cliente c : times) {
				aux.add(new SelectItem(c, c.getNomeTime()));
				mandanteItens.add(new SelectItem(c, c.getNomeTime()));
				visitanteItens.add(new SelectItem(c, c.getNomeTime()));
			}
		}
	}

	public void popularCampoCliente(AjaxBehaviorEvent event) {
		mandNaoInserido = true;
		Cliente codTime = (Cliente) ((HtmlSelectOneMenu) event.getSource()).getValue();
		if (codTime != null) {
			agendaTime.getMandante().getCliente().setNomeUser(codTime.getNomeUser());
		}
	}

	public void popularCampoClienteVisit(AjaxBehaviorEvent event) {
		visitNaoInserido = true;
		Cliente codVisitante = (Cliente) ((HtmlSelectOneMenu) event.getSource()).getValue();
		if (codVisitante != null) {
			agendaTime.getVisitante().getCliente().setNomeUser(codVisitante.getNomeUser());
		}
	}

	public List<AgendamentoTimes> getAgenda() {
		return agenda;
	}

	public AgendamentoTimes getAgendaTime() {
		return agendaTime;
	}

	public void setAgendaTime(AgendamentoTimes agendaTime) {
		this.agendaTime = agendaTime;
	}

	public List<Cliente> getTimes() {
		return times;
	}

	public void setTimes(List<Cliente> times) {
		this.times = times;
	}

	public void setAux(List<SelectItem> aux) {
		this.aux = aux;
	}

	public List<SelectItem> getMandanteItens() {
		return mandanteItens;
	}

	public void setMandanteItens(List<SelectItem> mandanteItens) {
		this.mandanteItens = mandanteItens;
	}

	public List<SelectItem> getVisitanteItens() {
		return visitanteItens;
	}

	public void setVisitanteItens(List<SelectItem> visitanteItens) {
		this.visitanteItens = visitanteItens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public Double getValorPorHorario() {
		return valorPorHorario;
	}

	public void setValorPorHorario(Double valorPorHorario) {
		this.valorPorHorario = valorPorHorario;
	}

	public String getAlteracaoHorario() {
		return alteracaoHorario;
	}

	public void setAlteracaoHorario(String alteracaoHorario) {
		this.alteracaoHorario = alteracaoHorario;
	}

	public String getSalvarPag() {
		return salvarPag;
	}

	public void setSalvarPag(String salvarPag) {
		this.salvarPag = salvarPag;
	}

	public PagMandante getPagMandante() {
		return pagMandante;
	}

	public void setPagMandante(PagMandante pagMandante) {
		this.pagMandante = pagMandante;
	}

	public Agenda getAgendaEsc() {
		return agendaEsc;
	}

	public void setAgendaEsc(Agenda agendaEsc) {
		this.agendaEsc = agendaEsc;
	}

	public TimeMandante getMandante() {
		return mandante;
	}

	public void setMandante(TimeMandante mandante) {
		this.mandante = mandante;
	}

	public TimeVisitante getVisitante() {
		return visitante;
	}

	public void setVisitante(TimeVisitante visitante) {
		this.visitante = visitante;
	}

}
