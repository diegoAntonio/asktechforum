package asktechforum.dominio;

public class Pergunta {
	private int idPergunta;
	private String titulo;
	private String descricao;
	
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
}