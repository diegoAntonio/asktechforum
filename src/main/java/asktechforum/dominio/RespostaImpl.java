package asktechforum.dominio;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import asktechforum.util.Util;

@Entity
@Table(name="resposta")

@NamedQueries({
@NamedQuery(name="Resposta.autor", query="Select r from RespostaImpl r where r.usuario.idUsuario = :id"),
@NamedQuery(name="Resposta.por_pergunta",query="Select r from RespostaImpl r where r.pergunta.idPergunta = :idPergunta")})
public class RespostaImpl implements Resposta {
	
	public static String JPQL_autor = "Resposta.autor";
	public static String JPQL_por_pergunta = "Resposta.por_pergunta";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idResposta;
	
	private String descricao;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="idPergunta")
	private PerguntaImpl pergunta;
	
	private int votos;
	
	private Date data;

	private Time hora;
	
	@Transient
	private String strData;

	@Transient
	private String strHora;
	
	public RespostaImpl() {
	}
	
	//GETTERS AND SETTERS
	@Override
	public int getIdResposta() {
		return idResposta;
	}
	
	@Override
	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}
	
	@Override
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int getIdUsuario() {
		return this.usuario.getIdUsuario();
	}

	@Override
	public void setIdUsuario(int idUsuario) {
		this.usuario.setIdUsuario(idUsuario);
	}

	@Override
	public int getIdPergunta() {
		return this.pergunta.getIdPergunta();
	}

	@Override
	public void setIdPergunta(int idPergunta) {
		this.pergunta.setIdPergunta(idPergunta);
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public Time getHora() {
		return hora;
	}

	@Override
	public void setHora(Time hora) {
		this.hora = hora;
	}

	@Override
	public String getStrData() {
		this.strData = Util.converterDataToString("dd/MM/yyyy", this.data);
		return this.strData;
	}

	@Override
	public void setStrData(String strData) {
		
	  try {
		this.data = Util.converterStringToDate("dd/MM/yyyy", strData);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	  
	}

	@Override
	public String getStrHora() {
		this.strHora = Util.converterTimeToString("dd/MM/yyyy", hora);
		return this.strHora;
	}

	@Override
	public void setStrHora(String strHora) {
		try {
			this.hora = Util.converterStringToTime("dd/MM/yyyy", strHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getNomeUsuario() {
		return this.usuario.getNome();
	}

	@Override
	public void setNomeUsuario(String nomeUsuario) {
		this.usuario.setNome(nomeUsuario);
	}

	@Override
	public String getEmail() {
		return this.usuario.getEmail();
	}
	
	@Override
	public void setEmail(String email) {
		this.usuario.setEmail(email);
	}
	
	@Override
	public int getVotos() {
		return votos;
	}

	@Override
	public void setVotos(int votos) {
		this.votos = votos;
	}

	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Pergunta getPergunta() {
		return pergunta;
	}

	@Override
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = (PerguntaImpl)pergunta;
	}
	
	
}
