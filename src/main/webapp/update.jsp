
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
	<%if(client==null){
	%>
	<fieldset class="error">
		<legend>Erreur</legend>
		<p>Vous n'êtes pas connecté.</p>
	</fieldset>
	<%
	}else {
	    %>
 	<%
		if (erreur != null) {
	%>
	<fieldset class="error">
		<legend>Erreur</legend>
		<p><%=erreur%></p>
	</fieldset>
	<br/>
	<%
		}
	%>
	<div class="container">
		<form action="update" method="post">
			<fieldset>
				<legend class="cursive">Modifier mes informations personnelles</legend>
				<table>
				<tr><td class="label"><label><b>Nom : </b></label></td><td><input type="text" name="nom" placeholder="Nom" value="<%=client.getNom()%>"/></td></tr>
				<tr><td class="label"><label><b>Prénom : </b></label></td><td><input type="text" name="prenom" placeholder="Prénom"
					value="<%=client.getPrenom()%>" /></td></tr>
				<tr><td class="label"><label><b>Mot de passe : </b></label></td><td><input
					type="password" name="motDePasse" placeholder="Mot de passe"/></td></tr>
				<tr><td class="label"><label><b>Numéro, rue : </b></label></td><td><input
					type="text" name="numeroRue" placeholder="Numéro, rue"
					value="<%=client.getAdresse().getNumeroRue()%>" /></td></tr>
				<tr><td class="label"><label><b>Ville : </b></label></td><td><input type="text" name="ville" placeholder="Ville"
					value="<%=client.getAdresse().getVille()%>" /></td></tr>
				<tr><td colspan="2"><input class="lf--submit" type="submit" value="Entrer"></td></tr>
				</table>
			</fieldset>
		</form>
		<br/>
		<form action="suppressionClient" method="post">
			<fieldset>
				<legend class="cursive">Supprimer mon compte client</legend>
				<table>
				<tr><td class="label2"><label><b>Confirmer le mot de passe : </b></label></td><td><input
					type="password" name="motDePasse" placeholder="Mot de passe"/></td></tr>
				<tr><td colspan="2"><input class="lf--submit" type="submit" value="Supprimer mon compte client"></td></tr>
				</table>
			</fieldset>
		</form>
	</div>
	<%
	}
	%>
</body>
</html>