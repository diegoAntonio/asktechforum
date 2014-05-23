package asktechforum.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import asktechforum.util.ConnectionUtil;
import asktechforum.util.UsuarioUtil;
import asktechforum.dominio.Usuario;

public class UsuarioDAO {
	private Connection connection;
    UsuarioUtil usuarioUtil;
	
	public UsuarioDAO(){
		this.connection = ConnectionUtil.getConnection();
		this.usuarioUtil = new UsuarioUtil();
	}
	
	public void alterarUsuario(Usuario usuario){
		try {
            PreparedStatement preparedStatement = this.connection
                    .prepareStatement("update usuario set nome=?,dt_nasc=?,admin=?,email=?,localizacao=?,senha=? where idUsuario=?");

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setDate(2, usuario.getDataNascimento());
            preparedStatement.setBoolean(3, usuario.isAdmin());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getLocalizacao());
            preparedStatement.setString(6, usuario.getSenha());
            preparedStatement.setInt(7, usuario.getIdUsuario());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
	        this.usuarioUtil.ajustarIdUsuario(this.consultarTodosUsuarios());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void alterarUsuarioAdmin(Usuario usuario){
		try {
            PreparedStatement preparedStatement = this.connection
                    .prepareStatement("update usuario set admin=? where idUsuario=?");

            preparedStatement.setBoolean(1, usuario.isAdmin());
            preparedStatement.setInt(2, usuario.getIdUsuario());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
	        this.usuarioUtil.ajustarIdUsuario(this.consultarTodosUsuarios());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void adicionarUsuario(Usuario usuario){
		try {
            PreparedStatement preparedStatement = this.connection
                    .prepareStatement("insert into usuario(nome,dt_nasc,email,localizacao,senha,admin) values ( ?, ?, ?, ?, ?, ? )");
            
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setDate(2, usuario.getDataNascimento());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getLocalizacao());
            preparedStatement.setString(5, usuario.getSenha());
            preparedStatement.setBoolean(6, usuario.isAdmin());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
	        this.usuarioUtil.ajustarIdUsuario(this.consultarTodosUsuarios());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void deletarUsuario(String email) {
        try {
            PreparedStatement preparedStatement = this.connection
                    .prepareStatement("delete from usuario where email=?");
            
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        	this.usuarioUtil.ajustarIdUsuario(this.consultarTodosUsuarios());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	public Usuario consultarUsuarioPorEmail_Senha(String email,String senha) {
		Usuario usuario = null;
		try {
            this.usuarioUtil.ajustarIdUsuario(this.consultarTodosUsuarios());
            
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from usuario where email=? and senha=?");
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,senha);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAdmin(rs.getBoolean("admin"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public Usuario consultarUsuarioPorId(int idUsuario) {
		Usuario usuario = new Usuario();
		try {
            this.usuarioUtil.ajustarIdUsuario(this.consultarTodosUsuarios());
            
			PreparedStatement preparedStatement = this.connection
					.prepareStatement("select * from usuario where idUsuario=?");
			
			preparedStatement.setInt(1, idUsuario);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
			}
            preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
		
	public Usuario consultarUsuarioPorEmail(String email) {
		Usuario usuario = new Usuario();
		try {
            this.usuarioUtil.ajustarIdUsuario(this.consultarTodosUsuarios());
            
			PreparedStatement preparedStatement = this.connection
					.prepareStatement("select * from usuario where email=?");
			
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
			}
            preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}	
	
	public List<Usuario> consultarUsuarioPorNome(String nome) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
            this.usuarioUtil.ajustarIdUsuario(this.consultarTodosUsuarios());
            
			PreparedStatement preparedStatement = this.connection
					.prepareStatement("select * from usuario where nome=?");
			
			preparedStatement.setString(1, nome);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuarios.add(usuario);
			}
            preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}	
	
	public void atualizarIdUsuario(List<Usuario> usuarios, int i, int index) {
        	int idUsuario = usuarios.get(i).getIdUsuario();
        	PreparedStatement preparedStatement;
			try {
				preparedStatement = this.connection.prepareStatement("update usuario set idUsuario=? where idUsuario=?;");
	            preparedStatement.setInt(1, index);
	            preparedStatement.setInt(2, idUsuario);
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public List<Usuario> consultarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from usuario;");
            
            while(rs.next()) {
            	Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
            	usuarios.add(usuario);
            }
            
            usuarios = this.usuarioUtil.ajustarIdUsuario(usuarios);
            
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}