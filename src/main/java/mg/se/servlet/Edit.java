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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Edit
 */
public class Edit extends HttpServlet implements ConnectionProperties {
	private static final long serialVersionUID = 1L;
	
	private String SELECT_ONE_ENSEIGNANT = "SELECT nom, nbHeure, tauxHoraire FROM Enseignant WHERE numEns = ?";
	private String UPDATE_ENSEIGNANT = "UPDATE Enseignant SET nom = ?, nbHeure = ?, tauxHoraire = ? WHERE numENs = ?";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numEns = 0;
		if (request.getParameter("numEns") == null) {
			response.sendRedirect("accueil");
		}
		else {
			numEns = Integer.parseInt(request.getParameter("numEns"));

			Connection connex = getDatabaseConnection();
			
			try {
				PreparedStatement statement = connex.prepareStatement(SELECT_ONE_ENSEIGNANT);
				statement.setInt(1, numEns);
				ResultSet results = statement.executeQuery();
				Enseignant foundEnseignant = new Enseignant();
				
				foundEnseignant.setNumEns(numEns);
				
				while (results.next()) {
					foundEnseignant.setNom(results.getString("nom"));
					foundEnseignant.setNbHeure(results.getFloat("nbHeure"));
					foundEnseignant.setTauxHoraire(results.getFloat("tauxHoraire"));
				}
				
				results.close();
				statement.close();
				connex.close();
				
				request.setAttribute("oneEnseignant", foundEnseignant);
				request.getRequestDispatcher("/Modifier.jsp").forward(request, response);
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enseignant editedEnseignant = new Enseignant();
		
		editedEnseignant.setNumEns(Integer.parseInt(request.getParameter("numEns")));
		editedEnseignant.setNom((String) request.getParameter("nom"));
		editedEnseignant.setNbHeure(Float.parseFloat(request.getParameter("nbHeure")));
		editedEnseignant.setTauxHoraire(Float.parseFloat(request.getParameter("tauxHoraire")));
		
		Connection connex = getDatabaseConnection();
		
		try {
			PreparedStatement statement = connex.prepareStatement(UPDATE_ENSEIGNANT);
			statement.setString(1, editedEnseignant.getNom());
			statement.setFloat(2, editedEnseignant.getNbHeure());
			statement.setFloat(3, editedEnseignant.getTauxHoraire());
			statement.setInt(4, editedEnseignant.getNumEns());
			
			statement.executeUpdate();
			
			statement.close();
			connex.close();
			
			response.sendRedirect("accueil");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
