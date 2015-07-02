package asktechforum.dominio;

import java.sql.Date;
import java.sql.Time;

public interface Resposta {

	//GETTERS AND SETTERS
	public abstract int getIdResposta();

	public abstract void setIdResposta(int idResposta);

	public abstract String getDescricao();

	public abstract void setDescricao(String descricao);

	public abstract int getIdUsuario();

	public abstract void setIdUsuario(int idUsuario);

	public abstract int getIdPergunta();

	public abstract void setIdPergunta(int idPergunta);

	public abstract Date getData();

	public abstract void setData(Date data);

	public abstract Time getHora();

	public abstract void setHora(Time hora);

	public abstract String getStrData();

	public abstract void setStrData(String strData);

	public abstract String getStrHora();

	public abstract void setStrHora(String strHora);

	public abstract String getNomeUsuario();

	public abstract void setNomeUsuario(String nomeUsuario);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract int getVotos();

	public abstract void setVotos(int votos);
	
	public abstract Usuario getUsuario() ;

	public abstract void setUsuario(Usuario usuario) ;

	public abstract Pergunta getPergunta() ;

	public abstract void setPergunta(Pergunta pergunta);

}