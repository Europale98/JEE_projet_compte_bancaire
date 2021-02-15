<%@ page import="entity.Client"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>
	<%@include file="banniere.jsp"%>
	<%
	    if (client != null) {
	    out.println("Bienvenue " + client.getNom() + " " + client.getPrenom());
	%>
	<br>
	<a href="update.jsp"><input type="button"
		value="Modification infos client"></a>
	<a href="comptes.jsp"><input type="button" value="Infos compte(s)"></a>
	<form action="deconnection" method="post">
		<input type="submit" value="Deconnection" />
	</form>
	<%
	    } else {
	out.println("Bienvenue");
	%>
	<a href="connection.jsp"><input type="button" value="Connection"></a>
	<a href="inscription.jsp"><input type="button" value="Inscription"></a>
	<%
	    }
	%>

</body>
</html>