<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifier</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/vuetify.min.css" />
		<link rel="stylesheet" href="css/others.css" />
	</head>

	<body class="bg-grey-lighten-4 text-body-1">
		<div class="container justify-center align-center my-16">
			<form method="post" action="modifier" class="d-flex flex-column">
					<input class="d-none" type="text" name="numEns" value="${oneEnseignant.getNumEns()}"></input>
					<input class="border rounded bg-white pa-4 my-2" type="text" name="nom" value="${oneEnseignant.getNom()}"></input>
					<input class="border rounded bg-white pa-4 my-2" type="number" name="nbHeure" value="${oneEnseignant.getNbHeure()}"></input>
					<input class="border rounded bg-white pa-4 my-2" type="number" name="tauxHoraire" value="${oneEnseignant.getTauxHoraire()}"></input>
					<input class="btn btn-primary text-white px-4 py-2 mt-8 align-self-center" type="submit" value="Confirmer">
					<a href="accueil" class="btn btn-secondary px-6 py-2 mt-8 align-self-center">Annuler</a>
			</form>
		</div>
	</body>
</html>