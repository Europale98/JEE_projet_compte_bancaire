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
import exception.CompteInexistantException;
import exception.DeficitImpossibleException;
import service.ClientService;

/**
 * Servlet implementation class Virement
 */
@WebServlet("/virement")
public class Virement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Virement() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double montant = Double.parseDouble(request.getParameter("montant"));

		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("service");
        appContext.scan("dao");
        appContext.scan("entity");
        appContext.scan("controller");
        appContext.refresh();
		ClientService cs = (ClientService) appContext.getBean("clientService");
		
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		Client client = null;
		if(type.equals("debit")) {
			try {
				client = cs.effectuerDebitCompte((Client) session.getAttribute("client"), Long.parseLong(request.getParameter("numeroCompte")), montant);
			} catch (NumberFormatException | DeficitImpossibleException | CompteInexistantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("credit")) {
			try {
				client = cs.effectuerCreditCompte((Client) session.getAttribute("client"), Long.parseLong(request.getParameter("numeroCompte")), montant);
			} catch (NumberFormatException | CompteInexistantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Long numeroCompteCredite = Long.parseLong(request.getParameter("compteCredite"));
			try {
				client = cs.effectuerVirementCompte((Client) session.getAttribute("client"), Long.parseLong(request.getParameter("numeroCompte")), numeroCompteCredite, montant);
			} catch (NumberFormatException | DeficitImpossibleException | CompteInexistantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		session.setAttribute("client", client);
			
		response.sendRedirect(request.getContextPath() + "/infosCompte.jsp");
	}
}
