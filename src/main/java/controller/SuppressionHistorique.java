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
        HttpSession session = request.getSession();
        String erreur = null;
        Client client = (Client) session.getAttribute("client");
        Long numeroCompte = null;
        try {
            numeroCompte = Long.parseLong(request.getParameter("numeroCompte"));
        } catch (NumberFormatException e1) {
            erreur = "Numéro de compte incorrect";
        }

        if(erreur==null) {
            ApplicationContexte appContext = ApplicationContexte.getInstance();
            ClientService cs = appContext.getClientService();

            try {
                client = cs.suppressionHistoriqueVirement(client.getNumeroClient(), numeroCompte);
            } catch (CompteInexistantException e) {
                erreur = "Compte inexistant";
            }

            session.setAttribute("client", client);
        }
        request.setAttribute("erreur", erreur);
        getServletContext().getRequestDispatcher("/infosCompte.jsp").forward(request, response);
    }
}
