<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mes comptes</title>
<link rel="stylesheet" href="stylesComptes.css">
<link rel="stylesheet" href="styleBanniere.css">
</head>
<body>

	<%@include file="banniere.jsp"%>

	<h2>
		Compte(s) de
		<%=client.getNom()%>
		<%=client.getPrenom()%></h1>
		<br>
		<%
			for (Compte c : client.getComptes()) {
		%>
		<form action="infosCompte.jsp" method="get">
			<fieldset>
				<legend class="cursive">
					Compte numéro
					<%=c.getNumeroCompte()%></legend>
				<h3 class="mb-0"><b>Solde :</b> <%=c.getMontant()%> </h3>
				<input type="hidden" name="numeroCompte"
					value=<%=c.getNumeroCompte()%> /> <input class="lf--submit" type="submit"
					value="Détails du compte" />
			</fieldset>
		</form>
		<br>
		<%
			}
		%>
		<form action="creerCompte" method="post">
			<fieldset>
				<legend class="cursive">Créer un nouveau compte</legend>

				<div class="box"><label for="montant"><h3 class="mb-0"><b>Montant initial :</b></h3></label></div>
				<div class="box"><input type="number" name="montant" step="0.01" min="0"><br></div>
				<br> <input  class="lf--submit" type="submit" value="Entrer">
			</fieldset>
		</form>
		<br>
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