package asktechforum.conection;

import java.sql.Connection;

import javax.persistence.EntityManager;

public abstract class ConexaoAbs {
	
	/**
	 * Metodo sem retorno responsavel
	 * por executar a conexao com o banco.
	 */
	public abstract void conectar();

	/**
	 *  Metodo que retorna uma conexao valida
	 *  com o banco.
	 *  
	 * @return Conexao com o banco.
	 * @throws IllegalArgumentException - Se o metodo nao foi implementado retorna uma excecao;
	 */
	public Connection getConexao(){
		throw new IllegalArgumentException("Conexao não suportada.");
	};
	
	/**
	 *  Metodo que retorna uma conexao valida
	 *  com o banco em JPA.
	 *  
	 * @return EntityManager com o banco.
	 * @throws IllegalArgumentException - Se o metodo nao foi implementado retorna uma excecao;
	 */
	public EntityManager getEntityManager(){
		throw new IllegalArgumentException("Conexao não suportada.");
	};
	
}
