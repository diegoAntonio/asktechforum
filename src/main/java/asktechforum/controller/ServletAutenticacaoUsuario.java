package asktechforum.controller;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import asktechforum.dominio.Usuario;
import asktechforum.negocio.UsuarioBC;
 

public class ServletAutenticacaoUsuario  extends HttpServlet {
    private static final long serialVersionUID = 7633293501883840556L;
    
    /**
     * Construtor do Servlet de Autenticacao de Usuario.
     */
    public ServletAutenticacaoUsuario() {
        super();
    }
 
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
    		throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.service(arg0, arg1);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {         
        
        String logout = (String) request.getParameter("logout");          
          
        HttpSession session = request.getSession();  
        if ("true".equals(logout)){   
                
            response.sendRedirect(getServletContext().getContextPath()+"/index.jsp"); 
            session.invalidate(); 
        }            
    }  
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                         throws ServletException, IOException{
 
        HttpSession session = request.getSession(); //obtem a sessao do usuario, caso exista
        UsuarioBC usuarioBC = new UsuarioBC();
    	Usuario user = null;
    	String email_form = request.getParameter("email"); // Pega o email vindo do formulario
    	String senha_form = request.getParameter("senha"); //Pega a senha vinda do formulario
    	Boolean saudacao = false;

    	try {
    		user = usuarioBC.consultarUsuarioPorEmail_Senha(email_form, senha_form);
    	}
    	catch ( Exception e ){

    	}

    	//se nao encontrou usuario no banco, redireciona para a pagina de login
    	if ( user == null ) {
    		session.setAttribute("erro","E-mail ou Senha inválidos!");
    		request.getRequestDispatcher("/login.jsp" ).forward(request, response);
    	}
    	else{
    		saudacao = true;
    		//se o dao retornar um usuario, coloca o mesmo na sessao
    		session.setAttribute("usuarioLogado", user);
    		session.setAttribute("saudacao", saudacao);
    		request.getRequestDispatcher("/index.jsp" ).forward(request, response);
    	}
    } 
}