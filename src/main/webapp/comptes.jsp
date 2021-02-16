<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Comptes client</title>
	<link rel="stylesheet" href="styles.css">
	<link rel="stylesheet" href="styleBanniere.css">
</head>
<body>

	<%@include file="banniere.jsp" %>
	<%
	out.println("Compte(s) de " + client.getNom() + " " + client.getPrenom());
	%>
	<br>
	<br>
	<%
	for (Compte c : client.getComptes()) {
		
		%>
		<form action="infosCompte.jsp" method="get">
			<fieldset>
				<legend>Compte num�ro <%=c.getNumeroCompte()%></legend>
				<%
				out.println(" Solde : " + c.getMontant());
				%>
				<br>
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<input type="submit" value="D�tails du compte"/>
			</fieldset>
		</form>
		<br>
	<%
	}
	%>
	<form action="creerCompte" method="post">
		<fieldset>
			<legend>Cr�er un nouveau compte</legend>
			
			<label for="montant">Montant initial :</label><br>
			<input type="number" name="montant" step="0.01" min="0"><br><br>
		    
		    <input type="submit" value="Entrer">
		</fieldset>
	</form>
	<br>
	<form action="deconnection" method="post">
		<input type="submit" value="D�connection"/>
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