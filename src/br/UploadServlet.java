package br;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 *1024;
	private int maxMemSize = 4 * 1024;
	private File file;
	
	public void init() {
		// File location where it would be stored
		filePath = "C:\\Program Files\\Apache Tomcat\\apache-tomcat-7.0.82\\webapps\\data";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if ( !isMultipart ) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>"); 
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>");
			out.println("</body>");
			out.println("</html>");
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		   
		//Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("C:\\temp"));
		
		// Create a new file upload handler
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		
		//maximum file size to be uploaded.
		servletFileUpload.setSizeMax( maxFileSize );
		
		try {
			// Parse the request to get file items.
			List<FileItem> fileItems = servletFileUpload.parseRequest(request);
			
			for ( FileItem fileItem : fileItems ) {
				if ( !fileItem.isFormField() ) {
					String fileName = fileItem.getName();					
					// Write the file
					if ( fileName.lastIndexOf("\\") >= 0 ) {
						file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\") ));
					} else {
						file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\") + 1 ));
					}
					fileItem.write( file );
					out.println("Uploaded Filename: " + fileName + "<br>");
				}
			}
			out.println("</body>");
			out.println("</html>");			
		} catch (Exception ex ){
			System.out.println(ex);
		}		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("GET method use with " + getClass().getName() + ": POST method required");
	}
	

}
