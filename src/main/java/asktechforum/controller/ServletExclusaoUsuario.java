package asktechforum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.repositorio.UsuarioDAO;

/**
 * Implementação do Servlet de Exclusao de Usuario.
 */
@WebServlet("/ServletExclusaoUsuario")
public class ServletExclusaoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String SUCESSOEXCLUSAO = "exclusaoUsuarioSucesso.jsp";
    
	private UsuarioDAO dao;

    /**
     * Construtor do Servlet de Exclusao de Usuario.
     */
    public ServletExclusaoUsuario() {
        super();
        this.dao = new UsuarioDAO();
    }

	/**
	 * Implementacao do metodo doGet() Servlet de Exclusao de Usuario.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Implementacao do metodo doPost() Servlet de Exclusao de Usuario.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view;
		
		String pesquisaUsuarioEmail = request.getParameter("exclusaoUsuarioEmail");
		
		if(pesquisaUsuarioEmail != null) {
			this.dao.deletarUsuario(pesquisaUsuarioEmail);
			
			view = request.getRequestDispatcher(SUCESSOEXCLUSAO);
			view.forward(request, response);
		
		}
	}

}
