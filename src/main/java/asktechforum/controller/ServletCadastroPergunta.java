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
import asktechforum.negocio.CadastroPerguntaBC;
import asktechforum.util.Util;

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

		this.cadastro = new CadastroPerguntaBC();
		Pergunta pergunta = new Pergunta();

		pergunta.setStrData(Util.getDataSistema());
		pergunta.setDescricao(request.getParameter("descricao"));
		pergunta.setStrHora(Util.getHoraSistema());
		pergunta.setTitulo(request.getParameter("titulo"));
		pergunta.setUsuario(1);
		//pergunta.setUsuario(Integer.parseInt(request.getParameter("1")));
		pergunta.setTag(request.getParameter("tag"));
		cadastro.adcionarPergunta(pergunta);

		RequestDispatcher view = request.getRequestDispatcher(SUCESSOCADASTRO);
		request.setAttribute("pergunta", pergunta);
		view.forward(request, response);

	}

}
