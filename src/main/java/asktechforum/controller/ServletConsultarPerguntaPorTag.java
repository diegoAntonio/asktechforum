package asktechforum.controller;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.Pergunta;
import asktechforum.negocio.CadastroPerguntaBC;
import asktechforum.util.Util;

/**
 * Servlet implementation class ServletConsultarPerguntaPorTag
 */
@WebServlet("/ServletConsultarPerguntaPorTag")
public class ServletConsultarPerguntaPorTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PESQUISA_TAG = "consultaPerguntaPorTag.jsp";
	private CadastroPerguntaBC cadastro;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsultarPerguntaPorTag() {
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
		// TODO Auto-generated method stub
		
		String tag = request.getParameter("tag");
		
		ArrayList<Pergunta> resultado = new ArrayList<Pergunta>();
		
		this.cadastro = new CadastroPerguntaBC();
		
		resultado = cadastro.consultarPerguntaPorTag(tag);

		RequestDispatcher view = request.getRequestDispatcher(PESQUISA_TAG);
		request.setAttribute("pergunta", resultado);
		view.forward(request, response);
	}

}
