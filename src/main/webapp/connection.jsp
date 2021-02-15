
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
		Numéro client : <input type="number" name="numeroClient" 
			value="<%=(request.getParameter("numeroClient")==null?"":request.getParameter("numeroClient"))%>"/> 
		Mot de passe : <input type="password" name="motDePasse" 
			value="<%=(request.getParameter("motDePasse")==null?"":request.getParameter("motDePasse"))%>"/>
		<input type="submit" value="Entrer" />
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