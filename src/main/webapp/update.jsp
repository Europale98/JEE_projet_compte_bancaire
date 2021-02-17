
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modification informations</title>
<link rel="stylesheet" href="stylesUpdate.css">
<link rel="stylesheet" href="styleBanniere.css">
</head>
<body>
	<%@include file="banniere.jsp"%>
	<div class="container">
		<form action="update" method="post">
			<fieldset>
				<legend class="cursive">Modifier vos informations
					personnelles</legend>
				<h3><b>Nom : </b><input type="text" name="nom" value="<%=client.getNom()%>" /></h3>
				<h3><b>Prénom :</b> <input type="text" name="prenom"
					value="<%=client.getPrenom()%>" /></h3>
					<h3><b>Mot de passe :</b> <input
					type="password" name="motDePasse" /></h3>
					<h3><b>Numéro, rue : <b></b><input
					type="text" name="numeroRue"
					value="<%=client.getAdresse().getNumeroRue()%>" /></h3>
					<h3><b>Ville :</b> <input type="text" name="ville"
					value="<%=client.getAdresse().getVille()%>" /></h3> 
				<input class="lf--submit" type="submit" value="Entrer">
			</fieldset>
		</form>
	</div>
	<%
		if (erreur != null) {
	%>
	<p class="error">
		<%=erreur%>
	</p>
	<%
		}
	%>
</body>
</html>