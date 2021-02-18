<%@ page import="entity.Client"%>
<%
	Client client = (Client) session.getAttribute("client");
	String erreur = (String) request.getAttribute("erreur");
%>
<div id="banniere">
	<ul>
		<li><a href="accueil.jsp" <%=(request.getServletPath().equals("/accueil.jsp")?"class=\"active\"":"")%>>Accueil</a></li>
	<%
		if (client == null) {
	%>
		<li class="right"><a href="inscription.jsp" <%=(request.getServletPath().equals("/inscription.jsp")?"class=\"active\"":"")%>>Inscription</a></li>
		<li class="right"><a href="connection.jsp" <%=(request.getServletPath().equals("/connection.jsp")?"class=\"active\"":"")%>>Connexion</a></li>
	<%
		} else {
	%>
		<li><a href="comptes.jsp" <%=((request.getServletPath().equals("/comptes.jsp")||request.getServletPath().equals("/infosCompte.jsp"))?"class=\"active\"":"")%>>Vos Comptes</a></li>
		<li><a href="update.jsp" <%=(request.getServletPath().equals("/update.jsp")?"class=\"active\"":"")%>>Modification</a></li>
		<li class="right"><a href="deconnection">Déconnexion</a></li>
	<%
		}
	%>
	</ul>
</div>
