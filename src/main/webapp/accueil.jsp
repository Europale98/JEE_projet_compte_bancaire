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
	    	<h3>Bienvenue <%=client.getNom()%> <%=client.getPrenom()%></h3>
	    	<p>
	    	Votre conseiller bancaire n'est pas disponible pour le moment
	    	</p>
	    	<br>
	    	<fieldset>
				<legend>Vos informations personnelles</legend>
				Numéro client : <%=client.getNumeroClient()%>
				<br>
				Adresse : <%=client.getAdresse()%>
				<br>
			</fieldset>	
	
	
	<%
	    } else {%>
	    
	<h2 style="text-align:center;">Bienvenue sur notre site de banque en ligne</h2>
	
	<p>
		Déja client ?
		<br>
		<span class="tabulation"></span>Connectez-vous pour pouvoir accéder à vos comptes
	</p>
	<p>
		Futur client ?
		<br>
		<span class="tabulation"></span>Inscrivez-vous dès maintenant pour faire partie de notre heureuse clientèle
	</p>
	<%
	    }
	%>

</body>
</html>