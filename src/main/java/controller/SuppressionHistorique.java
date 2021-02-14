package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Client;
import exception.CompteInexistantException;
import service.ApplicationContexte;
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
		ApplicationContexte appContext = ApplicationContexte.getInstance();

        ClientService cs = appContext.getClientService();
		
		HttpSession session = request.getSession();
			
		Client client = null;
		try {
			client = cs.suppressionHistoriqueVirement((Client) session.getAttribute("client"), Long.parseLong(request.getParameter("numeroCompte")));
		} catch (NumberFormatException | CompteInexistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("client", client);
			
		response.sendRedirect(request.getContextPath() + "/accueil.jsp");
	}
}
