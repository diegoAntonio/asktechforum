package asktechforum.factory;

import asktechforum.interfaces.RepositorioPergunta;
import asktechforum.interfaces.RepositorioResposta;
import asktechforum.interfaces.RepositorioUsuario;
import asktechforum.interfaces.RepositorioVoto;
import asktechforum.repositorio.jdbc.RepositorioPerguntasJDBC;
import asktechforum.repositorio.jdbc.RepositorioRespostaJDBC;
import asktechforum.repositorio.jpa.RepositorioUsuarioJPA;

/**
 * Classe que implementa o Abstract Factory
 * criando a familia JPA.
 * @author Diego
 *
 */
public class FabricaJPA implements FabricaDAO{

	@Override
	public RepositorioVoto criarDaoVoto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepositorioPergunta criarDaoPergunta() {
		return new RepositorioPerguntasJDBC();
	}

	@Override
	public RepositorioResposta criarDaoResposta() {
		return new RepositorioRespostaJDBC();
	}

	@Override
	public RepositorioUsuario criarDaoUsuario() {
		return new RepositorioUsuarioJPA();
	}

}
