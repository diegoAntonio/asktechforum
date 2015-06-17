package asktechforum.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.Usuario;
import asktechforum.negocio.UsuarioBC;

/**
 * Implementacao do Servlet de Pesquisa de Usuario.
 */
public class ServletPesquisaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String PESQUISA = "pesquisarUsuario.jsp";
    
    private UsuarioBC usuarioBC;
       
    /**
     * Construtor do Servlet de Pesquisa de Usuario.
     */
    public ServletPesquisaUsuario() {
        super();
        this.usuarioBC = new UsuarioBC();
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
					listaUsuarios.addAll(this.usuarioBC.consultarUsuarioPorNome(nome));
				}
				break;
			case "emailRadio":
				email = request.getParameter("email");
				if(email.trim() != "" && email != null) {
					if(this.usuarioBC.consultarUsuarioPorEmail(email) != null) {
						if(this.usuarioBC.consultarUsuarioPorEmail(email).getIdUsuario() != 0) {
							listaUsuarios = new ArrayList<Usuario>();
							listaUsuarios.add(this.usuarioBC.consultarUsuarioPorEmail(email));
						}
					}
				}
				break;
			case "listartodosRadio":
				listaUsuarios = new ArrayList<Usuario>();
				listaUsuarios.addAll(this.usuarioBC.consultarTodosUsuarios());
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
