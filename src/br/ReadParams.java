package br;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReadParams")
public class ReadParams extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Method to handle GET method request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set response content type
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String title = "Reading All Form Parameters";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType +
		"<html>\n" +
			"<head>\n" +
				"<title>" + title + "</title>\n" +
			"</head>\n" +
			"<body bgcolor = \"#f0f0f0\">\n" +
				"<h1 align = \"center\">" + title + "</h1>\n" +
				"<table width = \"100%\" border = \"1\" align = \"center\">\n" +
				"<tr bgcolor = \"#949494\">\n" +
					"<th>Param Name</th>\n" +
					"<th>Param Value(s)</th>\n"+
				"</tr>\n"
		);
		
		Enumeration<String> parameterNames = request.getParameterNames();
		
		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();
			out.print("<tr><td>" + parameterName + "</td>\n<td>");
			String[] parameterValues = request.getParameterValues(parameterName);
			
			// Read single values data
			if ( parameterValues.length == 1) {
				String parameterValue = parameterValues[0];
				if ( parameterValue.length() == 0)
					out.println("<i>No Value</i>");
				else
					out.println(parameterValue);
			} else {
				// Read multiple values data
				out.println("<ul>");
				for (int i = 0; i < parameterValues.length; i++) {
					out.println("<li>" + parameterValues[i]);
				}
				out.println("</ul>");
			}
		}
		out.println("</tr>\n</table>\n</body></html>");
	}
	
	// Method to handle POST method request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
