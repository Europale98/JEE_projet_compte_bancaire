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
@WebServlet("/actualiser")
public class ActualiserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualiserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String suite = request.getParameter("lien");
        suite += (request.getParameter("numeroCompte")!=null? "?numeroCompte=" + request.getParameter("numeroCompte") : "");
        
        ApplicationContexte appContext = ApplicationContexte.getInstance();
        ClientService cs = appContext.getClientService();
        HttpSession session = request.getSession();

        Client client = (Client) session.getAttribute("client");
        if(client != null) {
            try {
                client = cs.getClientByNumero(client.getNumeroClient());
                session.setAttribute("client", client);
            } catch (ClientInexistantException e) {
                session.setAttribute("client", null);
            }
        }
        response.sendRedirect(request.getContextPath() + suite);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
