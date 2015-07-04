package asktechforum.repositorio.jpa;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Resposta;
import asktechforum.dominio.Usuario;
import asktechforum.interfaces.RepositorioResposta;

public class RepositorioRespostaJPA extends RepositorioGenericJPA<Resposta, Integer>implements RepositorioResposta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String adicionarResposta(Resposta resposta) throws SQLException {
		String resultado = "cadastroSucesso";
		
		super.persist(resposta);
		
		return resultado;
	}

	@Override
	public void deletarResposta(int id) throws SQLException {		
		super.removeById(id);
	}

	@Override
	public Resposta consultarRespostaPorIdResposta(int id) throws SQLException {
		Resposta r = (Resposta)super.getById(id);
		
		return r;
	}

	@Override
	public ArrayList<Resposta> consultarTodasRespostas() throws SQLException {
		ArrayList<Resposta> respostas = new ArrayList<Resposta>(super.findAll());
		
		return respostas;
	}

	
	//TODO: Fazer metodos estress
	@Override
	public ArrayList<Resposta> consultarRespostaPorIdUsuario(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Resposta> consultarRespostaPorPergunta(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alterarResposta(Resposta resposta) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerVotoResposta(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void adcionarVotoResposta(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario consultarAutorPergunta(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Usuario> consultarContribuintesPergunta(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
