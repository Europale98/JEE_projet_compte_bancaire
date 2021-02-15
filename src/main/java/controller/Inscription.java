package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Client;
import exception.DeficitImpossibleException;
import service.ApplicationContexte;
import service.ClientService;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String erreur = null;
        String suite = "/accueil.jsp";
        String nom = request.getParameter("nom");
        if (nom==null||nom.isEmpty()) {
            erreur = "Nom est vide.";
        }
        String prenom = request.getParameter("prenom");
        if (prenom==null||prenom.isEmpty()) {
            erreur = "Prenom est vide.";
        }
        String mdp = request.getParameter("motDePasse");
        if (mdp==null||mdp.isEmpty()) {
            erreur = "Mot de passe est vide.";
        }
        String numeroRue = request.getParameter("numeroRue");
        if (numeroRue==null||numeroRue.isEmpty()) {
            erreur = "Numero et rue est vide.";
        }
        String ville = request.getParameter("ville");
        if (ville==null||ville.isEmpty()) {
            erreur = "Ville est vide.";
        }
        double montant = 0;
        try {
            montant = Double.parseDouble(request.getParameter("montant"));
        } catch (NumberFormatException e) {
            erreur = e.getMessage();
        }

        if(erreur != null) {
            ApplicationContexte appContext = ApplicationContexte.getInstance();

            ClientService cs = appContext.getClientService();

            HttpSession session = request.getSession();

            Client client = null;
            try {
                client = cs.createClient(nom, prenom, mdp, numeroRue, ville, montant);
            } catch (DeficitImpossibleException e) {
                erreur = e.getMessage();
            }

            session.setAttribute("client", client);
        }

        if (erreur != null) {
            request.setAttribute("erreur", erreur);
            suite = "/inscription.jsp";
            getServletContext().getRequestDispatcher(suite).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + suite);
        }
    }
}
