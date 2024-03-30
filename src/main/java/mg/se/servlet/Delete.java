package mg.se.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.se.dao.ConnectionProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet implements ConnectionProperties {
	private static final long serialVersionUID = 1L;
	
	private String DELETE_ONE_ENSEIGNANT = "DELETE FROM Enseignant WHERE numENs = ?";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connex = getDatabaseConnection();
		
		try {
			PreparedStatement statement = connex.prepareStatement(DELETE_ONE_ENSEIGNANT);
			statement.setInt(1, Integer.parseInt(request.getParameter("numEns")));
			
			statement.executeUpdate();
			
			statement.close();
			connex.close();
			
			response.sendRedirect("accueil");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
