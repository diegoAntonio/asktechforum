package asktechforum.dominio;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

import asktechforum.util.Util;

public class Pergunta {
	private int idPergunta;
	private String titulo;
	private String descricao;
	private int usuario;
	private String strData;
	private String strHora;
	private String tag;
	private Date data;
	private Time hora;
	
	
	public Pergunta() {
		
	}
	
	//GETTERS AND SETTERS
	public int getIdPergunta() {
		return idPergunta;
	}
	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public String getStrData() {
		strData = Util.converterDataToString("dd/MM/yyyy", data);
		return strData;
	}

	public void setStrData(String strData){
		
		try {
			this.data = Util.converterStringToDate("dd/MM/yyyy", strData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getStrHora() {
		strHora = Util.converterTimeToString("hh:mm", hora);
		return strHora;
	}

	public void setStrHora(String strHora)  {
		try {
			this.hora = Util.converterStringToTime("hh:mm", strHora);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public java.sql.Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
