package br;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCookies")
public class DeleteCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get an array of Cookies associated with this domain
		Cookie[] cookies = request.getCookies();
		
		// Set response content type
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String title = "Delete Cookies Example";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		
		out.println(docType +
				"<html>\n" +
		         "<head><title>" + title + "</title></head>\n" +
		         "<body bgcolor = \"#f0f0f0\">\n" );
		if ( cookies != null ) {
			out.println("<h2> Cookies Name and Value</h2>");
			
			for ( Cookie cookie : cookies ) {
				if ( (cookie.getName()).compareTo("first_name") == 0 ) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					out.print("Deleted cookie: " + cookie.getName() + "<br/>");
				}
				out.print("Name : " + cookie.getName() + ", ");
				out.print("Value: " + cookie.getValue() + "<br/>");
			}
		} else {
			out.print("<h2>No cookies founds</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
