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
import service.ApplicationContexte;
import service.ClientService;

/**
 * Servlet implementation class Deconnection
 */
@WebServlet("/suppressionClient")
public class SuppressionClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppressionClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String suite = "/accueil.jsp";
        String erreur = null;
		HttpSession session = request.getSession();
		Client client = (Client) session.getAttribute("client");
		if(client==null) {
		    erreur = "Non connecté";
		}
        String mdp = request.getParameter("motDePasse");
		
		if(client!=null) {
        	ApplicationContexte appContext = ApplicationContexte.getInstance();
        	ClientService cs = appContext.getClientService();
        	
		}
		if(erreur == null) {
            ApplicationContexte appContext = ApplicationContexte.getInstance();
            ClientService cs = appContext.getClientService();
            if(cs.verificationMotDePasse(client.getNumeroClient(), mdp)) {
                cs.deleteClientByNumero(client.getNumeroClient());
                client = null;
            }
            else {
                erreur = "Mot de passe incorrect";
            }
        }
		
		session.setAttribute("client", client);
		
        if (erreur != null) {
            request.setAttribute("erreur", erreur);
            suite = "/update.jsp";
            getServletContext().getRequestDispatcher(suite).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + suite);
        }
	}

}
