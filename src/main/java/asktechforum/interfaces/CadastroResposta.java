package asktechforum.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Resposta;

public interface CadastroResposta {
	
	public String adicionarResposta(Resposta resposta) throws SQLException;
	public void deletarResposta(int id) throws SQLException;
	public Resposta consultarRespostaPorIdResposta(int id) throws SQLException;
	public ArrayList<Resposta> consultarRespostaPorIdUsuario(int id)
			throws SQLException ;
	public ArrayList<Resposta> consultarTodasRespostas() throws SQLException;
	public ArrayList<Resposta> consultarRespostaPorPergunta(int id)
			throws SQLException;
	public String alterarResposta(Resposta resposta) throws SQLException;
	
}
