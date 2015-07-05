package asktechforum.repositorio.jpa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.PerguntaImpl;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.interfaces.RepositorioPergunta;

public class RepositorioPerguntasJPA extends RepositorioGenericJPA<PerguntaImpl,Integer>implements RepositorioPergunta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String adcionarPergunta(Pergunta pergunta) throws SQLException {
		String retorno = "cadastroSucesso";
		
		super.persist((PerguntaImpl) pergunta);
		
		return retorno;
	}

	@Override
	public void deletarPergunta(int id) throws SQLException {
		Pergunta p = this.consultarPerguntaPorIdPergunta(id);

		super.remove((PerguntaImpl) p);
	}

	@Override
	public Pergunta consultarPerguntaPorIdPergunta(int id) throws SQLException {
		Pergunta p = super.getById(id);
		
		return p;
	}

	@Override
	public ArrayList<Pergunta> consultarPerguntaIdUsuario(int id) throws SQLException {
		ArrayList<Pergunta> resultados = null;
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("id", id);
		
		resultados = new ArrayList<Pergunta>(super.findManyResult(PerguntaImpl.JPQL_autor, parameters));
		
		return resultados; 
	}

	@Override
	public ArrayList<Pergunta> consultarTodasPerguntas() throws SQLException {
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>(super.findAll());
		
		return perguntas;
	}

	@Override
	public String alterarPergunta(Pergunta pergunta) throws SQLException {
		String resultado = "alteracaoSucesso";
		
		super.merge((PerguntaImpl) pergunta);
		
		return resultado;
	}
	
	//TODO: Fazer metodos stress

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> consultaTodasAsTags() throws SQLException {
		EntityManager em = super.getEntityManager();
		ArrayList<String> resultados = null;
		Query query = null;
				
		query = em.createNamedQuery(PerguntaImpl.JPQL_tags);
		
		resultados = new ArrayList<String>(query.getResultList());
		
		return resultados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTag(String tag) throws SQLException {
		ArrayList<ResultConsultarPergunta> perguntas = null;
		EntityManager em = super.getEntityManager();
		Query query = em.createNamedQuery(PerguntaImpl.JPQL_por_tag);
		
		query.setParameter("tag", tag);
		
		perguntas = new ArrayList<ResultConsultarPergunta>(query.getResultList());
		
		return perguntas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTodasTags() throws SQLException {
		ArrayList<ResultConsultarPergunta> perguntas = null;
		EntityManager em = super.getEntityManager();
		Query query = em.createNamedQuery(PerguntaImpl.JPQL_agrupadas);
		
		perguntas = new ArrayList<ResultConsultarPergunta>(query.getResultList());
		
		return perguntas;
	}

}
