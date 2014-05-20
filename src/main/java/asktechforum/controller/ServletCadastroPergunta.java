package asktechforum.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.Tag;
import asktechforum.negocio.CadastroPerguntaBC;

/**
 * Servlet implementation class ServletCadastroPergunta
 */
@WebServlet("/ServletCadastroPergunta")
public class ServletCadastroPergunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCESSOCADASTRO = "cadastroPerguntaSucesso.jsp";
	private CadastroPerguntaBC cadastro;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCadastroPergunta() {
		super();
		// TODO Auto-generated constructor stub
		this.cadastro = new CadastroPerguntaBC();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Pergunta pergunta = new Pergunta();

		String flag = request.getParameter("flag");

		if (flag.contentEquals("pergunta")) {
			pergunta.setStrData(request.getParameter("data"));
			pergunta.setDescricao(request.getParameter("descricao"));
			pergunta.setStrHora(request.getParameter("hora"));
			pergunta.setTitulo(request.getParameter("titulo"));
			pergunta.setUsuario(Integer.parseInt(request
					.getParameter("usuario")));
			cadastro.adcionarPergunta(pergunta);
		} else {
			Tag tag = new Tag();
			pergunta.setIdPergunta(Integer.parseInt(request
					.getParameter("pergunta")));
			tag.setIdTag(Integer.parseInt(request.getParameter("tag")));
			cadastro.adcionarTag(pergunta.getIdPergunta(), tag.getIdTag());
		}

		RequestDispatcher view = request.getRequestDispatcher(SUCESSOCADASTRO);
		request.setAttribute("pergunta", pergunta);
		view.forward(request, response);

	}

}
