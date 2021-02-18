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
        String suite = "/accueil.jsp";
        String erreur = null;
        Long num = null;
        try {
            num = Long.parseLong(request.getParameter("numeroClient"));
        } catch(NumberFormatException e) {
            erreur = "Numéro de compte incorrect";
        }
        String mdp = request.getParameter("motDePasse");

        if(erreur == null) {
            ApplicationContexte appContext = ApplicationContexte.getInstance();

            ClientService cs = appContext.getClientService();
            if(cs.verificationMotDePasse(num, mdp)) {
                HttpSession session = request.getSession();

                Client client = null;
                try {
                    client = cs.getClientByNumero(num);
                    session.setAttribute("client", client);
                } catch (ClientInexistantException e) {
                    erreur = "Numéro de compte ou mot de passe incorrect";
                }
            }
            else {
                erreur = "Numéro de compte ou mot de passe incorrect";
            }
        }
        if (erreur != null) {
            request.setAttribute("erreur", erreur);
            suite = "/connection.jsp";
            getServletContext().getRequestDispatcher(suite).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + suite);
        }
    }

}
