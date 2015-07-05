package asktechforum.repositorio.jpa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import asktechforum.dominio.Resposta;
import asktechforum.dominio.RespostaImpl;
import asktechforum.dominio.Usuario;
import asktechforum.interfaces.RepositorioResposta;

public class RepositorioRespostaJPA extends RepositorioGenericJPA<RespostaImpl, Integer>implements RepositorioResposta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String adicionarResposta(Resposta resposta) throws SQLException {
		String resultado = "cadastroSucesso";
		
		super.persist((RespostaImpl)resposta);
		
		return resultado;
	}

	@Override
	public void deletarResposta(int id) throws SQLException {		
		super.removeById(id);
	}

	@Override
	public Resposta consultarRespostaPorIdResposta(int id) throws SQLException {
		Resposta r = (Resposta)super.getById(id);
		
		return r;
	}

	@Override
	public ArrayList<Resposta> consultarTodasRespostas() throws SQLException {
		ArrayList<Resposta> respostas = new ArrayList<Resposta>(super.findAll());
		
		return respostas;
	}

	
	//TODO: Fazer metodos estress
	@Override
	public ArrayList<Resposta> consultarRespostaPorIdUsuario(int id) throws SQLException {
		ArrayList<Resposta> respostas = null;
		Map<String,Object> parameters = null;
		
		parameters = new HashMap<String, Object>();
		
		parameters.put("id", id);
	
		respostas = new ArrayList<Resposta>(super.findManyResult(RespostaImpl.JPQL_autor, parameters));
		
		return respostas;
	}
	
	@Override
	public ArrayList<Resposta> consultarRespostaPorPergunta(int id) throws SQLException {
		ArrayList<Resposta> respostas = null;
		Map<String,Object> parameters = new HashMap<String,Object>();
		
		parameters.put("idPergunta", id);
		
		respostas = new ArrayList<Resposta>(super.findManyResult(RespostaImpl.JPQL_por_pergunta, parameters));
		
		return respostas;
	}

	@Override
	public String alterarResposta(Resposta resposta) throws SQLException {
		String resultado = "alteracaoSucesso";
		
		super.merge((RespostaImpl)resposta);
		
		return resultado;
	}

	@Override
	public void removerVotoResposta(int id) throws SQLException {
		Resposta r = super.getById(id);
		
		r.setVotos(r.getVotos() - 1);
		
		this.alterarResposta(r);
	}

	@Override
	public void adcionarVotoResposta(int id) throws SQLException {
		Resposta r = super.getById(id);
		
		r.setVotos(r.getVotos() + 1);
		
		this.alterarResposta(r);
	}

	@Override
	public Usuario consultarAutorPergunta(int id) throws SQLException {
		Usuario u = null;
		EntityManager em = super.getEntityManager();
		Query query = null;
		
		//gambiarra
		query = em.createQuery("Select u from Usuario u , PerguntaImpl p where u.idUsuario = p.usuario.idUsuario"
				+ "	and p.usuario.idUsuario = :id");
		
		query.setParameter("id", id);
		
		u = (Usuario)query.getSingleResult();
		
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Usuario> consultarContribuintesPergunta(int id) throws SQLException {
		ArrayList<Usuario> listaContribuintes = null;
		
		EntityManager em = super.getEntityManager();
		Query query = null;
		
		//gambiarra
		query = em.createQuery("Select u from Usuario u, RespostaImpl r, PerguntaImpl p "
				+ " where u.idUsuario = p.usuario.idUsuario and r.usuario.idUsuario = p.usuario.idUsuario "
				+ " and p.idPergunta = :id");
		
		query.setParameter("id", id);
		
		listaContribuintes = new ArrayList<Usuario>(query.getResultList());
		
		return listaContribuintes;
	}

}
