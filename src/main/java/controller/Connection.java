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
import exception.ClientInexistantException;

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
		Long num = Long.parseLong(request.getParameter("numeroClient"));
		String mdp = request.getParameter("motDePasse");
		
		String suite = ""; 
		ApplicationContexte appContext = ApplicationContexte.getInstance();

        ClientService cs = appContext.getClientService();
		if(cs.verificationMotDePasse(num, mdp)) {
			suite = "/accueil.jsp";
			HttpSession session = request.getSession();
			
			Client client = null;
            try {
                client = cs.getClientByNumero(num);
            } catch (ClientInexistantException e) {
                System.out.println(e.getMessage());
            }
			
			session.setAttribute("client", client);
		}
		else {
			suite = "/loginIncorrect.jsp";
		}
		//getServletContext().getRequestDispatcher(suite).forward(request, response);
		response.sendRedirect(request.getContextPath() + suite);
	}

}
