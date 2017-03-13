package fr.eni.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.utils.MonLogger;

/**
 * Servlet implementation class AccueilServlet
 */
public class AccueilServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Logger logger = MonLogger.getLogger(this.getClass().getSimpleName());

		      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.entering(this.getClass().getSimpleName(), "doGet()");

		RequestDispatcher rd = null;
		
		rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		
		logger.exiting(this.getClass().getSimpleName(), "doGet()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
