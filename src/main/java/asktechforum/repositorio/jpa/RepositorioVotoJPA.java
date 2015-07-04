package asktechforum.repositorio.jpa;

import java.sql.SQLException;

import asktechforum.dominio.Voto;
import asktechforum.interfaces.RepositorioVoto;

public class RepositorioVotoJPA extends RepositorioGenericJPA<Voto, Integer>implements RepositorioVoto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void adicionarVotoUsuario(int idUsuario, int idResposta) throws SQLException {
		
	}

	@Override
	public void deletarUsuarioVoto(int idUsuario, int idResposta) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean consultarUsuarioVoto(int idUsuario, int idResposta) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
