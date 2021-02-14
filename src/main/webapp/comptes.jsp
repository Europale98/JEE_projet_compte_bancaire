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
	<%
	for (Compte c : client.getComptes()) {
		out.println("Numéro du compte : " + c.getNumeroCompte() + " Solde : " + c.getMontant() + "\n");
		%>
		<form action="infosCompte.jsp" method="get">
			<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
			<input type="submit" value="Infos"/>
		</form>
		<br>
	<%
	}
	%>
	<form action="creerCompte" method="post">
		Montant initial du nouveau compte : <input type="number" name="montant"/>
		<br>
		<input type="submit" value="Creer un nouveau compte"/>
	</form>
	<br>
	<form action="deconnection" method="post">
		<input type="submit" value="Deconnection"/>
	</form>
	
</body>
</html>