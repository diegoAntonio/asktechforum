package asktechforum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.Usuario;
import asktechforum.repositorio.UsuarioDAO;
import asktechforum.util.UsuarioUtil;

/**
 * Implementação do Servlet de Alterar Perfil de Usuario.
 */
@WebServlet("/ServletAlteracaoUsuario")
public class ServletAlteracaoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String ALTERAR = "alterarUsuario.jsp";
    private static String SUCESSOALTERACAO = "perfilUsuario.jsp";
    
	private UsuarioDAO dao;
       
    /**
     * Construtor do Servlet de Alterar Perfil de Usuário.
     */
    public ServletAlteracaoUsuario() {
        super();
        this.dao = new UsuarioDAO();
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
		
		String pesquisaUsuarioEmail = request.getParameter("pesquisaUsuarioEmail");
		String alteracaoUsuarioId = request.getParameter("alteracaoUsuarioId");
		int idUsuario;
		RequestDispatcher view;
		
		if(pesquisaUsuarioEmail != null) {
			usuario = this.dao.consultarUsuarioPorEmail(pesquisaUsuarioEmail);
			
			view = request.getRequestDispatcher(ALTERAR);
			request.setAttribute("usuario", usuario);
			view.forward(request, response);
		
		}else if(alteracaoUsuarioId != null) {
			idUsuario = Integer.parseInt(alteracaoUsuarioId);
			usuario = this.dao.consultarUsuarioPorId(idUsuario);
			
			usuario.setNome(request.getParameter("nome"));
			usuario.setDataNascimento(UsuarioUtil.converterStringData(request.getParameter("dataNascimento")));
			usuario.setEmail(request.getParameter("email"));
			usuario.setLocalizacao(request.getParameter("localizacao"));
			
			if(request.getParameter("admin") == "true") { 
				usuario.setAdmin(true);
			}else {
				usuario.setAdmin(false);
			}
			
			this.dao.alterarUsuario(usuario);
			
			view = request.getRequestDispatcher(SUCESSOALTERACAO);
			request.setAttribute("usuario", usuario);
	        view.forward(request, response);
		}
	}

}
