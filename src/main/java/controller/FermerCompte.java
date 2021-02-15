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
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        String suite = "/comptes.jsp";
        String erreur = null;
        Long numeroCompte = null;
        try {
            numeroCompte = Long.parseLong(request.getParameter("numeroCompte"));
        } catch(NumberFormatException e) {
            erreur = e.getMessage();
        }
        if(erreur==null) {
            ApplicationContexte appContext = ApplicationContexte.getInstance();
            ClientService cs = appContext.getClientService();

            try {
                client = cs.fermerCompteClient(client, numeroCompte);
            } catch (CompteInexistantException | AuMoinsUnCompteException e) {
                erreur = e.getMessage();
            }
            session.setAttribute("client", client);
        }
        if (erreur!=null) {
            request.setAttribute("erreur", erreur);
            suite = "/infosCompte.jsp";
            getServletContext().getRequestDispatcher(suite).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + suite);
        }
    }
}
