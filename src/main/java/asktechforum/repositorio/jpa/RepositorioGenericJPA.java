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

/**
 * @author Diego
 *
 *	Classe que faz todas as operacoes default de um Repositorio JPA,
 * para um tipo especifico.
 * @param <T> - Objeto Mapeado pelo JPA que vai ser usado na classe.
 * @param <PK> - Tipo da Primary Key do Objeto Mapeado.
 */
public class RepositorioGenericJPA<T, PK> implements Serializable{

	private static final long serialVersionUID = -998876415266055760L;
	private ConnectionUtil conexaoUtil;

	public RepositorioGenericJPA() {
		this.conexaoUtil = ConnectionUtil.getInstancia();
	}

	/**
	 * Inicia uma transacao no entity manager.
	 */
	private void begin() {
		this.conexaoUtil.getEntityManager().getTransaction().begin();
	}

	/**
	 * Da o commit na transacao do entity manager.
	 */
	private void commit() {
		this.conexaoUtil.getEntityManager().getTransaction().commit();
	}

	/**
	 * Da o rollback na transacao do entity manager.
	 */
	private void rollback() {
		this.conexaoUtil.getEntityManager().getTransaction().rollback();
	}
	
	/**
	 * fecha o entity apos o uso.
	 */
	private void close() {
		this.conexaoUtil.getEntityManager().close();
	}
	
	
	/**
	 *  Retorna o entity usado por essa classe.O uso
	 *  protected eh para que as apenas as classes
	 *  do pacote consigam enxergar e realizar
	 *  consultas nao convecionais (gambiarras)
	 *  
	 * @return
	 */
	protected EntityManager getEntityManager(){
		return this.conexaoUtil.getEntityManager();
	}

	/**
	 *  Insere uma unidade do tipo <T> no banco.
	 * @param entity
	 * @throws SQLException
	 */
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

	/**
	 *  Atualiza uma unidade do tipo <T> no banco.
	 *  
	 * @param entity
	 * @throws SQLException
	 */
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

	/**
	 * Remove uma unidade do tipo <T> no banco.
	 * 
	 * @param entity
	 * @throws SQLException
	 */
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

	/**
	 *  Remove uma unidade do Tipo <T> do banco por um id
	 *  informado.
	 *  
	 * @param id
	 * @throws SQLException
	 */
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

	/**
	 *  Retorna uma unidade do Tipo <T> pelo seu id.
	 *  
	 * @param id
	 * @return
	 */
	protected T getById(PK id) {
		T obj = null;
		obj = this.getEntityManager().find(getTypeClass(), id);
		this.close();
		return obj;
	}

	/**
	 *  Retorna todas as instancias da unidade do tipo <T> 
	 *  existentes no banco.
	 *  
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 *  Retorna o tipo <T> que a classe usa nas consultas.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getTypeClass() {
		ParameterizedType pt = ((ParameterizedType) getClass()
				.getGenericSuperclass());
		return (Class<T>) pt.getActualTypeArguments()[0];
	}

	/**
	 *  Retorna um resultado com uma consulta padrao
	 *  criada pela classe <T>
	 *  
	 * @param namedQuery - query criada na classe <T> para a consulta.
	 * @param parameters - parametros usados na query.
	 * @return 
	 * @throws SQLException
	 */
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

	/**
	 * Retorna uma colecao de resultados encontrados
	 * por uma query criada na classe <T>
	 * 
	 * @param namedQuery - Query criada na classe <T>
	 * @param parameters - parametros usados na query.
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 *  Coloca todos os parametros informados na query
	 *  que vai ser consultada no banco.
	 * @param query
	 * @param parameters
	 */
	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
