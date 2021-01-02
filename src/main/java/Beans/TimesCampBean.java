package Beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import Beans.arquivos.LoadDocuments;
import Beans.arquivos.LoadImage;
import Daos.controller.DaoControllerGrupos;
import Daos.controller.DaoControllerJogadores;
import Daos.controller.DaoControllerTimesCamp;
import Geral.Mensagens;
import Model.Grupo;
import Model.Jogador;
import Model.TimeCamp;

@Named(value = "timesCampBean")
@ViewScoped
public class TimesCampBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int qtd;
	private boolean novoUsuario;
	private byte[] imagemByte;
	private UploadedFile upf;
	private UploadedFile fileInserir;
	private TimeCamp time = new TimeCamp();
	private Jogador jogador = new Jogador();
	private List<TimeCamp> times = new ArrayList<TimeCamp>();

	@Inject
	private DaoControllerTimesCamp daoControllerTimes;

	@Inject
	private DaoControllerJogadores daoControllerJogadores;

	@Inject
	private DaoControllerGrupos daoControllerGrupo;

	@PostConstruct
	public void listaTimes() {
		times = daoControllerTimes.listar(TimeCamp.class);
		novoUsuario = true;
	}

	public void handleFileUpload(FileUploadEvent event) {
		upf = event.getFile();
		imagemByte = event.getFile().getContent();
	}

	public void inserirTime(){
		if (daoControllerTimes.verificarExistenciaCampeonato()) {
			if (time.getNome() == null) {
				Mensagens.msgError("Necessário inserir o nome do time!!");
			} else {
				if (upf != null) {
					String[] imagem = LoadImage.getImagem(upf, imagemByte);
					time.setFotoIconBase64Original(imagemByte);

					time.setFotoIconBase64(imagem[0]);
					time.setExtensao(imagem[1]);
				}

				time = daoControllerTimes.atualizar(time);

				if (novoUsuario) {
					times.add(time);
					novoUsuario = false;
				} else {
					for (int i = 0; i < times.size(); i++) {
						if (times.get(i).getId() == time.getId()) {
							times.set(i, time);
							break;
						}
					}
				}
				time = new TimeCamp();
				upf = null;
				imagemByte = null;
				Mensagens.msgInfo("Salvo com sucesso");
			}
		} else {
			Mensagens.msgWarn("Não é possível cadastrar um novo time com campeonato em andamento!!");
		}
	}

	public void removerTime() {
		if (daoControllerTimes.verificarExistenciaCampeonato()) {
			daoControllerTimes.remover(time);
			times.remove(time);
			time = new TimeCamp();
			Mensagens.msgInfo("Time excluído com sucesso");
			listaTimes();
		} else {
			Mensagens.msgWarn("Não é possível remover times com campeonato em andamento!!");
		}
	}

	public void limparCamposTime() {
		time = new TimeCamp();
	}

	// Operações da classe jogador

	public void listaJogadores() {
		TimeCamp varAux = new TimeCamp();
		varAux = time;
		time = daoControllerTimes.listarTotal(time);
		novoUsuario = true;

		if (time == null) {
			setTime(varAux);
		}
	}

	public void novo() {
		time = new TimeCamp();
		jogador = new Jogador();
		// listaTimes();
	}

	public void limparCamposJogador() {
		jogador = new Jogador();
		upf = null;
	}

	public void inserirAoTodo(FileUploadEvent event) throws IOException {

		this.fileInserir = event.getFile();
		InputStream arquivo = fileInserir.getInputStream();

		if (fileInserir.getFileName().contains("docx")) {

			try {
				List<XWPFParagraph> paragrafos = LoadDocuments.getDocumentDocx(arquivo, fileInserir);

				for (XWPFParagraph xwpf : paragrafos) {
					String[] linhas = xwpf.getText().split(",");

					Jogador j = new Jogador();
					j.setNome(linhas[0]);
					j.setRg(Integer.parseInt(linhas[1]));
					j.setNumCamisa(Integer.parseInt(linhas[2]));
					j.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(linhas[3]));
					j.setGols(0);
					j.setGolsPorJogo(0);
					j.setQtdAmarelosJogo(0);
					j.setQtdCartoesAmarelos(0);
					j.setQtdVermelhosJogo(0);
					j.setQtdCartoesVermelhos(0);
					j.setTime(time);

					if (daoControllerJogadores.verificarNumeroJogador(j) == 0L) {

						j = daoControllerJogadores.atualizar(j);
						System.out.print(j.getNome() + " ");
						System.out.print(j.getRg() + " ");
						System.out.print(j.getNumCamisa() + " ");
						System.out.println(j.getDataNascimento());
					} else {
						Mensagens.msgWarn("Jogador com o número " + j.getNumCamisa() + " já existente no plantel!!");
					}
				}

				arquivo.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (fileInserir.getFileName().contains("xlsx")) {

			List<Jogador> listaJogadores = LoadDocuments.getDocumentXlsx(arquivo, fileInserir);

			for (Jogador j : listaJogadores) {
				j.setTime(time);
				j.setGols(0);
				j.setGolsPorJogo(0);
				j.setQtdAmarelosJogo(0);
				j.setQtdCartoesAmarelos(0);
				j.setQtdVermelhosJogo(0);
				j.setQtdCartoesVermelhos(0);
				if (daoControllerJogadores.verificarNumeroJogador(j) == 0L) {
					j = daoControllerJogadores.atualizar(j);
					System.out.print(time.getNome());
					System.out.print(j.getNome());
					System.out.print(j.getRg());
					System.out.print(j.getNumCamisa());
					System.out.println(j.getDataNascimento());
					System.out.println("-------");
				} else {
					Mensagens.msgWarn("Jogador com o número " + j.getNumCamisa() + " já existente no plantel!!");
				}
			}

			arquivo.close();

		}
		/*
		 * else if() { PDDocument document = PDDocument.load(arquivo); PDFTextStripper
		 * pdf = new PDFTextStripper(); }
		 */
	}

	public void inserirJogadores() {
		if (novoUsuario) {
			jogador.setGols(0);
			jogador.setGolsPorJogo(0);
			jogador.setQtdAmarelosJogo(0);
			jogador.setQtdCartoesAmarelos(0);
			jogador.setQtdVermelhosJogo(0);
			jogador.setQtdCartoesVermelhos(0);
		}
		jogador.setTime(time);
		jogador = daoControllerJogadores.atualizar(jogador);

		if (novoUsuario) {
			time.getJogadores().add(jogador);
		}

		novoUsuario = true;
		jogador = new Jogador();
		Mensagens.msgInfo("Salvo com sucesso");
	}

	public void removerJogadores() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext context = facesContext.getExternalContext();
		String id = context.getRequestParameterMap().get("codigojogador");

		Jogador jog = new Jogador();
		jog.setId(Long.parseLong(id));
		daoControllerJogadores.remover(jog);
		time.getJogadores().remove(jog);
		Mensagens.msgInfo("Jogador excluído com sucesso!!");
	}

	public void inserirGrupos() {
		if(qtd != 0 && qtd % 2 == 0) {
			if(times.size() % qtd == 0 && times.size() / qtd >= 4) {
				for (int i = 1; i <= qtd; i++) {
					Grupo grupo = new Grupo();
					grupo.setDescricao("Grupo " + i);
					daoControllerGrupo.atualizar(grupo);
				}

				try {
					ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(ec.getRequestContextPath() + "/pagesCamp/sorteioTimes.jsf");
				} catch (IOException e) {
					e.printStackTrace();
				}
				// PrimeFaces.current().executeScript("PF('dlgConfirm').hide()");
				// PrimeFaces.current().executeScript("PF('dlgSorteio').show()");
			} else {
				Mensagens.msgError("Quantidade de times no grupo deve ser maior que 4!!");
			}
		} else {
			Mensagens.msgError("Quantidade de grupos inapropriada!!");
		}
	}

	public String solicitarRelatorio() {
		if (daoControllerTimes.verificarExistenciaCampeonato()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext context = facesContext.getExternalContext();
			context.getSessionMap().put("sorteio", "sorteado");

			if (daoControllerTimes.qtdRegistrosGeral(TimeCamp.class) >= 16) {
				/*
				 * for (TimeCamp time : times) { //time = daoControllerTimes.listarTotal(time);
				 * 
				 * if (time == null /* || time.getJogadores().size() < 8 ) {
				 * Mensagens.msgError("Time " + time.getNome() +
				 * " não possui jogadores suficientes"); break; } }
				 */
				if (daoControllerGrupo.qtdRegistrosGeral(Grupo.class) != 0) {
					return "/pagesCamp/sorteioTimes.jsf?faces-redirect=true";
				} else {
					PrimeFaces.current().executeScript("PF('dlgConfirm').show()");
				}
			}

		} else {
			Mensagens.msgWarn("Campeonato já existente!!");
			// PrimeFaces.current().executeScript("PF('dlgConfirm').hide()");
		}
		return "";
	}

	public String direcionarGrupos() {
		if (daoControllerTimes.verificarExistenciaCampeonato()) {
			/*FacesContext fc = FacesContext.getCurrentInstance();
		    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
		    String usuario = (String) session.getAttribute("usuarioLogado");
			if(usuario == null) {*/
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ExternalContext context = facesContext.getExternalContext();
				context.getSessionMap().put("sorteio", "sorteado");
				return "/pagesCamp/sorteioTimes.jsf?faces-redirect=true";
			
		} else {
			Mensagens.msgWarn("Campeonato já existente");
			PrimeFaces.current().executeScript("PF('dlgConfirm').hide()");
		}
		return "";
	}

	public void editar() {
		novoUsuario = false;
	}

	public List<TimeCamp> getTimes() {
		return times;
	}

	public TimeCamp getTime() {
		return time;
	}

	public void setTime(TimeCamp time) {
		this.time = time;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public UploadedFile getUpf() {
		return upf;
	}

	public void setUpf(UploadedFile upf) {
		this.upf = upf;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

}
