package asktechforum.dominio;

public interface ResultConsultarPergunta extends Pergunta{

	public abstract String getAutor();

	public abstract void setAutor(String autor);

	public abstract int getQtdResposta();

	public abstract void setQtdResposta(int qtdResposta);

}