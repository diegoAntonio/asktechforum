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

import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.negocio.CadastroPerguntaBC;

/**
 * Implementacao do Servlet de Consultar Pergunta por Tag.
 */
@WebServlet("/ServletConsultarPerguntaPorTag")
public class ServletConsultarPerguntaPorTag extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String RESULTADO_CONSULTA = "consultaPerguntaPorTag.jsp";
	private static final String INDEX = "index.jsp";
	private static final String TODAS_AS_TAGS = "consultaTodas_asTags.jsp";
	private CadastroPerguntaBC cadastro;

	/**
     * Construtor do Servlet de Consultar Pergunta por Tag.
     */
    public ServletConsultarPerguntaPorTag() {
        super();
    }
    
    /**
	 * Implementacao do metodo service() Servlet de Consultar Pergunta por Tag.
	 */
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
    		throws ServletException, IOException {
    	super.service(arg0, arg1);
    }
    
    /**
	 * Implementacao do metodo doGet() Servlet de Consultar Pergunta por Tag.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String tag = request.getParameter("tag");
		this.cadastro = new CadastroPerguntaBC();
		ArrayList<ResultConsultarPergunta> perguntas;
		ArrayList<String> tags;
		RequestDispatcher view ; 
		
		if(tag.equals("allTags")){
			tags = cadastro.consultaTodasAsTags();
			view = request.getRequestDispatcher(TODAS_AS_TAGS);
			request.setAttribute("tags", tags);
		}else{
			perguntas = cadastro.consultarPerguntaPorTag(tag);
				
			if(tag.equals("all")){
				view = request.getRequestDispatcher(INDEX);
			}else{
				view = request.getRequestDispatcher(RESULTADO_CONSULTA);
			}
		
			request.setAttribute("pergunta", perguntas);
			
		}
		
		
		view.forward(request, response);
	}

	/**
	 * Implementacao do metodo doPost() Servlet de Consultar Pergunta por Tag.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag = request.getParameter("tag");
		this.cadastro = new CadastroPerguntaBC();
		ArrayList<ResultConsultarPergunta> tags = cadastro.consultarPerguntaPorTag(tag);
		RequestDispatcher view;
        HttpSession session = request.getSession();
		
		session.setAttribute("stop", false);
		
		if(tag.equals("all")){
			view = request.getRequestDispatcher(INDEX);
		}else{
			view = request.getRequestDispatcher(RESULTADO_CONSULTA);
		}
		
		if(tags.get(0).getIdPergunta() > 0) {
			request.setAttribute("pergunta", tags);
		}
		view.forward(request, response);
	}

}
