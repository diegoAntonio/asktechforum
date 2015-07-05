package asktechforum.repositorio.jpa;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import asktechforum.dominio.RespostaImpl;
import asktechforum.dominio.Usuario;
import asktechforum.dominio.Voto;
import asktechforum.dominio.VotoImpl;
import asktechforum.interfaces.RepositorioVoto;

public class RepositorioVotoJPA extends RepositorioGenericJPA<VotoImpl, Integer>implements RepositorioVoto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void adicionarVotoUsuario(int idUsuario, int idResposta) throws SQLException {
		Usuario u = null;
		RespostaImpl resposta = null;
		Voto v = new VotoImpl();
		EntityManager em = super.getEntityManager();
		Query query = null;
		Query queryResposta = null;

		try{
			em.getTransaction().begin();
			//consulta o usuario.
			query = em.createQuery(" Select u from Usuario u where u.idUsuario = idUsuario");
			query.setParameter("idUsuario", idUsuario);
			
			//consulta a resposta
			queryResposta = em.createQuery("Select r from RespostaImpl, Usuario u where u.idUsuario = "
					+ " r.idUsuario and r.resposta = :idResposta and u.idUsuario = :idUsuario");
			queryResposta.setParameter("idResposta", idResposta);
			queryResposta.setParameter("idUsuario", idUsuario);
			
			u = (Usuario) query.getSingleResult();
			resposta = (RespostaImpl)queryResposta.getSingleResult();
			
			resposta.setUsuario(u);
			
			v.setResposta(resposta);
			v.setUsuario(u);
			
			//insere o voto
			em.persist(v);
			//commit
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			throw new SQLException(e.getMessage());
		}finally {
			em.close();
		}	
	}

	@Override
	public void deletarUsuarioVoto(int idUsuario, int idResposta) throws SQLException {
		Usuario u = null;
		RespostaImpl resposta = null;
		Voto v = new VotoImpl();
		EntityManager em = super.getEntityManager();
		Query query = null;
		Query queryResposta = null;

		try{
			em.getTransaction().begin();
			//consulta o usuario.
			query = em.createQuery(" Select u from Usuario u where u.idUsuario = idUsuario");
			query.setParameter("idUsuario", idUsuario);
			
			//consulta a resposta
			queryResposta = em.createQuery("Select r from RespostaImpl, Usuario u where u.idUsuario = "
					+ " r.idUsuario and r.resposta = :idResposta and u.idUsuario = :idUsuario");
			queryResposta.setParameter("idResposta", idResposta);
			queryResposta.setParameter("idUsuario", idUsuario);
			
			u = (Usuario) query.getSingleResult();
			resposta = (RespostaImpl)queryResposta.getSingleResult();
			
			resposta.setUsuario(u);
			
			v.setResposta(resposta);
			v.setUsuario(u);
			
			//insere o voto
			em.remove(v);
			//commit
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			throw new SQLException(e.getMessage());
		}finally {
			em.close();
		}

	}

	@Override
	public Boolean consultarUsuarioVoto(int idUsuario, int idResposta) throws SQLException {
		boolean votou = false;
		EntityManager em = super.getEntityManager();
		Query query = null;
		
		try{
		
		query = em.createQuery("Select v from VotoImpl v where v.usuario.idUsuario = :idUsuario "
				+ "v.resposta.idResposta = :idResposta");
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("idResposta", idResposta);
		
		if(query.getSingleResult() != null){
			votou = true;
		}
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		
		return votou;
	}
}
