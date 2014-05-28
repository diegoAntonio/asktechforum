package asktechforum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.Resposta;
import asktechforum.dominio.Usuario;
import asktechforum.negocio.CadastroPerguntaBC;
import asktechforum.negocio.CadastroRespostaBC;
import asktechforum.util.Util;

/**
 * Servlet implementation class ServletCadastroResposta
 */
@WebServlet("/ServletCadastroResposta")
public class ServletCadastroResposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCESSOCADASTRO = "usuarioAutenticado/cadastroRespostaSucesso.jsp";
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
		Resposta resposta = new Resposta();
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		
		resposta.setStrData(Util.getDataSistema());
		resposta.setDescricao(request.getParameter("descricao"));
		resposta.setStrHora(Util.getHoraSistema());
		resposta.setIdPergunta(Integer.parseInt((String)session.getAttribute("idPergunta")));
		resposta.setIdUsuario(usuario.getIdUsuario());
		
		
		String retornoCadastroResposta = cadastro.adicionarResposta(resposta);
		
		if (retornoCadastroResposta != null && !retornoCadastroResposta.equals("cadastroSucesso")) {
    		session.setAttribute("erroCadastroResposta",retornoCadastroResposta);
    		request.setAttribute("resposta", resposta);
    		request.getRequestDispatcher("usuarioAutenticado/responder.jsp" ).forward(request, response);
    	}else{
    		RequestDispatcher view = request.getRequestDispatcher(SUCESSOCADASTRO);
    		request.setAttribute("resposta", resposta);
            view.forward(request, response);
    	}
		
	}
}
