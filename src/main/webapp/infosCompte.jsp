<%@ page import="entity.Client"%>
<%@ page import="entity.Compte"%>
<%@ page import="entity.Virement"%>
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
	out.println("Solde : " + c.getMontant());
	%>
	<br>
	<br>
	<%
	out.println("Historique des transactions : ");
	%>
	<br>
	<%
	for (Virement v : c.getHistoriqueVirement()) {
		out.println(v.getDate() + " " + v.getMontant() + " " );
		if(v.getDebiteur() != null) {
			out.println(v.getDebiteur().getNumeroCompte() + " " );
		} else {
			out.println("null ");
		}
		if(v.getCrediteur() != null) {
			out.println(v.getCrediteur().getNumeroCompte());
		}else {
			out.println("null");
		}
		%>
		<br>
		<%
	}	
	%>
	<br>
	<form action="suppressionHistorique" method="post">
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input type="submit" value="Supprimer l'historique"/>
	</form>
	<br>
	<br>
	<%
	out.println("Effectuer un mouvement bancaire :");%>
	<br>
	<form action="virement" method="post">
		Montant : <input type="number" name="montant"/>
		<input type="hidden" name="type" value="debit" />
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input type="submit" value="Debit"/>
	</form>
	<br>
	<form action="virement" method="post">
		Montant : <input type="number" name="montant"/>
		<input type="hidden" name="type" value="credit" />
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input type="submit" value="Credit"/>
	</form>
	<br>
	<form action="virement" method="post">
		Montant : <input type="number" name="montant"/>
		Numéro du compte à crédité : <input type="text" name="compteCredite"/>
		<input type="hidden" name="type" value="virement" />
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input type="submit" value="Virement"/>
	</form>
	<br>
	<br>
	<form action="fermerCompte" method="post">
		<input type="hidden" name="numeroCompte" value= <%=c.getNumeroCompte()%> />
		<input type="submit" value="Fermer ce compte"/>
	</form>
	<br>
	<form action="deconnection" method="post">
		<input type="submit" value="Deconnection"/>
	</form>
	
</body>
</html>