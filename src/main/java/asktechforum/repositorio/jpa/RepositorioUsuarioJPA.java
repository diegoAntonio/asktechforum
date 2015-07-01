package asktechforum.repositorio.jpa;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import asktechforum.dominio.Usuario;
import asktechforum.interfaces.RepositorioUsuario;
import asktechforum.util.ConnectionUtil;
import asktechforum.util.UsuarioUtil;

public class RepositorioUsuarioJPA implements RepositorioUsuario {
	UsuarioUtil usuarioUtil;
	private ConnectionUtil conexaoUtil;

	public RepositorioUsuarioJPA() {
		this.usuarioUtil = new UsuarioUtil();
		this.conexaoUtil = ConnectionUtil.getInstancia();
	}

	@Override
	public void alterarUsuario(Usuario usuario) throws SQLException {
		try {
			this.conexaoUtil.getEntityManager().getTransaction().begin();
			this.conexaoUtil.getEntityManager().merge(usuario);
			this.conexaoUtil.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.conexaoUtil.getEntityManager().getTransaction().rollback();
			throw new SQLException(e);
		} finally {
			this.conexaoUtil.getEntityManager().close();
		}
	}

	@Override
	public void alterarUsuarioAdmin(Usuario usuario) throws SQLException {
		try {
			this.conexaoUtil.getEntityManager().getTransaction().begin();
			this.conexaoUtil.getEntityManager().merge(usuario);
			this.conexaoUtil.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.conexaoUtil.getEntityManager().getTransaction().rollback();
			throw new SQLException(e);
		} finally {
			this.conexaoUtil.getEntityManager().close();
		}

	}

	@Override
	public void adicionarUsuario(Usuario usuario) throws SQLException {
		try {
			this.conexaoUtil.getEntityManager().getTransaction().begin();
			this.conexaoUtil.getEntityManager().persist(usuario);
			this.conexaoUtil.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.conexaoUtil.getEntityManager().getTransaction().rollback();
			throw new SQLException(e);
		} finally {
			this.conexaoUtil.getEntityManager().close();
		}
	}

	@Override
	public void deletarUsuario(String email) throws SQLException {
		Usuario u = this.consultarUsuarioPorEmail(email);
		try {
			this.conexaoUtil.getEntityManager().getTransaction().begin();
			this.conexaoUtil.getEntityManager().remove(u);
			this.conexaoUtil.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.conexaoUtil.getEntityManager().getTransaction().rollback();
			throw new SQLException(e);
		} finally {
			this.conexaoUtil.getEntityManager().close();
		}

	}

	@Override
	public void deletarUsuarioPorId(int idUsuario) throws SQLException {
		Usuario u = this.consultarUsuarioPorId(idUsuario);
		try {
			this.conexaoUtil.getEntityManager().getTransaction().begin();
			this.conexaoUtil.getEntityManager().remove(u);
			this.conexaoUtil.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.conexaoUtil.getEntityManager().getTransaction().rollback();
			throw new SQLException(e);
		} finally {
			this.conexaoUtil.getEntityManager().close();
		}
	}

	@Override
	public Usuario consultarUsuarioPorEmail_Senha(String email, String senha)
			throws SQLException {
		Usuario result = null;
		try {
			Query query = this.conexaoUtil.getEntityManager().createQuery(
					Usuario.JPQL_email_senha);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", email);
			map.put("senha", email);
			this.populateQueryParameters(query, map);

			result = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Nenhum resultado para query: "
					+ Usuario.JPQL_email_senha);
			throw new SQLException(e);
		} catch (Exception e) {
			System.out.println("Erro ao execultar a query: " + e.getMessage());
			throw new SQLException(e);
		} finally {
			this.conexaoUtil.getEntityManager().close();
		}
		return result;
	}

	@Override
	public Usuario consultarUsuarioPorId(int idUsuario) throws SQLException {
		Usuario u = (Usuario) this.conexaoUtil.getEntityManager().find(
				Usuario.class, idUsuario);
		this.conexaoUtil.getEntityManager().close();
		return u;
	}

	@Override
	public Usuario consultarUsuarioPorEmail(String email) throws SQLException {
		Usuario result = null;
		try {
			Query query = this.conexaoUtil.getEntityManager().createQuery(
					Usuario.JPQL_email);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", email);
			this.populateQueryParameters(query, map);

			result = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Nenhum resultado para query: "
					+ Usuario.JPQL_email);
			throw new SQLException(e);
		} catch (Exception e) {
			System.out.println("Erro ao execultar a query: " + e.getMessage());
			throw new SQLException(e);
		} finally {
			this.conexaoUtil.getEntityManager().close();
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarUsuarioPorNome(String nome)
			throws SQLException {
		List<Usuario> result = null;
		try {
			Query query = this.conexaoUtil.getEntityManager().createQuery(
					Usuario.JPQL_nome);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nome", nome);
			this.populateQueryParameters(query, map);

			result = (List<Usuario>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("Nenhum resultado para query: "
					+ Usuario.JPQL_nome);
			throw new SQLException(e);
		} catch (Exception e) {
			System.out.println("Erro ao execultar a query: " + e.getMessage());
			throw new SQLException(e);
		} finally {
			this.conexaoUtil.getEntityManager().close();
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarTodosUsuarios() throws SQLException {
		return (List<Usuario>) this.conexaoUtil.getEntityManager()
				.createQuery("FROM" + Usuario.class).getResultList();
	}

	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
