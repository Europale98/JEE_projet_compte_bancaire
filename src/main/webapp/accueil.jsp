<%@ page import="entity.Client"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
<link rel="stylesheet" href="styleBanniere.css">
<link rel="stylesheet" href="stylesAccueil.css">

</head>
<body>
	<%@include file="banniere.jsp"%>
	<%
	    if (client != null) {%>
	<h2>
		Bienvenue
		<%=client.getNom()%>
		<%=client.getPrenom()%></h2>
	<p>Votre conseiller bancaire n'est pas disponible pour le moment</p>
	<br>
	<fieldset>
		<legend class="cursive">Mes informations personnelles</legend>
		<div class="col-md-8">
			<div class="card mb-3">
				<div class="card-body">
					<div class="row">
						<div class="col-sm-3">
							<h3 class="mb-0">Numéro client :</h3>
						</div>
						<div class="col-sm-9 text-secondary"><%=client.getNumeroClient()%></div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<h3 class="mb-0">Adresse :</h3>
						</div>
						<div class="col-sm-9 text-secondary"><%=client.getAdresse()%></div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<h3 class="mb-0">Nombre de comptes :</h3>
						</div>
						<div class="col-sm-9 text-secondary"><%=client.getComptes().size()%></div>
					</div>
					<hr>
					<div class="row">
					</div>
				</div>
			</div>
	</fieldset>


	<%
	    } else {%>

	<h2 style="text-align: center;">Bienvenue sur notre site de banque
		en ligne</h2>


	<p>
	<div class="cursive">Déja client ?</div>
	<span class="tabulation"></span>Connectez-vous pour pouvoir accéder à
	vos comptes
	</p>
	<p>
	<div class="cursive">Futur client ?</div>
	<span class="tabulation"></span>Inscrivez-vous dès maintenant pour
	faire partie de notre heureuse clientèle
	</p>

	<%
	    }
	%>
</body>
</html>