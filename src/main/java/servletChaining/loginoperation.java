package servletChaining;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/loginoperation")
public class loginoperation extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch user details from the HTML form
        String mobileno = request.getParameter("emobileno");
        String password = request.getParameter("password");

        // JDBC code
        String url = "jdbc:mysql://localhost:3306/ramakrishna?user=root&password=12345";
        String query = "SELECT * FROM ramakrishna.employee WHERE emobileno=? AND password=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, mobileno);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            PrintWriter writer = response.getWriter();

            if (rs.next()) {
                // Retrieve data from the database
                String ename = rs.getString("ename");
                String emobile = rs.getString("emobileno");
                String eid = rs.getString("eid");
                String email = rs.getString("email");
                String esalary = rs.getString("esalary");
                String edeptno = rs.getString("edeptno");
                System.out.println("Data retrieved from the database: ename=" + ename);

                // Access the session object
                HttpSession session = request.getSession();
                System.out.println("Session object accessed...");

                // Store user's data in the session object
                session.setAttribute("ename", ename);
                session.setAttribute("emobile", emobile);
                session.setAttribute("eid", eid);
                session.setAttribute("email", email);
                session.setAttribute("esalary", esalary);
                session.setAttribute("edeptno", edeptno);
                System.out.println("Data stored in session...");

                // Set session expiration time
                session.setMaxInactiveInterval(10);

                // Forward the request to main.html
                RequestDispatcher dispatcher = request.getRequestDispatcher("main.html");
                dispatcher.include(request, response);

                // Print the name of the user
                writer.println("<center><h2 style='color:blueviolet;'>Welcome " + ename + "</h2></center>");
                writer.println("<center><h2 style='color:green;'>Login Successful</h2></center>");
            } else {
                // Credentials are invalid
                // Show login.html
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
                dispatcher.include(request, response);
                writer.println("<center><h2 style='color:red;'>Login failed</h2></center>");
            }

            // Close the resources
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e)
{
            e.printStackTrace();
        }
    }
}