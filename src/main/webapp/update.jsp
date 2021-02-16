
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Modification informations</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%@include file="banniere.jsp" %>
	<form action="update" method="post">
		<fieldset>
		    <legend>Modifier vos informations personnelles</legend>
		    Nom : <input type="text" name="nom" value="<%=client.getNom()%>"/>
			<br>
			Prénom : <input type="text" name="prenom" value="<%=client.getPrenom()%>"/>
			<br>
			Mot de passe : <input type="password" name="motDePasse"/>
			<br>
			Numéro, rue : <input type="text" name="numeroRue" value="<%=client.getAdresse().getNumeroRue()%>"/>
			<br>
			Ville : <input type="text" name="ville" value="<%=client.getAdresse().getVille()%>"/>
			<br><br>
		    <input type="submit" value="Entrer">
		</fieldset>
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