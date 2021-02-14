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
import exception.DeficitImpossibleException;
import service.ClientService;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mdp = request.getParameter("motDePasse");
		String numeroRue = request.getParameter("numeroRue");
		String ville = request.getParameter("ville");
		double montant = Double.parseDouble(request.getParameter("montant"));
		
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.refresh();
		ClientService cs = (ClientService) appContext.getBean("clientService");
		
		HttpSession session = request.getSession();
			
		Client client = null;
        try {
            client = cs.createClient(nom, prenom, mdp, numeroRue, ville, montant);
        } catch (DeficitImpossibleException e) {
            System.out.println(e.getMessage());
        }
			
		session.setAttribute("client", client);
			
		response.sendRedirect(request.getContextPath() + "/accueil.jsp");
	}
}
