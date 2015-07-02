package asktechforum.dominio;

public class ResultConsultarPerguntaImpl extends PerguntaImpl implements ResultConsultarPergunta {
	
	private String autor;
	private int qtdResposta;
	
	public ResultConsultarPerguntaImpl(){
		super();
	}

	/* (non-Javadoc)
	 * @see asktechforum.dominio.ResultConsultarPergunta#getAutor()
	 */
	@Override
	public String getAutor() {
		return autor;
	}

	/* (non-Javadoc)
	 * @see asktechforum.dominio.ResultConsultarPergunta#setAutor(java.lang.String)
	 */
	@Override
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/* (non-Javadoc)
	 * @see asktechforum.dominio.ResultConsultarPergunta#getQtdResposta()
	 */
	@Override
	public int getQtdResposta() {
		return qtdResposta;
	}

	/* (non-Javadoc)
	 * @see asktechforum.dominio.ResultConsultarPergunta#setQtdResposta(int)
	 */
	@Override
	public void setQtdResposta(int qtdResposta) {
		this.qtdResposta = qtdResposta;
	}
	
}
