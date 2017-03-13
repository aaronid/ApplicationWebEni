package fr.eni.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dao.AnimateurDao;
import fr.eni.model.Animateur;
import fr.eni.utils.MonLogger;

/**
 * Servlet implementation class AuthentificationCookieServlet
 */
public class AuthentificationCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = MonLogger.getLogger(this.getClass().getName());   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentificationCookieServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.entering(this.getClass().getName(), "doGet()");
		
		RequestDispatcher rd = null;
		
		boolean estDejaConnecte = false;
		
		// on récupère les paramètres envoyés par le formulaire 
		String email = request.getParameter("email");
		String mdp = request.getParameter("mdp");
		String stayConnected = request.getParameter("stayConnected");
		
		// vérification des informations avec celles de la bdd
		Animateur aTestCnx = new Animateur();
		aTestCnx.setEmail(email);
		aTestCnx.setMotDePasse(mdp);			
		Animateur animateurBdd = new AnimateurDao().get(aTestCnx);
		
		logger.info("info du formulaire : " + email + " " + mdp);
		logger.info("info de la bdd : " + animateurBdd.getEmail() + " " + animateurBdd.getMotDePasse());
				
		// récupération des cookies stockés par le client				
		Cookie[] cookies = request.getCookies();
				
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie != null && "info".equals(cookie.getName())){
					
					logger.info("Vérification des données en cookies :");
					
					String value = cookie.getValue();
					String[] ids = value.split("-");
					String emailCookie = ids[0];
					String mdpCookie = ids[1];
					
					Animateur animConnecte = new AnimateurDao().get(new Animateur(emailCookie,mdpCookie));
										
					logger.info("emailCookie : " + animConnecte.getEmail());
					logger.info("mdpCookie : " + mdpCookie);
					
					logger.info("email : " + emailCookie);
					logger.info("mdp : " + animConnecte.getMotDePasse());
					
					if(emailCookie.equals(animConnecte.getEmail())
							&& mdpCookie.equals(animConnecte.getMotDePasse())){
						
						logger.info("cookie ok");
						
						estDejaConnecte = true;
						rd = getServletContext().getRequestDispatcher("/sectionAnimateur/sectionAnimateur.jsp");
						
					}
				}
			}
		}
		
		// si l'utilisateur n'a pas de cookies de connection
		// on vérifie les 3 cas possibles
		if(!estDejaConnecte){
			
			
			// première tentative de connection
			if(request.getParameter("email")==null
				&& request.getParameter("mdp")==null){
					
				logger.info("Première cnx");
				
				rd = getServletContext().getRequestDispatcher("/authentification.jsp");
				
			}else if(email.equals(animateurBdd.getEmail())
					&& mdp.equals(animateurBdd.getMotDePasse())){
					
				logger.info("connection OK");
				
				// connection OK
				// vérifier si le client veut rester connecter
				if(stayConnected != null){
					// création d'un cookie avec les infos de connection
					
					logger.info("Première cnx et stayConnected on : création d'un cookie de cnx");
					
					Cookie connectionInfo = new Cookie("info",email + "-" + mdp);
					connectionInfo.setMaxAge(60*365);
					response.addCookie(connectionInfo);
					
				}
				
				rd = getServletContext().getRequestDispatcher("/sectionAnimateur/sectionAnimateur.jsp");
				
			}else if(!mdp.equals(animateurBdd.getMotDePasse()) 
					&& !email.equals(animateurBdd.getEmail())){
				
				logger.info("connection KO");
				
				// connection KO
				String error = "Email ou mot de passe invalide";
				request.setAttribute("erreur", error);
				rd = getServletContext().getRequestDispatcher("/authentification.jsp");
				
			}
			
			
		}
					
		rd.forward(request, response);
		
		logger.exiting(this.getClass().getName(), "doGet()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
