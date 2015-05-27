package asktechforum.util;

import java.sql.Connection;
import java.sql.SQLException;

import asktechforum.conection.ConexaoAbs;
import asktechforum.conection.ConexaoFactory;

/**
 * Classe que implementa o padrao singleton
 *	servindo conexoes a quem precisar.
 * @author Diego
 *	
 */
public class ConnectionUtil {
	private static ConnectionUtil instancia;
	private ConexaoAbs connection = null;
	private ConexaoFactory fabricaConexao;
	
	private ConnectionUtil(){
		fabricaConexao = new ConexaoFactory();
		this.iniciarConexao();
	}
	
	public void iniciarConexao(){
		this.connection = fabricaConexao.criarConexao(1);		
	}
	
	public Connection getConnection() {
		if (this.connection == null) {
			this.iniciarConexao();
		}

		try {
			// checando se a conexao ainda eh valida.
			if (!this.connection.getConexao().isValid(500)) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			this.iniciarConexao();
		}

		return this.connection.getConexao();
	}
	
	public static ConnectionUtil getInstancia() {
		if (ConnectionUtil.instancia == null) {
			instancia = new ConnectionUtil();
		}
		return instancia;
	}
	
}