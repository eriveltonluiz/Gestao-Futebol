package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class TimeCamp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String tecnico;
	
	private String auxTecnico;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer vitorias;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer derrotas;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer empates;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer pontos;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer golsPro;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer golsContra;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer saldoDeGols;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer qtdCartoesAmarelos;
	
	@Column(columnDefinition = "int(11) default 0")
	private Integer qtdCartoesVermelhos;
	
	@Transient
	private Integer indice;
	
	@Column(columnDefinition = "LONGTEXT")
	private String fotoIconBase64;
	
	private String extensao;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] fotoIconBase64Original;
	
	@ManyToOne
	private Grupo grupo;

	@OneToMany(mappedBy = "time")
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	@OneToMany(mappedBy = "time")
	private List<CampVisitante> visitantes = new ArrayList<CampVisitante>();
	
	@OneToMany(mappedBy = "time")
	private List<CampMandante> mandantes = new ArrayList<CampMandante>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public String getAuxTecnico() {
		return auxTecnico;
	}

	public void setAuxTecnico(String auxTecnico) {
		this.auxTecnico = auxTecnico;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Integer getGolsPro() {
		return golsPro;
	}

	public void setGolsPro(Integer golsPro) {
		this.golsPro = golsPro;
	}

	public Integer getGolsContra() {
		return golsContra;
	}

	public void setGolsContra(Integer golsContra) {
		this.golsContra = golsContra;
	}

	public Integer getSaldoDeGols() {
		return saldoDeGols;
	}

	public void setSaldoDeGols(Integer saldoDeGols) {
		this.saldoDeGols = saldoDeGols;
	}

	public Integer getQtdCartõesAmarelos() {
		return qtdCartoesAmarelos;
	}

	public void setQtdCartõesAmarelos(Integer qtdCartõesAmarelos) {
		this.qtdCartoesAmarelos = qtdCartõesAmarelos;
	}

	public Integer getQtdCartõesVermelhos() {
		return qtdCartoesVermelhos;
	}

	public void setQtdCartõesVermelhos(Integer qtdCartõesVermelhos) {
		this.qtdCartoesVermelhos = qtdCartõesVermelhos;
	}
	
	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getFotoIconBase64Original() {
		return fotoIconBase64Original;
	}

	public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
		this.fotoIconBase64Original = fotoIconBase64Original;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public List<CampMandante> getMandantes() {
		return mandantes;
	}
	
	public List<CampVisitante> getVisitantes() {
		return visitantes;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getVitorias() {
		return vitorias;
	}

	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}

	public Integer getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(Integer derrotas) {
		this.derrotas = derrotas;
	}

	public Integer getEmpates() {
		return empates;
	}

	public void setEmpates(Integer empates) {
		this.empates = empates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auxTecnico == null) ? 0 : auxTecnico.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tecnico == null) ? 0 : tecnico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeCamp other = (TimeCamp) obj;
		if (auxTecnico == null) {
			if (other.auxTecnico != null)
				return false;
		} else if (!auxTecnico.equals(other.auxTecnico))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tecnico == null) {
			if (other.tecnico != null)
				return false;
		} else if (!tecnico.equals(other.tecnico))
			return false;
		return true;
	}

	
}
