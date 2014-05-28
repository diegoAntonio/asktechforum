package asktechforum.negocio;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//import java.sql.Connection;
//import java.sql.SQLException;



import asktechforum.dominio.Usuario;
import asktechforum.repositorio.UsuarioDAO;
//import asktechforum.util.ConnectionUtil;
import asktechforum.util.UsuarioUtil;
import asktechforum.dominio.Pergunta;
import asktechforum.dominio.Resposta;
import asktechforum.negocio.CadastroPerguntaBC;
import asktechforum.negocio.CadastroRespostaBC;

public class UsuarioBC {

	private UsuarioDAO usuarioDAO;
	private CadastroPerguntaBC perguntaBC;
	private CadastroRespostaBC respostaBC;
	
	public UsuarioBC() {
        super();
        this.usuarioDAO = new UsuarioDAO();
        this.perguntaBC = new CadastroPerguntaBC();
        this.respostaBC = new CadastroRespostaBC();
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
	
	public void deletarUsuarioPorId(int idUsuario) {
		this.usuarioDAO.deletarUsuarioPorId(idUsuario);
	}

	public void deletarUsuario(String email) {
		Usuario usuario = this.usuarioDAO.consultarUsuarioPorEmail(email);
		Usuario usuarioExcluido = new Usuario();
		ArrayList<Pergunta> perguntas = null;
		ArrayList<Resposta> respostas = null;
        Random geradorRandomico = new Random();
        int numeroRandomico = geradorRandomico.nextInt();
        int idUsuarioExcluido;
        
        while(numeroRandomico < 1 || numeroRandomico > 99999999 || this.usuarioDAO.consultarUsuarioPorId(numeroRandomico).getIdUsuario() == numeroRandomico) {
        	numeroRandomico = geradorRandomico.nextInt();
        }
		
		try {
			perguntas = this.perguntaBC.consultarPerguntaIdUsuario(usuario.getIdUsuario());
			respostas = this.respostaBC.consultarRespostaPorIdUsuario(usuario.getIdUsuario());
			
			if(perguntas != null || respostas != null) {
				if(perguntas.isEmpty() != true || respostas.isEmpty() != true) {
					usuarioExcluido.setNome("Usuário Excluído");
					usuarioExcluido.setDataString("");
					usuarioExcluido.setAdmin(false);
					usuarioExcluido.setEmail("usuarioExcluido@" + numeroRandomico + ".com");
					usuarioExcluido.setLocalizacao("");
					usuarioExcluido.setSenha(numeroRandomico+"");
					usuarioExcluido.setConfSenha(numeroRandomico+"");
					this.adicionarUsuario(usuarioExcluido);
					idUsuarioExcluido = this.usuarioDAO.consultarUsuarioPorEmail("usuarioExcluido@" + numeroRandomico + ".com").getIdUsuario();
					usuarioExcluido.setIdUsuario(usuario.getIdUsuario());
					this.alterarUsuario(usuarioExcluido);
					this.deletarUsuarioPorId(idUsuarioExcluido);
				}else {
					this.usuarioDAO.deletarUsuario(email);
				}
			}else {
				this.usuarioDAO.deletarUsuario(email);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				if(usuario.getNome().trim().equals("")) {
					flag = false;
				}
			}else {
				flag = false;
			}
			
			if(usuario.getEmail() != null) {
				if(usuario.getEmail().trim().equals("")) {
					flag = false;
				}else if(!usuario.getEmail()
						.matches("^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$")) {
					flag = false;
				}
			}else {
				flag = false;
			}
			
			if(usuario.getSenha() != null) {
				if(usuario.getSenha().trim().equals("")) {
					flag = false;
				}
			}else {
				flag = false;
			}
			
			if(usuario.getDataString() != null) {
				if(!usuario.getDataString().trim().equals("")) {
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
				if(!usuario.getSenha().trim().equals("")) {
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
				if(usuario.getSenha().trim().equals("") && usuario.getConfSenha().trim().equals("")) {
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
