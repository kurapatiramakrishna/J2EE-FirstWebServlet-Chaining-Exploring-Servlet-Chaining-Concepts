package org.jsp.firstwebServletprogram;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/httpServlet")
public class httpServlet extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
{
	//fetch the data from the html
	String empid=req.getParameter("empid");
	
	PrintWriter writer=resp.getWriter();
	
	  
		
	
	try 
	{
		//jdbc
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query="select * from ramakrishna.employee where eid=?";

		//connection
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection =DriverManager.getConnection(url);
		PreparedStatement ps=connection.prepareStatement(query);
		
		ps.setString(1, empid);
		ResultSet rs=ps.executeQuery();
		
		if (rs.next())
		{
			writer.println("Employee details  are:");
			writer.println("eid :"+rs.getString("eid"));
			writer.println("ename :"+rs.getString("ename"));
			writer.println("Emai :"+rs.getString("Email"));
			writer.print("esalary :"+rs.getString("esalary"));
			writer.println("eMobileNo :"+rs.getString("emobileno"));
			writer.println("Password :"+rs.getString("Password"));
			writer.println("edeptno :"+rs.getString("edeptno"));
		}
		else
		{
			writer.print("record is not found");
		}
		connection.close();
	} 
	catch (Exception e)
{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
  }
}
