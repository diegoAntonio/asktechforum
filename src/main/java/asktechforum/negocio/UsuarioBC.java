package asktechforum.negocio;

import java.util.ArrayList;
import java.util.List;

import asktechforum.dominio.Usuario;
import asktechforum.repositorio.UsuarioDAO;

public class UsuarioBC {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioBC() {
        super();
        this.usuarioDAO = new UsuarioDAO();
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
