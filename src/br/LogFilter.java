package br;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns = {"/*"},
		initParams = {
				@WebInitParam(
						name = "test-param",
						value = "Initialization Paramter"
						)
		})
public class LogFilter implements Filter {

	@Override
	public void destroy() {
		/* Called before the Filter instance is removed from service by the web container*/
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// Get the IP address of client machine
		String ipAddress = request.getRemoteAddr();
		
		// Log the IP address and current timestamp
		System.out.println("IP " + ipAddress + ", Time " + (new Date()).toString());
		
		// Pass request back down the filter chain
		filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// GEt init parameter
		String testParameter = filterConfig.getInitParameter("test-param");
		
		// Print the init parameter
		System.out.println("Test Parameter: " + testParameter);

	}

}
