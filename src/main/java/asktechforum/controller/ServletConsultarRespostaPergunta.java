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

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.Resposta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.negocio.CadastroPerguntaBC;
import asktechforum.negocio.CadastroRespostaBC;

/**
 * Servlet implementation class ServletConsultarRespostaPergunta
 */
@WebServlet("/ServletConsultarRespostaPergunta")
public class ServletConsultarRespostaPergunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String RESULTADO_CONSULTA = "consultarRespostaPorPergunta.jsp";
	private CadastroRespostaBC cadastro;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsultarRespostaPergunta() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		session.setAttribute("idPergunta", request.getParameter("id") );
		String idPergunta = request.getParameter("id");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
