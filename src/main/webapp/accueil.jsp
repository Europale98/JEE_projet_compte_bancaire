<%@ page import="entity.Client"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>
	<% Client client = (Client) session.getAttribute("client");
	out.println("Bienvenue " + client.getNom() + " " + client.getPrenom());
	%>
	<a href="update.jsp"><input type="button" value="Modification infos"></a>
	<a href="accueil.jsp"><input type="button" value="Deconnection"></a>
</body>
</html>