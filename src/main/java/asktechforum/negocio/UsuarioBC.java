package asktechforum.negocio;

import java.util.ArrayList;
import java.util.List;
//import java.sql.Connection;
//import java.sql.SQLException;

import asktechforum.dominio.Usuario;
import asktechforum.repositorio.UsuarioDAO;
//import asktechforum.util.ConnectionUtil;
//import asktechforum.util.UsuarioUtil;

public class UsuarioBC {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioBC() {
        super();
        this.usuarioDAO = new UsuarioDAO();
	}
	
	public void alterarUsuario(Usuario usuario){
		this.usuarioDAO.alterarUsuario(usuario);
	}

	public void alterarUsuarioAdmin(Usuario usuario){
		this.usuarioDAO.alterarUsuarioAdmin(usuario);
	}

	public void adicionarUsuario(Usuario usuario){
		this.usuarioDAO.adicionarUsuario(usuario);
	}

	public void deletarUsuario(String email) {
		this.usuarioDAO.deletarUsuario(email);
	}

	public Usuario consultarUsuarioPorEmail_Senha(String email,String senha) {
		Usuario usuario = null;
		if(this.usuarioDAO.consultarUsuarioPorEmail_Senha(email, senha).getIdUsuario() != 0) {
			usuario = this.usuarioDAO.consultarUsuarioPorEmail_Senha(email, senha);
		}
		return usuario;
	}

	public Usuario consultarUsuarioPorId(int idUsuario) {
		Usuario usuario = new Usuario();
		usuario = this.usuarioDAO.consultarUsuarioPorId(idUsuario);
		return usuario;
	}

	public Usuario consultarUsuarioPorEmail(String email) {
		Usuario usuario = new Usuario();
		usuario = this.usuarioDAO.consultarUsuarioPorEmail(email);
		return usuario;
	}

	public List<Usuario> consultarUsuarioPorNome(String nome) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = this.usuarioDAO.consultarUsuarioPorNome(nome);
		return usuarios;
	}
	
	public void atualizarIdUsuario(List<Usuario> usuarios, int i, int index) {
		this.usuarioDAO.atualizarIdUsuario(usuarios, i, index);
	}
	
	public List<Usuario> consultarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = this.usuarioDAO.consultarTodosUsuarios();
        return usuarios;
	}
	
	public int consultarQuantidadeAdmin(Usuario usuario) {
		int quantAdmin = 0;
		Boolean flag = false;
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		listaUsuarios = this.usuarioDAO.consultarTodosUsuarios();
		
		if(listaUsuarios != null) {
			for(Usuario u : listaUsuarios) {
				if(u.isAdmin()) {
					quantAdmin++;
					if(u.getEmail().equals(usuario.getEmail())){
						flag = true;
					}
				}
			}
			if(quantAdmin == 1 && flag == false) {
				quantAdmin++;
			} 
		}
		
		return quantAdmin;
	}
	
	public boolean verificarEmail(String email, Usuario usuario) {
		boolean flag = false;
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		listaUsuarios = this.usuarioDAO.consultarTodosUsuarios();
		
		if(listaUsuarios != null) {
			for(Usuario u : listaUsuarios) {
				if(u.getEmail().equals(email)) {
					if(u.getEmail().equals(usuario.getEmail())) {
						flag = false;
					}else {
						flag = true;
					}
				}
			}
		}
		
		return flag;
	}
		
}
