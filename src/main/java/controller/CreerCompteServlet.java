package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Client;
import exception.ClientInexistantException;
import exception.DeficitImpossibleException;
import service.ApplicationContexte;
import service.ClientService;

/**
 * Servlet implementation class CreerCompte
 */
@WebServlet("/creerCompte")
public class CreerCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerCompteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String erreur = null;
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        if(client==null) {
            erreur = "Non connecté";
        }
        double montant = 0;
		try {
		    montant = Double.parseDouble(request.getParameter("montant"));
		} catch(NumberFormatException e) {
		    erreur = "Montant incorrect";
		}
		if (erreur == null) {
    		ApplicationContexte appContext = ApplicationContexte.getInstance();
            ClientService cs = appContext.getClientService();
            
    		try {
    			client = cs.creerCompteClient(client.getNumeroClient(), montant);
    		} catch (DeficitImpossibleException e) {
    		    erreur = "Deficit impossible";
    		} catch (ClientInexistantException e) {
    		    client = null;
    		    erreur = e.getMessage();
            }
		}
		
		session.setAttribute("client", client);
		request.setAttribute("erreur", erreur);

        getServletContext().getRequestDispatcher("/comptes.jsp").forward(request, response);
	}
}
