package asktechforum.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.Resposta;
import asktechforum.negocio.CadastroRespostaBC;

/**
 * Implementacao do Servlet de Consultar Respostas de Pergunta.
 */
@WebServlet("/ServletConsultarRespostaPergunta")
public class ServletConsultarRespostaPergunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String RESULTADO_CONSULTA = "consultarRespostaPorPergunta.jsp";
	private CadastroRespostaBC cadastro;
       
	/**
     * Construtor do Servlet de Consultar Respostas de Pergunta.
     */
    public ServletConsultarRespostaPergunta() {
        super();
    }

	/**
	 * Implementacao do metodo service() Servlet de Consultar Respostas de Pergunta.
	 */
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
	}

	/**
	 * Implementacao do metodo doGet() Servlet de Consultar Respostas de Pergunta.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("idPergunta", request.getParameter("idPergunta"));
		String idPergunta = request.getParameter("idPergunta");
		String descricao = request.getParameter("descricao");
		String autor = request.getParameter("autor");
		String titulo = request.getParameter("titulo");
		
		
		this.cadastro = new CadastroRespostaBC();

		ArrayList<Resposta> resp = cadastro.consultarRespostaPorPergunta(Integer.parseInt(idPergunta));

		RequestDispatcher view = request.getRequestDispatcher(RESULTADO_CONSULTA);
		request.setAttribute("resposta", resp);
		request.setAttribute("descricao", descricao);
		request.setAttribute("autor", autor);
		request.setAttribute("titulo", titulo);
		view.forward(request, response);
	}

	/**
	 * Implementacao do metodo doPost() Servlet de Consultar Respostas de Pergunta.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("idPergunta", request.getParameter("idPergunta"));
		String idPergunta = (String)session.getAttribute("idPergunta");
		String descricao = request.getParameter("descricao");
		String autor = request.getParameter("autor");
		String titulo = request.getParameter("titulo");
		
		this.cadastro = new CadastroRespostaBC();

		ArrayList<Resposta> resp = this.cadastro.consultarRespostaPorPergunta(Integer.parseInt(idPergunta));

		RequestDispatcher view = request.getRequestDispatcher(RESULTADO_CONSULTA);
		request.setAttribute("resposta", resp);
		request.setAttribute("descricao", descricao);
		request.setAttribute("autor", autor);
		request.setAttribute("titulo", titulo);
		view.forward(request, response);
	}

}
