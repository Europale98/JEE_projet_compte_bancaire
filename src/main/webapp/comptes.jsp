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
<%	if(client==null) {
	%>
	<fieldset class="error">
		<legend>Erreur</legend>
		<p>Vous n'êtes pas connecté.</p>
	</fieldset>
	<%
	} else {
	%>
	<table>
		<tr><td id="new">
		<h2> Compte(s) de <%=client.getNom()%> <%=client.getPrenom()%></h2>
		<br/><br/>
		<form action="creerCompte" method="post">
			<fieldset>
				<legend class="cursive">Créer un nouveau compte</legend>

				<div class="box"><label for="montant"><h3 class="mb-0"><b>Montant initial :</b></h3></label></div>
				<div class="box"><input type="number" name="montant" placeholder="Montant" step="0.01" min="0"><br></div>
				<br> <input  class="lf--submit" type="submit" value="Entrer">
			</fieldset>
		</form>
		<%
			if (erreur != null) {
		%>
		<fieldset class="error">
			<legend>Erreur</legend>
			<p><%=erreur%></p>
		</fieldset>
		<%
			}
		%>
		</td>
		<td>
		<div id="comptes">
		<%
			for (Compte c : client.getComptes()) {
		%>
		<form action="infosCompte.jsp" method="get">
			<fieldset>
				<legend class="cursive">
					Compte numéro
					<%=c.getNumeroCompte()%>
				</legend>
				<input type="hidden" name="numeroCompte" value=<%=c.getNumeroCompte()%> />
				<table>
				<tr><td><h3 class="mb-0"><b>Solde :</b> <%=c.getMontant()%> </h3></td>
				<td><input class="lf--submit" type="submit" value="Détails du compte" /></td>
				</tr>
				</table>
			</fieldset>
		</form>
		<br>
		<%
			}
		%>
		</div>
		</td>
		</tr>
		</table>
		<%
	}
		%>
	
</body>
</html>