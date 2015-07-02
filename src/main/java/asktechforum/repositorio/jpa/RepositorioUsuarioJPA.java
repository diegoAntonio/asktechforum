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
		super.merge(usuario);
	}

	@Override
	public void alterarUsuarioAdmin(Usuario usuario) throws SQLException {
		super.merge(usuario);
	}

	@Override
	public void adicionarUsuario(Usuario usuario) throws SQLException {
		super.persist(usuario);
	}

	@Override
	public void deletarUsuario(String email) throws SQLException {
		Usuario u = this.consultarUsuarioPorEmail(email);
		super.remove(u);
	}

	@Override
	public void deletarUsuarioPorId(int idUsuario) throws SQLException {
		super.removeById(idUsuario);
	}

	@Override
	public Usuario consultarUsuarioPorEmail_Senha(String email, String senha)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("senha", senha);
		return super.findOneResult(Usuario.JPQL_email_senha, map);
	}

	@Override
	public Usuario consultarUsuarioPorId(int idUsuario) throws SQLException {
		return super.getById(idUsuario);
	}

	@Override
	public Usuario consultarUsuarioPorEmail(String email) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		return super.findOneResult(Usuario.JPQL_email, map);
	}

	@Override
	public List<Usuario> consultarUsuarioPorNome(String nome)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nome", nome);
		return new ArrayList<Usuario>(super.findManyResult(Usuario.JPQL_nome, map));
	}

	@Override
	public List<Usuario> consultarTodosUsuarios() throws SQLException {
		return new ArrayList<Usuario>(super.findAll());
	}
}
