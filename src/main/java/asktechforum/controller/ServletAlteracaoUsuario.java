package asktechforum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.Usuario;
import asktechforum.repositorio.UsuarioDAO;
import asktechforum.util.UsuarioUtil;
import asktechforum.negocio.UsuarioBC;

/**
 * Implementação do Servlet de Alterar Perfil de Usuario.
 */
@WebServlet("/ServletAlteracaoUsuario")
public class ServletAlteracaoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String ALTERAR = "./usuarioAutenticado/alterarUsuario.jsp";
    private static String ERROALTERAREMAIL = "./usuarioAutenticado/alterarUsuario.jsp";
    private static String SUCESSOALTERACAO = "perfilUsuario.jsp";
    private static String ERROALTERACAO = "./usuarioAutenticado/alteracaoExclusaoUsuarioErro.jsp";
    
	private UsuarioDAO usuarioDAO;
	private UsuarioBC usuarioBC;
       
    /**
     * Construtor do Servlet de Alterar Perfil de Usuário.
     */
    public ServletAlteracaoUsuario() {
        super();
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioBC = new UsuarioBC();
    }

    /**
	 * Implementacao do metodo doGet() Servlet de Alterar Perfil de Usuario.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Implementacao do metodo doPost() Servlet de Alterar Perfil de Usuario.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		HttpSession session = request.getSession();
		int quantAdmin;
		
		String pesquisaUsuarioEmail = request.getParameter("pesquisaUsuarioEmail");
		String alteracaoUsuarioId = request.getParameter("alteracaoUsuarioId");
		String alteracaoAdminId = request.getParameter("alteracaoAdminId");
		
		int idUsuario = 0;
		RequestDispatcher view;
		
    	session.setAttribute("erroAlteracaoExclusao", true);
		
		if(pesquisaUsuarioEmail != null) {
			usuario = this.usuarioDAO.consultarUsuarioPorEmail(pesquisaUsuarioEmail);
			
			view = request.getRequestDispatcher(ALTERAR);
			request.setAttribute("usuarioAlteracao", usuario);
			view.forward(request, response);
		
		}else if(alteracaoUsuarioId != null) {
			idUsuario = Integer.parseInt(alteracaoUsuarioId);
			
			usuario = this.usuarioDAO.consultarUsuarioPorId(idUsuario);
			quantAdmin = this.usuarioBC.consultarQuantidadeAdmin(usuario);

			if(!this.usuarioBC.verificarEmail(request.getParameter("email"), usuario)) {
				usuario.setNome(request.getParameter("nome"));
				usuario.setDataNascimento(UsuarioUtil.converterStringData(request.getParameter("dataNascimento")));
				usuario.setEmail(request.getParameter("email"));
				usuario.setLocalizacao(request.getParameter("localizacao"));
				usuario.setSenha(request.getParameter("senha"));
				
				if(request.getParameter("admin") != null) {
					if(request.getParameter("admin").trim().equals("true")) { 
						usuario.setAdmin(true);
						this.usuarioDAO.alterarUsuario(usuario);
						
						view = request.getRequestDispatcher(SUCESSOALTERACAO);
						request.setAttribute("usuarioPerfil", usuario);
						session.setAttribute("usuarioLogado", usuario);
				        view.forward(request, response);
					}
				}else {
					if(quantAdmin > 1) {
						usuario.setAdmin(false);
						this.usuarioDAO.alterarUsuario(usuario);
						
						view = request.getRequestDispatcher(SUCESSOALTERACAO);
						request.setAttribute("usuarioPerfil", usuario);
						session.setAttribute("usuarioLogado", usuario);
				        view.forward(request, response);
			        }else {
						view = request.getRequestDispatcher(ERROALTERACAO);
				        view.forward(request, response);
			        }
				}
			}else {
				view = request.getRequestDispatcher(ERROALTERAREMAIL);
				request.setAttribute("emailExistente", true);
				request.setAttribute("usuarioAlteracao", usuario);
				view.forward(request, response);
			}
	        
		}else if(alteracaoAdminId != null) {
			idUsuario = Integer.parseInt(alteracaoAdminId);
			
			usuario = this.usuarioDAO.consultarUsuarioPorId(idUsuario);
			
			if(request.getParameter("admin") != null) {
				usuario.setAdmin(true);
			}else {
				usuario.setAdmin(false);
			}
			
			this.usuarioDAO.alterarUsuarioAdmin(usuario);
			
			view = request.getRequestDispatcher(SUCESSOALTERACAO);
			request.setAttribute("usuarioPerfil", usuario);
	        view.forward(request, response);
			
		}
	}

}
