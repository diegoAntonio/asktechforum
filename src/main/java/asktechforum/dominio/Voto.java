package asktechforum.dominio;

public interface Voto {

	//GETTERS AND SETTERS
	int getIdVoto();

	void setIdVoto(int idVoto);

	int getIdUsuario();

	void setIdUsuario(int idUsuario);

	int getIdResposta();

	void setIdResposta(int idResposta);
	
	public Usuario getUsuario();

	public void setUsuario(Usuario usuario);

	public RespostaImpl getResposta();

	public void setResposta(RespostaImpl resposta);

}