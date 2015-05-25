package asktechforum.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import asktechforum.config.PropriedadesBancoLoader;

/**
 * Classe que implementa o padrao singleton
 *	servindo conexoes a quem precisar.
 * @author Diego
 *	
 */
public class ConnectionUtil {
	private static ConnectionUtil instancia;
	private Connection connection = null;
	
	private ConnectionUtil(){
		this.criarConexao();
	}
	
	private void criarConexao() {
		try {
			String driver = PropriedadesBancoLoader.BANCO_DRIVER;
			String url = PropriedadesBancoLoader.BANCO_URL;
			String user = PropriedadesBancoLoader.BANCO_USER_NAME;
			String password = PropriedadesBancoLoader.BANCO_PASSWD;
			Class.forName(driver);
			this.connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public Connection getConnection() {
		if (this.connection == null) {
			this.criarConexao();
		}

		try {
			// checando se a conexao ainda eh valida.
			if (!this.connection.isValid(500)) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			this.criarConexao();
		}

		return this.connection;
	}
	
	public static ConnectionUtil getInstancia() {
		if (ConnectionUtil.instancia == null) {
			instancia = new ConnectionUtil();
		}
		return instancia;
	}
	
}