package br;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class SiteHitCounter implements Filter {
	private int hitCount;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		hitCount++;
		System.out.println("Site visits count: " + hitCount);
		chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		hitCount = 0;

	}

}
