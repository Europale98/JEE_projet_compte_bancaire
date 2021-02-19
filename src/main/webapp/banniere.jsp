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
		<li><a href="comptes.jsp" <%=((request.getServletPath().equals("/comptes.jsp")||request.getServletPath().equals("/infosCompte.jsp"))?"class=\"active\"":"")%>>Mes Comptes</a></li>
		<li><a href="update.jsp" <%=(request.getServletPath().equals("/update.jsp")?"class=\"active\"":"")%>>Modification</a></li>
		<li class="right"><a href="deconnection">Déconnexion</a></li>
		<li class="right"><a href="actualiser?lien=<%=request.getServletPath()%>
				<%=(request.getServletPath().equals("/infosCompte.jsp")?
			        ("&numeroCompte=" + request.getParameter("numeroCompte")):"")%>">
			<svg id="i-reload" x="0px" y="0px" width="22px" height="22px" viewBox="0 0 32 32"  fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" >
		    	<path d="M29 16 C29 22 24 29 16 29 8 29 3 22 3 16 3 10 8 3 16 3 21 3 25 6 27 9 M20 10 L27 9 28 2" />
		    </svg>
		</a></li>
	<%
		}
	%>
	</ul>
</div>
