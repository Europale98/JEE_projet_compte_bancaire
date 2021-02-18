<%@page import="exception.CompteInexistantException"%>
<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<%@ page import="entity.Virement"%>
<%@ page import="entity.Virement.TypeVirement"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Mon compte <%=request.getParameter("numeroCompte") %></title>

	<link rel="stylesheet" href="styleBanniere.css">
	<link rel="stylesheet" href="stylesInfosCompte.css">

</head>
<body>

	<%@include file="banniere.jsp" %>
	<%
	if(client==null){
	%>
	<fieldset class="error">
		<legend>Erreur</legend>
		<p>Vous n'êtes pas connecté.</p>
	</fieldset>
	<%
	}else {
	    
	Long numeroCompte = Long.parseLong(request.getParameter("numeroCompte"));
	boolean aVous = true;
	Compte c = null;
	try {
	    c = client.getCompte(numeroCompte);
	} catch (CompteInexistantException e) {
	    aVous = false;
	%>
	<fieldset class="error">
		<legend>Erreur</legend>
		<p>Vous ne possédez pas un tel compte.</p>
	</fieldset>
	<%
	}
	if (aVous) {
	%>
	<h2 class="inline">Compte <%=numeroCompte%> de <%=client.getNom()%> <%=client.getPrenom()%></h2>
	<h2 class="float-left"><b>Solde :</b> <%=c.getMontant()%> &euro;</h2>
	<br/>
	<%
		if (erreur != null) {
	%>
	<fieldset class="error">
		<legend>Erreur</legend>
		<p><%=erreur%></p>
	</fieldset>
	<br>
	<%
		}
	%>
	<fieldset id="formulaire">
		<legend class="cursive">Effectuer un mouvement bancaire</legend>
		<fieldset>
			<legend class="cursive2">Transaction vers l'extérieur</legend>
			<form action="virement" method="post">
				<div class="block"><div class="block_int"><input type="radio" name="type" value="debit">
  				<label for="debit">Débit</label></div>
  				<div class="block_int"><input type="radio" name="type" value="credit">
				<label for="credit">Crédit</label></div></div>
				
				<div class="block"><label for="montant">Montant : </label><input type="number" name="montant" placeholder="Montant" step=0.01 min="0"/></div>
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<div class="block_submit"><input class="lf--submit" type="submit" value="Entrer"></div>
			</form>
		</fieldset>
		<fieldset>
			<legend class="cursive2">Transaction entre comptes</legend>
			<form action="virement" method="post">
				<div class="block"><label for="compteCredite">Numéro du compte à créditer : </label><input type="text" name="compteCredite" placeholder="Numéro de compte"/></div>
				<div class="block"><label for="montant">Montant : </label><input type="number" name="montant" placeholder="Montant" step=0.01 min="0"/></div>
				<input type="hidden" name="type" value="virement" />
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<div class="block_submit"><input class="lf--submit" type="submit" value="Entrer"></div>
			</form>
		</fieldset>
	</fieldset>
	<form class="inline float-left" action="fermerCompte" method="post">
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input class="lf--submit suppr" type="submit" value="Fermer ce compte"/>
	</form>
	<form class="float-left" action="suppressionHistorique" method="post">
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input class="lf--submit suppr" type="submit" value="Supprimer l'historique"/>
	</form>
	<br/>
	<br/>
	<br/>
	<fieldset id="virement">
		<legend class="cursive3">Historique des transactions</legend>
		<div id="table-virement">
		<table style="width:100%">
			<thead><tr>
			<th class="tab-date">Date</th> 
			<th class="tab-type">Type</th>
			<th class="tab-ori">Origine/Destination</th>
			<th class="tab-montant">Montant</th>
			</tr></thead>
		<%
			Long debiteur;
			Long crediteur;
			for (Virement v : c.getHistoriqueVirement()) {
			    debiteur = (v.getDebiteur() == null)?null:v.getDebiteur().getNumeroCompte();
				crediteur = (v.getCrediteur() == null)?null:v.getCrediteur().getNumeroCompte();
				%>
				<tr>
				<td class="tab-date"><%=v.getDate()%></td>
				<td class="tab-type"><%=v.getType()%></td>
				<td class="tab-ori">
					<%
					if (debiteur == null) {
					    out.println("Extérieur");
					} else if (crediteur == null) {
					    out.println("Extérieur");
					} else if (debiteur.equals(c.getNumeroCompte())) {
					    out.println(crediteur);
					} else {
					    out.println(debiteur);
					}
					%>
				</td>
				<%
				out.println("<td class=\"tab-montant ");
				if (debiteur!=null && debiteur.equals(c.getNumeroCompte())) {
				    out.println("debit\"> -");
				} else {
				    out.println("credit\"> +");
				}
				out.println(v.getMontant()+"</td>");
				%>
				</tr>
			<%
			}	
		%>
		</table>
		</div>
	</fieldset>
	<%
		}
	}
	%>
</body>
</html>