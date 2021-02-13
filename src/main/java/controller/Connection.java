package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import entity.Client;
import service.ClientService;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("numeroClient");
		String mdp = request.getParameter("motDePasse");
		System.out.println(num);
		System.out.println(mdp);
		
		String suite = ""; 
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.refresh();
        
		ClientService cs = (ClientService) appContext.getBean("clientService");
		if(cs.verificationMotDePasse(Long.getLong(num), mdp)) {
			suite = "/accueil.jsp";
			HttpSession session = request.getSession();
			
			Client client = cs.getClientByNumero(Long.getLong(num));
			
			session.setAttribute("client", client);
		}
		else {
			suite = "/loginIncorrect.jsp";
		}
		//getServletContext().getRequestDispatcher(suite).forward(request, response);
		response.sendRedirect(request.getContextPath() + suite);
	}

}
