package asktechforum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.Tag;
import asktechforum.negocio.CadastroPerguntaBC;
import asktechforum.negocio.CadastroTagBC;

/**
 * Servlet implementation class ServeltCadastroTag
 */
@WebServlet("/ServeltCadastroTag")
public class ServeltCadastroTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCESSOCADASTRO = "cadastroTagSucesso.jsp";
    private CadastroTagBC cadastro;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeltCadastroTag() {
        super();
        this.cadastro = new CadastroTagBC();
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
		Tag tag = new Tag();
		
		tag.setNome(request.getParameter("nome"));

		cadastro.adcionarTag(tag);
		
		RequestDispatcher view = request.getRequestDispatcher(SUCESSOCADASTRO);
		request.setAttribute("tag", tag);
        view.forward(request, response);
	}

}
