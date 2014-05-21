package asktechforum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.Resposta;
import asktechforum.negocio.CadastroPerguntaBC;
import asktechforum.negocio.CadastroRespostaBC;

/**
 * Servlet implementation class ServletCadastroResposta
 */
@WebServlet("/ServletCadastroResposta")
public class ServletCadastroResposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCESSOCADASTRO = "cadastroRespostaSucesso.jsp";
	private CadastroRespostaBC cadastro;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCadastroResposta() {
        super();
        this.cadastro = new CadastroRespostaBC();
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
		Resposta resposta = new Resposta();
		
		resposta.setStrData(request.getParameter("data"));
		resposta.setDescricao(request.getParameter("descricao"));
		resposta.setStrHora(request.getParameter("hora"));
		resposta.setIdPergunta(Integer.parseInt(request.getParameter("pergunta")));
		resposta.setIdUsuario(Integer.parseInt(request.getParameter("usuario")));
		
		cadastro.adcionarResposta(resposta);
		
		RequestDispatcher view = request.getRequestDispatcher(SUCESSOCADASTRO);
		request.setAttribute("resposta", resposta);
        view.forward(request, response);
	}

}
