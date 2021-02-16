
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Inscription</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%@include file="banniere.jsp" %>
	<form action="inscription" method="post">
		<fieldset>
		    <legend>S'inscrire</legend>
		    
		    <label for="nom">Nom :</label><br>
		    <input type="text" name="nom"
			value="<%=(request.getParameter("nom")==null?"":request.getParameter("nom"))%>"/><br>
		    
		    <label for="prenom">Prénom :</label><br>
		    <input type="text" name="prenom"
			value="<%=(request.getParameter("prenom")==null?"":request.getParameter("prenom"))%>"/><br>
		    
		    <label for="motDePasse">Mot de passe :</label><br>
		    <input type="password" name="motDePasse"
			value="<%=(request.getParameter("motDePasse")==null?"":request.getParameter("motDePasse"))%>"/><br>
		    
		    <label for="numeroRue">Numéro, rue :</label><br>
		    <input type="text" name="numeroRue"
			value="<%=(request.getParameter("numeroRue")==null?"":request.getParameter("numeroRue"))%>"/><br>
		    
		    <label for="ville">Ville :</label><br>
		    <input type="text" name="ville"
			value="<%=(request.getParameter("ville")==null?"":request.getParameter("ville"))%>"/><br>
		    
		    <label for="montant">Montant initial du compte :</label><br>
		    <input type="number" name="montant" step="0.01 min="0""
			value="<%=(request.getParameter("montant")==null?"":request.getParameter("montant"))%>"/><br><br>
		    
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