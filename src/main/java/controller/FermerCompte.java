package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Client;
import entity.Compte;
import exception.AuMoinsUnCompteException;
import exception.CompteInexistantException;
import service.ApplicationContexte;
import service.ClientService;

/**
 * Servlet implementation class FermerCompte
 */
@WebServlet("/fermerCompte")
public class FermerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FermerCompte() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContexte appContext = ApplicationContexte.getInstance();

        ClientService cs = appContext.getClientService();
		
		HttpSession session = request.getSession();
			
		Client client = (Client) session.getAttribute("client");
		Long numeroCompte = Long.parseLong(request.getParameter("numeroCompte"));
		try {
			client = cs.fermerCompteClient(client, numeroCompte);
		} catch (NumberFormatException | CompteInexistantException | AuMoinsUnCompteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("client", client);
			
		response.sendRedirect(request.getContextPath() + "/comptes.jsp");
	}
}
