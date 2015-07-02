package asktechforum.dominio;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public interface Pergunta {

	//GETTERS AND SETTERS
	public abstract int getIdPergunta();

	public abstract void setIdPergunta(int idPergunta);

	public abstract String getTitulo();

	public abstract void setTitulo(String titulo);

	public abstract String getDescricao();

	public abstract void setDescricao(String descricao);

	public abstract int getIdUsuario();

	public abstract void setIdUsuario(int idUsuario);

	public abstract String getStrData();

	public abstract void setStrData(String strData);

	public abstract String getStrHora();

	public abstract void setStrHora(String strHora);

	public abstract java.sql.Date getData();

	public abstract void setData(Date data);

	public abstract Time getHora();

	public abstract void setHora(Time hora);

	public abstract String getTag();

	public abstract void setTag(String tag);

	public abstract ArrayList<String> getListTags();

	public abstract void setListTags(ArrayList<String> listTags);
	
	public abstract Usuario getUsuario();

	public abstract void setUsuario(Usuario usuario);

	public abstract List<Resposta> getRespostas() ;

	public abstract void setRespostas(List<Resposta> respostas);

}