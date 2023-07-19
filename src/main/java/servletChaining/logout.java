package servletChaining;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/logout")
public class logout extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Access session object
        HttpSession session = request.getSession();

        // Fetch username from session object
        String ename = (String) session.getAttribute("ename");

        PrintWriter writer = response.getWriter();

        if (ename != null) {
            session.invalidate(); // Invalidate the session
            writer.println("<center><h2>Thank you " + ename + " for visiting the application</h2></center>");
            writer.println("<center><h2>Logout successful</h2></center>");
        } else {
            writer.println("<center><h2 style='color:red;'>Session Time Out</h2></center>");
        }
}
    
}