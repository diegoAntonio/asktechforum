package asktechforum.dominio;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import asktechforum.util.Util;

@Entity
@Table(name = "pergunta")
@NamedQueries({@NamedQuery(name="Pergunta.autor",query="select p from PerguntaImpl p where p.usuario.idUsuario = :id"),
			  @NamedQuery(name="Pergunta.tags",query="select distinct p.tag from PerguntaImpl p"),
			  @NamedQuery(name="Pergunta.por_tag",query="select distinct p from PerguntaImpl p where p.tag = :tag"),
			  @NamedQuery(name="Pergunta.agrupada",query="select p from PerguntaImpl p where p.tag in ("
			  		+ "select p2.tag from "
			  		+ " PerguntaImpl p2 group by p2.tag)")})
public class PerguntaImpl implements ResultConsultarPergunta{
	
	public static String JPQL_autor = "Pergunta.autor";
	public static String JPQL_tags = "Pergunta.tags";
	public static String JPQL_por_tag = "Pergunta.por_tag";
	public static String JPQL_agrupadas = "Pergunta.agrupada";

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPergunta;
	
	private String titulo;
	
	private String descricao;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@OneToMany(cascade=CascadeType.REFRESH)
	@JoinColumn(name="idPergunta")
	private List<RespostaImpl> respostas;

	@Transient
	private String strData;

	@Transient
	private String strHora;
	
	private String tag;
	
	@Transient
	private ArrayList<String> listTags;
	
	private Date data;
	
	private Time hora;
	
	
	public PerguntaImpl() {
		
	}
	
	@Override
	public int getIdPergunta() {
		return idPergunta;
	}
	
	@Override
	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}
	
	@Override
	public String getTitulo() {
		return titulo;
	}
	
	@Override
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	public String getStrData() {
		strData = Util.converterDataToString("dd/MM/yyyy", data);
		return strData;
	}

	/* (non-Javadoc)
	 * @see asktechforum.dominio.Pergunta#setStrData(java.lang.String)
	 */
	@Override
	public void setStrData(String strData){
		
		try {
			this.data = Util.converterStringToDate("dd/MM/yyyy", strData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see asktechforum.dominio.Pergunta#getStrHora()
	 */
	@Override
	public String getStrHora() {
		strHora = Util.converterTimeToString("hh:mm", hora);
		return strHora;
	}

	@Override
	public void setStrHora(String strHora)  {
		try {
			this.hora = Util.converterStringToTime("hh:mm", strHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public java.sql.Date getData() {
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
	public String getTag() {
		return tag;
	}

	@Override
	public void setTag(String tag) {
		this.tag = tag;
		this.listTags = this.separaTags(tag);
	}

	@Override
	public ArrayList<String> getListTags() {
		return listTags;
	}

	@Override
	public void setListTags(ArrayList<String> listTags) {
		this.listTags = listTags;
	}
	
	/**
	 * Tratamento específico para separar as tags numa lista de tags.
	 * @param tag
	 * @return
	 */
	private ArrayList<String> separaTags(String tag){
		ArrayList<String> retorno = new ArrayList<String>();
		if(tag != null) {
			String[] tagArray = tag.split(" ");
			for(int i=0; i<tagArray.length; i++){
				retorno.add(tagArray[i]);
			}
		}
		return retorno;
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
	public List<RespostaImpl> getRespostas() {
		return this.respostas;
	}

	@Override
	public void setRespostas(List<RespostaImpl> respostas) {
		this.respostas = respostas;
	}

	@Override
	public String getAutor() {
		return this.usuario.getNome();
	}

	@Override
	public void setAutor(String autor) {
		this.usuario.setNome(autor);
	}

	@Override
	public int getQtdResposta() {
		return this.respostas.size();
	}

	@Override
	public void setQtdResposta(int qtdResposta) {
		throw new IllegalArgumentException("Andre disse que ia da merda e Diego nao ouviu");
	}

}
