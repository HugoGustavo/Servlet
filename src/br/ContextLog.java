package br;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContextLog")
public class ContextLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String par = request.getParameter("par1");
		
		ServletContext context = getServletContext();
		
		if ( par == null || par.equals("") )
			context.log("No message received: ", new IllegalStateException("Missing parameter"));
		else
			context.log("Here is the visitor's message: " + par);
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String title = "Context Log";
	      String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      
	      out.println(docType +
	         "<html>\n" +
	            "<head><title>" + title + "</title></head>\n" +
	            "<body bgcolor = \"#f0f0f0\">\n" +
	               "<h1 align = \"center\">" + title + "</h1>\n" +
	               "<h2 align = \"center\">Messages sent</h2>\n" +
	            "</body></html>"
	      );
		
	}

}
