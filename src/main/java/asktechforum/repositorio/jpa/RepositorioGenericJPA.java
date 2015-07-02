package asktechforum.repositorio.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import asktechforum.util.ConnectionUtil;

public class RepositorioGenericJPA<T, PK> implements Serializable{

	private static final long serialVersionUID = -998876415266055760L;
	private ConnectionUtil conexaoUtil;

	public RepositorioGenericJPA() {
		this.conexaoUtil = ConnectionUtil.getInstancia();
	}

	private void begin() {
		this.conexaoUtil.getEntityManager().getTransaction().begin();
	}

	private void commit() {
		this.conexaoUtil.getEntityManager().getTransaction().commit();
	}

	private void rollback() {
		this.conexaoUtil.getEntityManager().getTransaction().rollback();
	}
	
	private void close() {
		this.conexaoUtil.getEntityManager().close();
	}
	
	private EntityManager getEntityManager(){
		return this.conexaoUtil.getEntityManager();
	}

	protected void persist(T entity) throws SQLException {
		try {
			this.begin();
			this.getEntityManager().persist(entity);
			this.commit();
		} catch (Exception e) {
			this.rollback();
			throw new SQLException(e);
		} finally {
			this.close();
		}
	}

	protected void merge(T entity) throws SQLException {
		try {
			this.begin();
			this.getEntityManager().merge(entity);
			this.commit();
		} catch (Exception e) {
			this.rollback();
			throw new SQLException(e);
		} finally {
			this.close();
		}
	}

	protected void remove(T entity) throws SQLException {
		try {
			this.begin();
			this.getEntityManager().remove(entity);
			this.commit();
		} catch (Exception e) {
			this.rollback();
			throw new SQLException(e);
		} finally {
			this.close();
		}
	}

	protected void removeById(PK id) throws SQLException {
		try {
			this.begin();
			this.getEntityManager().remove(this.getById(id));
			this.commit();
		} catch (Exception e) {
			this.rollback();
			throw new SQLException(e);
		} finally {
			this.close();
		}
	}

	protected T getById(PK id) {
		T obj = null;
		obj = this.getEntityManager().find(getTypeClass(), id);
		this.close();
		return obj;
	}

	@SuppressWarnings("unchecked")
	protected Collection<T> findAll() throws SQLException {
		Collection<T> obj = null;
		String[] tolken = getTypeClass().toString().split("\\.");
		String classe = tolken[tolken.length-1];
		System.out.println(classe);
		obj = this.getEntityManager().createQuery("FROM " + classe)
				.getResultList();
		this.close();
		return obj;
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getTypeClass() {
		ParameterizedType pt = ((ParameterizedType) getClass()
				.getGenericSuperclass());
		return (Class<T>) pt.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters)
			throws SQLException {
		T result = null;
		try {
			Query query = this.getEntityManager().createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				this.populateQueryParameters(query, parameters);
			}
			result = (T) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Nenhum resultado para query: " + namedQuery);
			throw new SQLException(e);
		} catch (Exception e) {
			System.out.println("Erro ao execultar a query: " + e.getMessage());
			throw new SQLException(e);
		} finally {
			this.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	protected Collection<T> findManyResult(String namedQuery,
			Map<String, Object> parameters) throws SQLException {
		Collection<T> result = null;
		try {
			Query query = this.getEntityManager().createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				this.populateQueryParameters(query, parameters);
			}
			result = query.getResultList();
		} catch (NoResultException e) {
			System.out.println("Nenhum resultado para query: " + namedQuery);
			throw new SQLException(e);
		} catch (Exception e) {
			System.out.println("Erro ao execultar a query: " + e.getMessage());
			throw new SQLException(e);
		} finally {
			this.close();
		}
		return result;
	}

	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
