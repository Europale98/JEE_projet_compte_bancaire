<%@ page import="entity.Client"%>
<!DOCTYPE html>
<html>
<%
    Client client = (Client) session.getAttribute("client");
	String erreur = (String) request.getAttribute("erreur");
%>
<ul>
	<li><a href="accueil.jsp">Accueil</a></li>
	<%
	    if (client == null) {
	%>
	<li><a href="connection.jsp">Connection</a></li>
	<li><a href="inscription.jsp">Inscription</a></li>
	<%
	    } else {
	%>
	<li><a href="comptes.jsp">Vos Comptes</a></li>
	<li><a href="update.jsp">Modification</a></li>
	<li><a href="deconnection">Déconnection</a></li>
	<%
	    }
	%>
</ul>
</html>