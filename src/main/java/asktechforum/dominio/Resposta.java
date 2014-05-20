package asktechforum.dominio;

import java.sql.Date;
import java.text.ParseException;

import asktechforum.util.Util;

public class Resposta {
	private int idResposta;
	private String descricao;
	private int idUsuario;
	private int idPergunta;
	private Date data;
	private Date hora;
	private String strData;
	private String strHora;
	
	private Util util;

	
	public Resposta() {
		util = new Util();
	}
	
	//GETTERS AND SETTERS
	public int getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getStrData() {
		strData = util.converterDataToString("dd/MM/yyyy", this.data);
		return strData;
	}

	public void setStrData(String strData) {
		
	  try {
		this.data = util.converterStringToDate("dd/MM/yyyy", strData);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	}

	public String getStrHora() {
		strHora = util.converterDataToString("dd/MM/yyyy", data);
		return strHora;
	}

	public void setStrHora(String strHora) {
		try {
			this.hora = util.converterStringToDate("dd/MM/yyyy", strHora);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
