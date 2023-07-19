package org.jsp.firstwebServletprogram;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/genaricServlet")
public class genaricServlet extends GenericServlet
{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
	{
		//Back end code
				System.out.println("Backcode execution...");
				//print output on web browser
				PrintWriter writer=response.getWriter();
				writer.println("<h1>backcode execution <h1>");
			
				
				String temp = request.getParameter("empid");
				int id = Integer.parseInt(temp);

				String name = request.getParameter("ename");

				String temp2 = request.getParameter("salary");
				double salary = Double.parseDouble(temp2);

				String temp3 = request.getParameter("edeptno");
				int deptno = Integer.parseInt(temp3);

				String emobile = request.getParameter("emobile");
				String email = request.getParameter("email");
				String password = request.getParameter("password");

				writer.println("Employee Name: " + name);
				writer.println("<br> Employee ID: " + id);
				writer.println("<br> Salary: " + salary);
				writer.println("<br> Department Number: " + deptno);
				writer.println("<br> Mobile: " + emobile);
				writer.println("<br> Email: " + email);
				writer.println("<br> Password: " + password);

				
				//jdbc code for connecting
				String url="jdbc:mysql://localhost:3306?user=root&password=12345";
				String query="insert into ramakrishna.employee values(?,?,?,?,?,?,?)";
				try 
				{
					// register mysql driver with servlet
					Class.forName("com.mysql.jdbc.Driver");//classNotFoundException
					Connection connection=DriverManager.getConnection(url);//sql execption
					PreparedStatement  ps=connection.prepareStatement(query);
					
					// assign values placeholder
					ps.setInt(1,id);
					ps.setString(2,name);
					ps.setDouble(3,salary);
					ps.setInt(4,deptno);
					ps.setString(5, emobile);
					ps.setString(6, password);
					ps.setString(7, email);
					
					ps.executeUpdate();
					writer.println("<br><h1 style='color:green;'>Registation Successfull!!</h1>");
					
					//close the connection
					connection.close();
					
				}
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
  
}
