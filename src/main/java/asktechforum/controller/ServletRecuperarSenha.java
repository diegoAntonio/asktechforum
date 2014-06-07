package asktechforum.controller;

import asktechforum.negocio.UsuarioBC;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.*;

/**
 * Implementacao do Servlet de Recuperar Senha.
 */
@WebServlet("/ServletRecuperarSenha")
public class ServletRecuperarSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Construtor do Servlet de Recuperar Senha.
     */
    public ServletRecuperarSenha() {
        super();
    }

	/**
	 * Implementacao do metodo doGet() de Recuperar Senha.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Implementacao do metodo doPost() de Recuperar Senha.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailPesquisado = request.getParameter("email");
		UsuarioBC consultaUsuario = new UsuarioBC();
		Usuario usuario = consultaUsuario.consultarUsuarioPorEmail(emailPesquisado);
		
		try{
			if(usuario.getEmail() == null){
				request.getRequestDispatcher("respostaNegativaEsqueceuSenha.jsp").forward(request, response);
				
			}else{
				Email email = new Email();
				email.sendMail(usuario.getSenha(), usuario.getNome(),usuario.getEmail());
				request.getRequestDispatcher("respostaPositivaEsqueceuSenha.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
