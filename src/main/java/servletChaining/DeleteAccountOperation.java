package servletChaining;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteAccountOperation")
public class DeleteAccountOperation extends HttpServlet
{
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Retrieve the email from the request
	        String email = request.getParameter("email");

	        // JDBC code
	        String url = "jdbc:mysql://localhost:3306/ramakrishna?user=root&password=12345";
	        String query = "DELETE FROM ramakrishna.employee WHERE email=?";

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(url);
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	           
	            int rowsDeleted = ps.executeUpdate();

	            PrintWriter writer = response.getWriter();

	            if (rowsDeleted > 0) 
	            {
	                // Account deletion successful
	                writer.println("<center><h2>Account Deleted</h2></center>");
	                writer.println("<center><p>Your account has been successfully deleted.</p></center>");
	            } else {
	                // Account deletion failed
	                writer.println("<center><h2>Delete Account Failed</h2></center>");
	                writer.println("<center><p>Unable to delete the account.</p></center>");
	            }

	            // Close the resources
	            ps.close();
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
