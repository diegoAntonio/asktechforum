package asktechforum.factory;

import asktechforum.interfaces.RepositorioPergunta;
import asktechforum.interfaces.RepositorioResposta;
import asktechforum.interfaces.RepositorioUsuario;
import asktechforum.interfaces.RepositorioVoto;
import asktechforum.repositorio.jpa.RepositorioPerguntasJPA;
import asktechforum.repositorio.jpa.RepositorioRespostaJPA;
import asktechforum.repositorio.jpa.RepositorioUsuarioJPA;
import asktechforum.repositorio.jpa.RepositorioVotoJPA;

/**
 * Classe que implementa o Abstract Factory
 * criando a familia JPA.
 * @author Diego
 *
 */
public class FabricaJPA implements FabricaDAO{

	@Override
	public RepositorioVoto criarDaoVoto() {
		return new RepositorioVotoJPA();
	}

	@Override
	public RepositorioPergunta criarDaoPergunta() {
		return new RepositorioPerguntasJPA();
	}

	@Override
	public RepositorioResposta criarDaoResposta() {
		return new RepositorioRespostaJPA();
	}

	@Override
	public RepositorioUsuario criarDaoUsuario() {
		return new RepositorioUsuarioJPA();
	}

}
