
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Modification informations</title>
	<link rel="stylesheet" href="styles.css">
	<link rel="stylesheet" href="styleBanniere.css">
</head>
<body>
	<%@include file="banniere.jsp" %>
	<form action="update" method="post">
		Nom : <input type="text" name="nom" value="<%=client.getNom()%>"/>
		<br>
		Pr�nom : <input type="text" name="prenom" value="<%=client.getPrenom()%>"/>
		<br>
		Mot de passe : <input type="password" name="motDePasse"/>
		<br>
		Num�ro, rue : <input type="text" name="numeroRue" value="<%=client.getAdresse().getNumeroRue()%>"/>
		<br>
		Ville : <input type="text" name="ville" value="<%=client.getAdresse().getVille()%>"/>
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