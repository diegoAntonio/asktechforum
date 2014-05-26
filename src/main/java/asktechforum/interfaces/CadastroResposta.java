package asktechforum.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Resposta;

public interface CadastroResposta {
	
	public void adcionarResposta(Resposta resposta) throws SQLException;
	public void deletarResposta(int id) throws SQLException;
	public Resposta consultarRespostaPorIdResposta(int id) throws SQLException;
	public ArrayList<Resposta> consultarRespostaPorIdUsuario(int id)
			throws SQLException ;
	public ArrayList<Resposta> consultarTodasResposta() throws SQLException;
	public ArrayList<Resposta> consultarRespostaPorPergunta(int id)
			throws SQLException;
	
}
