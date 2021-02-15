
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connection</title>
</head>
<body>
	<%@include file="banniere.jsp" %>
	<form action="connection" method="post">
		Numéro client : <input type="number" name="numeroClient"/>
		Mot de passe : <input type="password" name="motDePasse"/>
		<input type="submit" value="Entrer"/>
	</form>
</body>
</html>