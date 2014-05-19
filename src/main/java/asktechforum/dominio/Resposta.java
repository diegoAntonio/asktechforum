package asktechforum.dominio;

public class Resposta {
	private int idResposta;
	private String descricao;
	
	public Resposta() {
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
}