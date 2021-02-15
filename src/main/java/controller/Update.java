package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Client;
import service.ApplicationContexte;
import service.ClientService;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		
		ApplicationContexte appContext = ApplicationContexte.getInstance();
        ClientService cs = appContext.getClientService();
		
		HttpSession session = request.getSession();
		Client client = cs.updateClient((Client) session.getAttribute("client"), nom, prenom, mdp, numeroRue, ville);
		session.setAttribute("client", client);
			
		response.sendRedirect(request.getContextPath() + "/accueil.jsp");
	}
}
