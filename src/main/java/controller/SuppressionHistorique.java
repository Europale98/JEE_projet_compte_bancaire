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
import entity.Compte;
import service.ClientService;

/**
 * Servlet implementation class SuppressionHistorique
 */
@WebServlet("/suppressionHistorique")
public class SuppressionHistorique extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppressionHistorique() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.refresh();
		ClientService cs = (ClientService) appContext.getBean("clientService");
		
		HttpSession session = request.getSession();
			
		Client client = cs.suppressionHistoriqueVirement((Client) session.getAttribute("client"), request.getParameter("numeroCompte"));
		
		session.setAttribute("client", client);
			
		response.sendRedirect(request.getContextPath() + "/accueil.jsp");
	}
}
