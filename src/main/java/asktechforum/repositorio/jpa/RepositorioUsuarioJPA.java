package asktechforum.repositorio.jpa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asktechforum.dominio.Usuario;
import asktechforum.interfaces.RepositorioUsuario;

/**
 * Representa um repositorio de Usuarios do JPA.
 * @author Diego
 *
 */
public class RepositorioUsuarioJPA extends RepositorioGenericJPA<Usuario, Integer> implements RepositorioUsuario  {

	private static final long serialVersionUID = -7310817251630018426L;

	public RepositorioUsuarioJPA() {
	}

	@Override
	public void alterarUsuario(Usuario usuario) throws SQLException {
		//chama o metodo de alterar usuario de RepositorioGenericJPA;
		super.merge(usuario);
	}

	@Override
	public void alterarUsuarioAdmin(Usuario usuario) throws SQLException {
		//chama o metodo alterar usuario de RepositorioGenericJPA
		super.merge(usuario);
	}

	@Override
	public void adicionarUsuario(Usuario usuario) throws SQLException {
		//chama o metodo inserir de RepositorioGenericJPA
		super.persist(usuario);
	}

	@Override
	public void deletarUsuario(String email) throws SQLException {
		//procura o usuario no banco.
		Usuario u = this.consultarUsuarioPorEmail(email);
		//remove o usuario.
		super.remove(u);
	}

	@Override
	public void deletarUsuarioPorId(int idUsuario) throws SQLException {
		//remove o usuario chamando o metodo de RepositorioGenericJPA
		super.removeById(idUsuario);
	}

	@Override
	public Usuario consultarUsuarioPorEmail_Senha(String email, String senha)
			throws SQLException {
		//utiliza uma consulta criada em Usuario
		//para retornar os usuarios por email e senha.
		
		//os parametros em string devem bater com os parametros
		//na consulta criada.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("senha", senha);
		
		//chama o metodo de RepositorioGenericJPA, passando o nome da query
		//e os parametros.
		return super.findOneResult(Usuario.JPQL_email_senha, map);
	}

	@Override
	public Usuario consultarUsuarioPorId(int idUsuario) throws SQLException {
		//chama o metodo de RepositorioGenericJPA para localizar por um id.
		return super.getById(idUsuario);
	}

	@Override
	public Usuario consultarUsuarioPorEmail(String email) throws SQLException {
		//utiliza uma query criada em Usuario para consultar pelo email.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		return super.findOneResult(Usuario.JPQL_email, map);
	}

	@Override
	public List<Usuario> consultarUsuarioPorNome(String nome)
			throws SQLException {
		
		//chama a consulta criada em Usuario para consultar pelo nome.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nome", nome);
		//chama o metodo de RepositorioGenericJPA para usar a consulta 
		return new ArrayList<Usuario>(super.findManyResult(Usuario.JPQL_nome, map));
	}

	@Override
	public List<Usuario> consultarTodosUsuarios() throws SQLException {
		//retorna o metodo de listar todos de RepositorioGenericJPA
		return new ArrayList<Usuario>(super.findAll());
	}
}
