<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Comptes client</title>
</head>
<body>

	<% Client client = (Client) session.getAttribute("client");
	out.println("Compte(s) de " + client.getNom() + " " + client.getPrenom());
	%>
	<br>
	<br>
	<%
	for (Compte c : client.getComptes()) {
		
		%>
		<form action="infosCompte.jsp" method="get">
			<fieldset>
				<legend>Compte numéro <%=c.getNumeroCompte()%></legend>
				<%
				out.println(" Solde : " + c.getMontant());
				%>
				<br>
				<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
				<input type="submit" value="Détails du compte"/>
			</fieldset>
		</form>
		<br>
	<%
	}
	%>
	<form action="creerCompte" method="post">
		<fieldset>
			<legend>Créer un nouveau compte</legend>
			
			<label for="montant">Montant initial :</label><br>
			<input type="number" name="montant"><br><br>
		    
		    <input type="submit" value="Entrer">
		</fieldset>
	</form>
	<br>
	<form action="deconnection" method="post">
		<input type="submit" value="Déconnection"/>
	</form>
	
</body>
</html>