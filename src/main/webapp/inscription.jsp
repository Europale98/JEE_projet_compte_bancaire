
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
<link rel="stylesheet" href="stylesInscription.css">
<link rel="stylesheet" href="styleBanniere.css">
</head>
<body>
	<%@include file="banniere.jsp"%>
	<form action="inscription" method="post">
		<div class="container">
			<fieldset>
				<legend class="cursive">S'inscrire</legend>
				<br> <label for="nom">Nom :</label><br> <input type="text"
					name="nom" placeholder="Nom" required
					value="<%=(request.getParameter("nom") == null ? "" : request.getParameter("nom"))%>" /><br>

				<label for="prenom">Prénom :</label><br> <input type="text"
					name="prenom" placeholder="Prénom" required
					value="<%=(request.getParameter("prenom") == null ? "" : request.getParameter("prenom"))%>" /><br>

				<label for="motDePasse">Mot de passe :</label><br> <input
					type="password" name="motDePasse" placeholder="Mot de passe" required
					value="<%=(request.getParameter("motDePasse") == null ? "" : request.getParameter("motDePasse"))%>" /><br>

				<label for="numeroRue">Numéro, rue :</label><br> <input
					type="text" name="numeroRue" placeholder="Numéro, Rue" required
					value="<%=(request.getParameter("numeroRue") == null ? "" : request.getParameter("numeroRue"))%>" /><br>

				<label for="ville">Ville :</label><br> <input type="text"
					name="ville" placeholder="Ville" required
					value="<%=(request.getParameter("ville") == null ? "" : request.getParameter("ville"))%>" /><br>

				<label for="montant">Montant initial du compte :</label><br> <input
					type="number" name="montant" step="0.01 min="
					0"" placeholder="Montant initial" required
					value="<%=(request.getParameter("montant") == null ? "" : request.getParameter("montant"))%>" /><br>
				<input type="submit" value="Entrer" class="lf--submit">
			</fieldset>
		</div>
	</form>

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