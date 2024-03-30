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
 * Servlet implementation class FetchAll
 */
public class FetchAll extends HttpServlet implements ConnectionProperties {
	private static final long serialVersionUID = 1L;
	
	private String SELECT_ALL_ENSEIGNANT = "SELECT * FROM Enseignant";
	private String SELECT_MAX_MIN_SUM = "SELECT MAX(nbHeure * tauxHoraire) as max_, MIN(nbHeure * tauxHoraire) as min_, SUM(nbHeure * tauxHoraire) as sum_ FROM Enseignant";
	private String EMPTY_TABLE = "Données inexistante";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchAll() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connex = getDatabaseConnection();
		
		try {
			PreparedStatement statement = connex.prepareStatement(SELECT_ALL_ENSEIGNANT);
			ResultSet results = statement.executeQuery();
			
			List<Enseignant> arrayOfEnseignant = new ArrayList<>();
			
			while (results.next()) {
				Enseignant enseignant = new Enseignant();
				
				enseignant.setNumEns(results.getInt("numEns"));
				enseignant.setNom(results.getString("nom"));
				enseignant.setNbHeure(results.getFloat("nbHeure"));
				enseignant.setTauxHoraire(results.getFloat("tauxHoraire"));
				
				arrayOfEnseignant.add(enseignant);
			}
			
			results.close();
			statement.close();
			
			PreparedStatement statement_bonus = connex.prepareStatement(SELECT_MAX_MIN_SUM);
			ResultSet results_bonus = statement_bonus.executeQuery();
			
			float minimum = 0;
			float maximum = 0;
			float somme = 0;
			
			while (results_bonus.next()) {
				maximum = results_bonus.getFloat("max_");
				minimum = results_bonus.getFloat("min_");
				somme = results_bonus.getFloat("sum_");
			}
			
			results_bonus.close();
			statement_bonus.close();
			
			connex.close();
			
			if (!arrayOfEnseignant.isEmpty()) {
				request.setAttribute("allEnseignant", arrayOfEnseignant);
				request.setAttribute("min", minimum);
				request.setAttribute("max", maximum);
				request.setAttribute("sum", somme);
				request.getRequestDispatcher("/Accueil.jsp").forward(request, response);
			}
			else {
				request.setAttribute("Erreur", EMPTY_TABLE);
				request.getRequestDispatcher("/Accueil.jsp").forward(request, response);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
//		response.sendRedirect("Accueil.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
