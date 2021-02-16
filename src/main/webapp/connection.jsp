
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Connection</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%@include file="banniere.jsp"%>
	<form action="connection" method="post">
		<fieldset>
		    <legend>Se connecter</legend>
		    <label for="numeroClient">Numéro client :</label><br>
		    <input type="number" name="numeroClient"
			value="<%=(request.getParameter("numeroClient")==null?"":request.getParameter("numeroClient"))%>"/><br>
		    <label for="motDePasse">Mot de passe :</label><br>
		    <input type="password" name="motDePasse"
			value="<%=(request.getParameter("motDePasse")==null?"":request.getParameter("motDePasse"))%>"/><br><br>
		    <input type="submit" value="Entrer">
	  </fieldset>
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