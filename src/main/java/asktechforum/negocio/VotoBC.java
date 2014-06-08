package asktechforum.negocio;

import java.sql.SQLException;

import asktechforum.repositorio.VotoDAO;

public class VotoBC {
	private VotoDAO votoDAO;
	
	public VotoBC() {
		this.votoDAO = new VotoDAO();
	}
	
	public void adicionarVotoUsuario(int idUsuario, int idResposta) {
		try {
			this.votoDAO.adicionarVotoUsuario(idUsuario, idResposta);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarUsuarioVoto(int idUsuario, int idResposta) {
		try {
			this.votoDAO.deletarUsuarioVoto(idUsuario, idResposta);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean consultarUsuarioVoto(int idUsuario, int idResposta) {
		Boolean flag = true;
		try {
			flag = this.votoDAO.consultarUsuarioVoto(idUsuario, idResposta);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}