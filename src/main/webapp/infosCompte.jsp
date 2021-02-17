<%@page import="exception.CompteInexistantException"%>
<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<%@ page import="entity.Virement"%>
<%@ page import="entity.Virement.TypeVirement"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Informations compte</title>
	<link rel="stylesheet" href="styles.css">
	<link rel="stylesheet" href="styleBanniere.css">
</head>
<body>

	<%@include file="banniere.jsp" %>
	<%
	Long numeroCompte = Long.parseLong(request.getParameter("numeroCompte"));
	boolean aVous = true;
	Compte c = null;
	try {
	    c = client.getCompte(numeroCompte);
	} catch (CompteInexistantException e) {
	    aVous = false;
	%>
	<p class="error">
		Vous ne possédez pas un tel compte.
	</p>
	<%
	}
	if (aVous) {
		out.println("Compte " + numeroCompte + " de " + client.getNom() + " " + client.getPrenom() + "\n");
	
	%>
	<br>
	<br>
	
	<%
		out.println("Solde : " + c.getMontant());
	%>
	<br>
	<br>
	<%
		out.println("Historique des transactions : ");
	%>
	<br>
	<%
		for (Virement v : c.getHistoriqueVirement()) {
			out.println(v.getType() + " " + v.getDate() + " " + v.getMontant() + " " );
			if(v.getDebiteur() != null) {
				out.println(v.getDebiteur().getNumeroCompte() + " " );
			} else {
				out.println("null ");
			}
			if(v.getCrediteur() != null) {
				out.println(v.getCrediteur().getNumeroCompte());
			}else {
				out.println("null");
			}
		%>
		<br>
		<%
		}	
	%>
	<br>
	<form action="suppressionHistorique" method="post">
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input type="submit" value="Supprimer l'historique"/>
	</form>
	<br>
	<br>
	<fieldset>
		<legend>Effectuer un mouvement bancaire</legend>
		<br>
		<fieldset>
			<legend>Transaction vers l'extérieur</legend>
			<form action="virement" method="post">
				<input type="radio" name="type" value="debit">
  				<label for="male">Débit</label><br>
  				
				<input type="radio" name="type" value="credit">
				<label for="female">Crédit</label><br>
				
				Montant : <input type="number" name="montant" step=0.01 min="0"/>
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<br><br>
				<input type="submit" value="Entrer">
			</form>
		<br>
		</fieldset>
		<br>
		<fieldset>
			<legend>Transaction entre comptes</legend>
			<form action="virement" method="post">
				Montant : <input type="number" name="montant" step=0.01 min="0"/><br>
				Numéro du compte à crédité : <input type="text" name="compteCredite"/>
				<input type="hidden" name="type" value="virement" />
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<br><br>
				<input type="submit" value="Entrer">
			</form>
		</fieldset>
	</fieldset>
	<br>
	<br>
	<form action="fermerCompte" method="post">
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input type="submit" value="Fermer ce compte"/>
	</form>
	<br>
	
	<form action="comptes.jsp" method="get">
		<input type="submit" value="Retour aux comptes"/>
	</form>
	<br>
	<form action="deconnection" method="post">
		<input type="submit" value="Deconnection"/>
	</form>
	<%
		if (erreur != null) {
	%>
	<p class="error">
		<%=erreur %>
	</p>
	<%
	    }
	}
	%>
</body>
</html>