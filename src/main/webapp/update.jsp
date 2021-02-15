
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modification informations</title>
</head>
<body>
	<%@include file="banniere.jsp" %>
	<form action="update" method="post">
		Nom : <input type="text" name="nom"/>
		<br>
		Prénom : <input type="text" name="prenom"/>
		<br>
		Mot de passe : <input type="password" name="motDePasse"/>
		<br>
		Numéro, rue : <input type="text" name="numeroRue"/>
		<br>
		Ville : <input type="text" name="ville"/>
		<br>
		<input type="submit" value="Entrer"/>
	</form>
</body>
</html>