package asktechforum.controller;

import asktechforum.repositorio.UsuarioDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.*;

/**
 * Servlet implementation class ServletRecuperarSenha
 */
@WebServlet("/ServletRecuperarSenha")
public class ServletRecuperarSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRecuperarSenha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailPesquisado = request.getParameter("email");
		UsuarioDAO consultaUsuario = new UsuarioDAO();
		Usuario usuario = consultaUsuario.consultarUsuarioPorEmail(emailPesquisado);
		
			try{
				if(usuario.getEmail() == null){
					request.getRequestDispatcher("respostaNegativaEsqueceuSenha.jsp").forward(request, response);
					
				}else{
					
					System.out.println(usuario.getSenha());
					System.out.println(usuario.getNome());
					System.out.println(usuario.getEmail());
					Email email = new Email();
					email.sendMail(usuario.getSenha(), usuario.getNome(),usuario.getEmail());
					request.getRequestDispatcher("respostaPositivaEsqueceuSenha.jsp").forward(request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		
		
		

}
