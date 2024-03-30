<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>KRama</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/vuetify.min.css" />
		<link rel="stylesheet" href="css/others.css" />
	</head>
	<body class="bg-grey-lighten-4 text-body-1">
		<div class="navbar sticky-top bg-white elevation-2 justify-center justify-sm-start px-8 py-2">
			<ul class="navbar-nav nav-underline flex-column flex-sm-row justify-center justify-sm-start align-center align-sm-start">
				<li class="navbar-brand d-flex text-button align-center mr-sm-16">
					<img alt="management_icon" src="assets/icon.png" height="32" class="pr-4">Sujet N° 11
				</li>
				<li class="nav-item">
					<a class="nav-link active text-overline" href="#">Tableau</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-overline" href="#chart">Chart</a>
				</li>
				<!-- <li class="nav-item">
					<a class="nav-link text-overline" href="https://github.com/Dzyanino">Dzyanino</a>
				</li> -->
			</ul>
		</div>
		<div class="container-fluid pa-2 pa-sm-4 pa-md-8 px-lg-16">
			<div class="table-responsive">
				<a href="Ajouter.jsp" class="btn btn-primary rounded mb-4">Ajouter</a>
				<div class="h-screen overflow-y-scroll rounded elevation-2 bg-white">
					<table class="table table-hover">
						<thead>
							<tr>
								<td class="text-button bg-grey-lighten-3 text-center">#</td>
								<td class="text-button">Nom et Prénom(s)</td>
								<td class="text-button">Nombre d'heures</td>
								<td class="text-button">Taux horaire</td>
								<td class="text-button">Salaire</td>
								<td class="text-center text-button">Actions</td>
							</tr>
						</thead>
						
						<tbody>
							<c:if test="${empty allEnseignant}">
								<tr>
									<td colspan="6" class="text-overline text-center">Vide...</td>
								</tr>
							</c:if>
							<c:if test="${not empty allEnseignant}">
								<c:forEach var="enseignant" items="${allEnseignant}">
									<tr class="text-body-1">
										<td class="bg-grey-lighten-4 text-center">${enseignant.getNumEns()}</td>
										<td>${enseignant.getNom()}</td>
										<td>${enseignant.getNbHeure()}</td>
										<td>${enseignant.getTauxHoraire()}</td>
										<td>${enseignant.getSalaire()}</td>
										<td class="text-center d-flex justify-space-evenly">
											<a href="modifier?numEns=${enseignant.getNumEns()}" class="btn btn-sm btn-warning">Edit.</a>
											<a href="supprimer?numEns=${enseignant.getNumEns()}" class="btn btn-sm btn-danger">Suppr.</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>							
				</div>
				<div class="d-flex align-center justify-center justify-sm-end text-center text-sm-left text-uppercase mt-4 pr-lg-8">
					<p>Salaire minimal : <strong>${min}</strong></p>
					<p class="mx-2 mx-sm-4 mx-md-8 mx-lg-16">Salaire maximal : <strong>${max}</strong></p>
					<p>Total : <strong>${sum}</strong></p>
				</div>
			</div>
			<div class="container mt-16 pt-16">
				<canvas id="chart"></canvas>
			</div>
		</div>
		
		
		<footer class="footer">
		</footer>
		
		<script type="text/javascript" src="css/chart.js"></script>
		<script>
			const dest = document.getElementById("chart");
			
			new Chart(dest, {
				type: "bar",
				data: {
					labels: ["Salaire minimum", "Salaire maximum", "Montant total"],
					datasets: [{
						label: "",
						data: [${min}, ${max}, ${sum}],
						backgroundColor: [
							"#ffb3b3",
							"#66ff99",
							"#80ccff"
						],
						borderColor: [
							"#ff0000",
							"#00e64d",
							"#0099ff"
						],
						borderWidth: 3
					}]
				},
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
		</script>
	</body>
</html>