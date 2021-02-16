<%@page import="exception.CompteInexistantException"%>
<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<%@ page import="entity.Virement"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Détails compte</title>

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
	if (aVous) {%>
	
	Compte <%=numeroCompte%> de <%=client.getNom()%> <%=client.getPrenom()%>
	<br>
	<br>
	Solde : <%=c.getMontant()%>
	<br>
	<br>
	<table style="width:100%">
		<caption><b>Historique des transactions</b></caption>
		<tr>
		<th>Type</th>
		<th>Date</th> 
		<th>Montant</th>
		<th>Compte débiteur</th>
		<th>Compte créditeur</th>
		</tr>
	<%
		for (Virement v : c.getHistoriqueVirement()) {
			%>
			<tr>
			<td><%=v.getType()%></td>
			<td><%=v.getDate()%></td>
			<td><%=v.getMontant()%></td>
			<td>
				<%if(v.getDebiteur() != null) {
					out.println(v.getDebiteur().getNumeroCompte() + " " );
				} else {
					out.println("Extérieur");
				} %>
			</td>
			<td>
				<%if(v.getCrediteur() != null) {
					out.println(v.getCrediteur().getNumeroCompte());
				}else {
					out.println("Extérieur");
				} %>
			</td>
			</tr>
		<%
		}	
	%>
	</table>
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
  				<label for="debit">Débit</label><br>
  				
				<input type="radio" name="type" value="credit">
				<label for="credit">Crédit</label><br>
				
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
				Numéro du compte à créditer : <input type="text" name="compteCredite"/>
				<input type="hidden" name="type" value="virement" />
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<br>
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