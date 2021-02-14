<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<%@ page import="service.CompteService"%>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Informations compte</title>
</head>
<body>

	<% Client client = (Client) session.getAttribute("client");
	Long numeroCompte = Long.parseLong(request.getParameter("numeroCompte"));
	out.println("Compte " + numeroCompte + " de " + client.getNom() + " " + client.getPrenom() + "\n");
	
	%>
	<br>
	<%
	AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
    appContext.scan("service");
    appContext.scan("dao");
    appContext.scan("entity");
    appContext.scan("controller");
    appContext.refresh();
	CompteService cs = (CompteService) appContext.getBean("compteService");
	Compte c = cs.getCompteByNumero(numeroCompte);
	out.println("Solde : " + c.getMontant() + "\n");
	%>
	<form action="virement" method="post">
		Montant : <input type="number" name="montant"/>
		<input type="submit" value="Effectuer un mouvement bancaire"/>
	</form>
	<form action="fermerCompte" method="post">
		<input type="submit" value="Fermer ce compte"/>
	</form>
	<br>
	<form action="deconnection" method="post">
		<input type="submit" value="Deconnection"/>
	</form>
	
</body>
</html>