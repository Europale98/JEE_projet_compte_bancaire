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
import exception.DeficitImpossibleException;
import exception.MontantImpossibleException;
import service.ApplicationContexte;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String erreur = null;
        HttpSession session = request.getSession();
        String type = request.getParameter("type");
        Long numeroCompte = null;
        try {
            numeroCompte = Long.parseLong(request.getParameter("numeroCompte"));
        } catch (NumberFormatException e1) {
            erreur = e1.getMessage();
        }
        Client client = (Client) session.getAttribute("client");
        double montant = 0;
        try {
            montant = Double.parseDouble(request.getParameter("montant"));
        } catch (NumberFormatException e1) {
            erreur = e1.getMessage();
        }

        if(erreur==null) {
            ApplicationContexte appContext = ApplicationContexte.getInstance();
            ClientService cs = appContext.getClientService();

            if (type.equals("debit")) {
                try {
                    client = cs.effectuerDebitCompte(client, numeroCompte, montant);
                } catch (DeficitImpossibleException | CompteInexistantException | MontantImpossibleException e) {
                    erreur = e.getMessage();
                }
            } else if (type.equals("credit")) {
                try {
                    client = cs.effectuerCreditCompte(client, numeroCompte, montant);
                } catch (CompteInexistantException | MontantImpossibleException e) {
                    erreur = e.getMessage();
                }
            } else if(type.equals("virement")){
                Long numeroCompteCredite = Long.parseLong(request.getParameter("compteCredite"));
                try {
                    client = cs.effectuerVirementCompte(client, numeroCompte,
                            numeroCompteCredite, montant);
                } catch (DeficitImpossibleException | CompteInexistantException | MontantImpossibleException e) {
                    erreur = e.getMessage();
                }
            }

            session.setAttribute("client", client);
        }
        request.setAttribute("erreur", erreur);
        getServletContext().getRequestDispatcher("/infosCompte.jsp").forward(request, response);
    }
}
