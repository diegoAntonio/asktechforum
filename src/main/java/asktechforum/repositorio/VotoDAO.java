package asktechforum.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import asktechforum.util.ConnectionUtil;

public class VotoDAO {
	private Connection connection = null;

	public VotoDAO() {
	}
	
	public void adicionarVotoUsuario(int idUsuario, int idResposta) throws SQLException {
		String sql = "insert into voto(idUsuario, idResposta)values( ?, ? )";
		PreparedStatement preparedStatement = null;
		
		try {
			this.connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setInt(2, idResposta);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			this.connection.close();
		}
	}
	
	public void deletarUsuarioVoto(int idUsuario, int idResposta) throws SQLException {
		PreparedStatement preparedStatement = null;
        try {
    		this.connection = ConnectionUtil.getConnection();
            preparedStatement = this.connection
                    .prepareStatement("delete from voto where idUsuario=? and idResposta=?");

			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setInt(2, idResposta);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            this.connection.close();
        }
	}
	
	public Boolean consultarUsuarioVoto(int idUsuario, int idResposta) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Boolean flag = true;
		try {
			this.connection = ConnectionUtil.getConnection();
			preparedStatement = this.connection
					.prepareStatement("select * from voto where idUsuario=? and idResposta=?");

			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setInt(2, idResposta);
			rs = preparedStatement.executeQuery();
			
			if(rs.next() == false) {
				flag = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            this.connection.close();
        }
		
		return flag;
	}
	
}