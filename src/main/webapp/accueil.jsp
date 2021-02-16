<%@ page import="entity.Client"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Accueil</title>
	<link rel="stylesheet" href="styles.css">
	<style> 
	.tabulation { 
	display: inline-block; 
	margin-left: 40px; 
	} 
	</style> 
</head>
<body>
	<%@include file="banniere.jsp"%>
	<%
	    if (client != null) {%>
	    	Bienvenue <%=client.getNom()%> <%=client.getPrenom()%>
	<%
	    } else {%>
	    
	<h2 style="text-align:center;">Bienvenue sur notre site de banque en ligne</h2>
	
	<p>
		D�ja client ?
		<br>
		<span class="tabulation"></span>Connectez-vous pour pouvoir acc�der a vos comptes
	</p>
	<p>
		Futur client ?
		<br>
		<span class="tabulation"></span>Inscrivez-vous d�s maintenant pour faire partie de notre heureuse client�le
	</p>
	<%
	    }
	%>

</body>
</html>