package asktechforum.repositorio.jpa;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.interfaces.RepositorioPergunta;

public class RepositorioPerguntasJPA extends RepositorioGenericJPA<Pergunta,Integer>implements RepositorioPergunta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String adcionarPergunta(Pergunta pergunta) throws SQLException {
		String retorno = "cadastroSucesso";
		
		super.persist(pergunta);
		
		return retorno;
	}

	@Override
	public void deletarPergunta(int id) throws SQLException {
		Pergunta p = this.consultarPerguntaPorIdPergunta(id);

		super.remove(p);
	}

	@Override
	public Pergunta consultarPerguntaPorIdPergunta(int id) throws SQLException {
		Pergunta p = (Pergunta)super.getById(id);
		
		return p;
	}

	@Override
	public ArrayList<Pergunta> consultarPerguntaIdUsuario(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Pergunta> consultarTodasPerguntas() throws SQLException {
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>(super.findAll());
		
		return perguntas;
	}

	@Override
	public String alterarPergunta(Pergunta pergunta) throws SQLException {
		String resultado = "alteracaoSucesso";
		
		super.merge(pergunta);
		
		return resultado;
	}
	
	//TODO: Fazer metodos stress

	@Override
	public ArrayList<String> consultaTodasAsTags() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTag(String tag) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTodasTags() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
