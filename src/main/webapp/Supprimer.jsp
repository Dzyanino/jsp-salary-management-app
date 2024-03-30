<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Supprimer</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/vuetify.min.css" />
		<link rel="stylesheet" href="css/others.css" />
	</head>

	<body>
		<form method="post" action="supprimer">
			<input class="" type="text" name="numEns" value="<% request.getParameter("numEns") %>"></input>
			<input class="d-none" type="text" name="vraiment" value="oui"></input>
			<input type="submit" value="Confirmer" class="btn btn-danger">
			<a href="accueil" class="btn -btn-secondary">Annuler</a>
		</form>
	</body>
</html>