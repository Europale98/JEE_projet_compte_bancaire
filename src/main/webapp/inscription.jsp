
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
</head>
<body>
	<%@include file="banniere.jsp" %>
	<form action="inscription" method="post">
		Nom : <input type="text" name="nom" value="<%=request.getParameter("nom")%>"/>
		<br>
		Prénom : <input type="text" name="prenom" value="<%=request.getParameter("prenom")%>"/>
		<br>
		Mot de passe : <input type="password" name="motDePasse" value="<%=request.getParameter("password")%>"/>
		<br>
		Numéro, rue : <input type="text" name="numeroRue" value="<%=request.getParameter("text")%>"/>
		<br>
		Ville : <input type="text" name="ville" value="<%=request.getParameter("ville")%>"/>
		<br>
		Montant initial du compte : <input type="number" name="montant" value="<%=request.getParameter("number")%>"/>
		<br>
		<input type="submit" value="Entrer"/>
	</form>
	
	<%
		if (erreur != null) {
	%>
	<p class="error">
		<%=erreur %>
	</p>
	<%
	    }
	%>
</body>
</html>