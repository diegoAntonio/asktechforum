package asktechforum.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Diego
 *	Classe que implementa o padrao singleton
 *	servindo conexoes a quem precisar.
 */
public class ConnectionUtil {
	private static ConnectionUtil instancia;
	private Connection connection = null;
	
	private ConnectionUtil(){
		this.criarConexao();
	}
	
	private void criarConexao() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/asktechforum";
			String user = "root";
			String password = "12345";
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
			// checando se a conexao ainda é valida.
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