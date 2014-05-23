package asktechforum.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.Usuario;
import asktechforum.repositorio.UsuarioDAO;

/**
 * Implementação do Servlet de Pesquisa de Usuario.
 */
@WebServlet("/ServletPesquisaUsuario")
public class ServletPesquisaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String PESQUISA = "pesquisarUsuario.jsp";
    
    private UsuarioDAO usuarioDAO;
       
    /**
     * Construtor do Servlet de Pesquisa de Usuário.
     */
    public ServletPesquisaUsuario() {
        super();
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
	 * Implementacao do metodo doGet() Servlet de Pesquisa de Usuario.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Implementacao do metodo doPost() Servlet de Pesquisa de Usuario.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> listaUsuarios = null;
		String pesquisaRadio = request.getParameter("pesquisaRadio");
		String nome, email;
		
		if(pesquisaRadio != null && pesquisaRadio.trim() != "") {
			switch (pesquisaRadio) {
			case "nomeRadio":
				nome = request.getParameter("nome");
				if(nome.trim() != "" && nome != null) {
					listaUsuarios = new ArrayList<Usuario>();
					listaUsuarios.addAll(usuarioDAO.consultarUsuarioPorNome(nome));
				}
				break;
			case "emailRadio":
				email = request.getParameter("email");
				if(email.trim() != "" && email != null && usuarioDAO.consultarUsuarioPorEmail(email).getIdUsuario() != 0) {
					listaUsuarios = new ArrayList<Usuario>();
					listaUsuarios.add(usuarioDAO.consultarUsuarioPorEmail(email));
				}
				break;
			case "listartodosRadio":
				listaUsuarios = new ArrayList<Usuario>();
				listaUsuarios.addAll(usuarioDAO.consultarTodosUsuarios());
				break;
			case "":
				break;
			default:
				break;
			}
			
			RequestDispatcher view = request.getRequestDispatcher(PESQUISA);
			request.setAttribute("usuarios", listaUsuarios);
	        view.forward(request, response);
		}
	}

}
