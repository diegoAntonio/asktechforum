package asktechforum.negocio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Connection;
//import java.sql.SQLException;


import asktechforum.dominio.Usuario;
import asktechforum.repositorio.UsuarioDAO;
//import asktechforum.util.ConnectionUtil;
import asktechforum.util.UsuarioUtil;

public class UsuarioBC {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioBC() {
        super();
        this.usuarioDAO = new UsuarioDAO();
	}
	
	public boolean alterarUsuario(Usuario usuario){
		boolean flag = validarUsuario(usuario);
		
		if(flag) {
			this.usuarioDAO.alterarUsuario(usuario);
		}
		
		return flag;
	}

	public boolean alterarUsuarioAdmin(Usuario usuario){
		boolean flag = validarUsuario(usuario);
		
		if(flag) {
			this.usuarioDAO.alterarUsuarioAdmin(usuario);
		}
		
		return flag;
	}

	public boolean adicionarUsuario(Usuario usuario){
		boolean flag = validarUsuario(usuario);
		
		if(flag) {
			this.usuarioDAO.adicionarUsuario(usuario);
		}
		
		return flag;
	}

	public void deletarUsuario(String email) {
		this.usuarioDAO.deletarUsuario(email);
	}

	public Usuario consultarUsuarioPorEmail_Senha(String email, String senha) {
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
	
	public List<Usuario> consultarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = this.usuarioDAO.consultarTodosUsuarios();
        return usuarios;
	}
	
	public boolean validarUsuario(Usuario usuario) {
		boolean flag = true;
		Date data = null;
		
		if(usuario != null) {
			
			if(usuario.getNome() != null) {
				if(usuario.getNome().trim() == "") {
					flag = false;
				}
			}else {
				flag = false;
			}
			
			if(usuario.getEmail() != null) {
				if(usuario.getEmail().trim() == "") {
					flag = false;
				}else if(!usuario.getEmail()
						.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)$")) {
					flag = false;
				}
			}else {
				flag = false;
			}
			
			if(usuario.getSenha() != null) {
				if(usuario.getSenha().trim() == "") {
					flag = false;
				}
			}else {
				flag = false;
			}
			
			if(usuario.getDataString() != null) {
				if(usuario.getDataString().trim() != "") {
					if(usuario.getDataString().length() == 10) {
						data = UsuarioUtil.converterStringData(usuario.getDataString());
					}
					if(usuario.getDataString().length() != 10 || data == null) {
						flag = false;
					}else {
						usuario.setDataNascimento(data);
					}
				}
			}
			
			if(usuario.getSenha() != null) {
				if(usuario.getSenha().trim() != "") {
					if(usuario.getSenha().length() > 8) {
						flag = false;
					}
				}else {
					flag = false;
				}
			}else {
				flag = false;
			}
			
			if(usuario.getSenha() != null && usuario.getConfSenha() != null) {
				if(usuario.getSenha().trim() == "" && usuario.getConfSenha().trim() == "") {
					flag = false;
				}
				if(!usuario.getSenha().equals(usuario.getConfSenha())) {
					flag = false;
				}
			}
			
		}else {
			flag = false;
		}
		
		return flag;
	}
	
	public boolean validarEmail(String email) {
		boolean flag = true;
		
		return flag;
	}
	
	public boolean validarIdUsuario(int idUsuario) {
		boolean flag = true;
		
		return flag;
	}
	
	public boolean validarNome(String nome) {
		boolean flag = true;
		
		return flag;
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
