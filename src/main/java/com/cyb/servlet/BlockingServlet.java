package com.cyb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BlockingServlet
 */
@WebServlet(urlPatterns = "/BlockingServlet") 
public class BlockingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {  
		      long start = System.currentTimeMillis();  
		      //Thread.sleep(2000);  
		      String name = Thread.currentThread().getName();  
		      long duration = System.currentTimeMillis() - start;  
		      response.getWriter().printf(this+",Thread %s completed the task in %d ms.", name, duration);  
		    } catch (Exception e) {  
		      throw new RuntimeException(e.getMessage(), e);  
		    }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
