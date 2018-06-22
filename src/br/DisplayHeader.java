package br;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayHeader")
public class DisplayHeader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Method to handle GET mehtod Request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content Type
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		String title = "HTTP Header Request Example";
		out.println(docType +
		    "<html>\n" +
		    "<head><title>" + title + "</title></head>\n"+
		    "<body bgcolor = \"#f0f0f0\">\n" +
		    "<h1 align = \"center\">" + title + "</h1>\n" +
		    "<table width = \"100%\" border = \"1\" align = \"center\">\n" +
		    "<tr bgcolor = \"#949494\">\n" +
		    "<th>Header Name</th><th>Header Value(s)</th>\n"+
		    "</tr>\n"
		);
		
		Enumeration<String> headerNames = request.getHeaderNames();
		
		while(headerNames.hasMoreElements()) {
			String parameterName = (String) headerNames.nextElement();
			out.print("<tr><td>" + parameterName + "</td>\n");
			String parameterValue = request.getHeader(parameterName);
			out.println("<td>" + parameterValue + "</td></tr>\n");
		}
		out.println("</table><\n<body></html>");
	}
	
	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
