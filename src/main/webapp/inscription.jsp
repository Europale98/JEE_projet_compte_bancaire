
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
</head>
<body>
	<form action="inscription" method="post">
		<fieldset>
		    <legend>S'inscrire</legend>
		    
		    <label for="nom">Nom :</label><br>
		    <input type="text" name="nom"><br>
		    
		    <label for="prenom">Prénom :</label><br>
		    <input type="text" name="prenom"><br>
		    
		    <label for="motDePasse">Mot de passe :</label><br>
		    <input type="text" name="motDePasse"><br>
		    
		    <label for="numeroRue">Numéro, rue :</label><br>
		    <input type="text" name="numeroRue"><br>
		    
		    <label for="ville">Ville :</label><br>
		    <input type="text" name="ville"><br>
		    
		    <label for="montant">Montant initial du compte :</label><br>
		    <input type="number" name="montant"><br><br>
		    
		    <input type="submit" value="Entrer">
		</fieldset>
	</form>
</body>
</html>