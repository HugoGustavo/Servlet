package br;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JBDC drive name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/test";
		
		// Databsae credentials
		final String USER = "root";
		final String PASS = "root";
		
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Database Result";
	      
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body bgcolor = \"#f0f0f0\">\n" +
				"<h1 align = \"center\">" + title + "</h1>\n"
		);
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Register JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Open a connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// Execute SQL query
			statement = connection.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			resultSet = statement.executeQuery(sql);
			
			// Extract data from result set
			while( resultSet.next() ) {
				// Retrieve by column name
				int id = resultSet.getInt("id");
				int age = resultSet.getInt("age");
				String first = resultSet.getString("first");
				String last = resultSet.getString("last");
				
				// Display values
				out.print("ID: " + id);
				out.print(", Age: " + age);
				out.print(", First: " + first);
				out.print(", Last: " + last + "<br>");
			}
			out.println("</body></html>");
			
			// Clean-up environmet
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException se) {
			// Handl erros for JDBC
			se.printStackTrace();
		} catch(Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if ( statement != null )
					statement.close();
			} catch (SQLException se2) {
				
			}
			try {
				if ( connection != null )
					connection.close();
			} catch (SQLException se) {
				
			}
		}
		
	}

}
