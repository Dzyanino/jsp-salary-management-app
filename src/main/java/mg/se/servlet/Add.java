package mg.se.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.se.dao.ConnectionProperties;
import mg.se.dao.Enseignant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Add
 */
public class Add extends HttpServlet implements ConnectionProperties {
	private static final long serialVersionUID = 1L;
	
	private String ADD_ENSEIGNANT = "INSERT INTO Enseignant(nom, nbHeure, tauxHoraire) VALUES(?, ?, ?)";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("accueil");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enseignant newEnseignant = new Enseignant();
		
		newEnseignant.setNom(request.getParameter("nom"));
		newEnseignant.setNbHeure(Float.parseFloat(request.getParameter("nbHeure")));
		newEnseignant.setTauxHoraire(Float.parseFloat(request.getParameter("tauxHoraire")));
		
		Connection connex = getDatabaseConnection();
		
		try {
			PreparedStatement statement = connex.prepareStatement(ADD_ENSEIGNANT);
			statement.setString(1, newEnseignant.getNom());
			statement.setFloat(2, newEnseignant.getNbHeure());
			statement.setFloat(3, newEnseignant.getTauxHoraire());
			
			statement.executeUpdate();
			
			statement.close();
			
			response.sendRedirect("accueil");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
