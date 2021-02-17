<%@page import="exception.CompteInexistantException"%>
<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<%@ page import="entity.Virement"%>
<%@ page import="entity.Virement.TypeVirement"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>D�tails compte</title>

	<link rel="stylesheet" href="styleBanniere.css">
	<link rel="stylesheet" href="stylesInfosCompte.css">

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
		Vous ne poss�dez pas un tel compte.
	</p>
	<%
	}
	if (aVous) {%>
	
	<h2>Compte <%=numeroCompte%> de <%=client.getNom()%> <%=client.getPrenom()%></h2>
	<h3><b>Solde :</b> <%=c.getMontant()%></h3>
	<br>
	<table style="width:100%">
		<caption><b>Historique des transactions</b></caption>
		<tr>
		<th>Type</th>
		<th>Date</th> 
		<th>Montant</th>
		<th>Compte d�biteur</th>
		<th>Compte cr�diteur</th>
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
					out.println("Ext�rieur");
				} %>
			</td>
			<td>
				<%if(v.getCrediteur() != null) {
					out.println(v.getCrediteur().getNumeroCompte());
				}else {
					out.println("Ext�rieur");
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
		<input class="lf--submit" type="submit" value="Supprimer l'historique"/>
	</form>
	<br>
	<br>
	<fieldset>
		<legend class="cursive">Effectuer un mouvement bancaire</legend>
		<br>
		<fieldset>
			<legend class="cursive2">Transaction vers l'ext�rieur</legend>
			<form action="virement" method="post">
				<input type="radio" name="type" value="debit">
  				<label for="debit">D�bit</label><br>
  				
				<input type="radio" name="type" value="credit">
				<label for="credit">Cr�dit</label><br>
				
				Montant : <input type="number" name="montant" step=0.01 min="0"/>
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<br><br>
				<input class="lf--submit" type="submit" value="Entrer">
			</form>
		</fieldset>
		<br>
		<fieldset>
			<legend class="cursive2">Transaction entre comptes</legend>
			<form action="virement" method="post">
				Montant : <input type="number" name="montant" step=0.01 min="0"/><br>
				Num�ro du compte � cr�diter : <input type="text" name="compteCredite"/>
				<input type="hidden" name="type" value="virement" />
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<br>
				<input class="lf--submit" type="submit" value="Entrer">
			</form>
		</fieldset>
	</fieldset>
	<br>
	<br>
	<form action="fermerCompte" method="post">
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input class="lf--submit" type="submit" value="Fermer ce compte"/>
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