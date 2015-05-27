package asktechforum.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import asktechforum.config.PropriedadesBancoLoader;

public class ConexaoLocal  extends ConexaoAbs{
	private Connection conexao;
	@Override
	public void conectar() {
		try {
			String driver = PropriedadesBancoLoader.BANCO_DRIVER;
			String url = PropriedadesBancoLoader.BANCO_URL;
			String user = PropriedadesBancoLoader.BANCO_USER_NAME;
			String password = PropriedadesBancoLoader.BANCO_PASSWD;
			Class.forName(driver);
			this.conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		
	
	public Connection getConexao() {
		return conexao;
	}

}
