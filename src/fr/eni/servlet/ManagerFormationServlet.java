package fr.eni.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dao.FormationDao;
import fr.eni.model.Formation;
import fr.eni.utils.MonLogger;

/**
 * Servlet implementation class ManagerFormationServlet
 */
public class ManagerFormationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = MonLogger.getLogger(this.getClass().getName());
	RequestDispatcher rd = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerFormationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "doGet()");			
		
		logger.info("action à effectuer : " + request.getParameter("action"));
		
		String action = request.getParameter("action");

		
		// si le paramètre action est défini 
		if(request.getParameter("action")!=null){
			
			switch (request.getParameter("action")) {
			
			case "listeFormationsAnim":
				
				logger.info("cas listeFormationsAnim");
				
				// appel de la méthode affichant le tableau de formations
				// dans la section animateur
				listerFormations(request, response);
				break;
			case "listeFormations":
				
				logger.info("cas listeFormations");
				
				// appel de la méthode affichant le tableau de formations
				// de la page formations.jsp (hors section animateur)
				listerFormations(request, response);
				break;
			case "ajouter":
				
				logger.info("cas affichage formulaire ajout");
				
				// affichage du formulaire dans le cas d'un ajout
				request.setAttribute("action", action);
				rd = getServletContext().getRequestDispatcher("/sectionAnimateur/gestionFormations.jsp");
				rd.forward(request, response);
				break;
			case "modifier":
				
				logger.info("cas affichage formulaire modifier");
				
				// affichage du formulaire dans le cas d'une modification
				int identifiant = Integer.parseInt(request.getParameter("id"));
				Formation formationAModifier = new FormationDao().get(identifiant);						
				request.setAttribute("formation", formationAModifier);
				request.setAttribute("action", action);
				rd = getServletContext().getRequestDispatcher("/sectionAnimateur/gestionFormations.jsp");
				rd.forward(request, response);
				break;
			case "supprimer":
				
				logger.info("cas suppression");
				
				// appel de la méthode supprimerFormation()
				supprimerFormation(request, response);
				break;
			default:
				break;
	
			} 
		
		// si le paramètre id existe, c'est une modification
		}else if (request.getParameter("id")!=null){
			
			logger.info("cas modification");
			
			modifierFormation(request,response);
			
		// sinon, si le paramètre libelle n'est pas null, c'est un ajout
		}else if(request.getParameter("libelle")!=null){	
			
			logger.info("cas ajout");
			
			ajouterFormation(request,response);	
			
		}
			
		logger.exiting(this.getClass().getName(), "doGet()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
		
	/**
	 * Suppression d'une formation et actualisation de la liste
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supprimerFormation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "supprimerFormation()");
		
		int idFormation = Integer.parseInt(request.getParameter("id"));
		
		boolean suppressionOk = new FormationDao().delete(idFormation);		
		
		if(suppressionOk){
			listerFormations(request,response);
		}else{
			logger.info("id récupéré : " + request.getParameter("id"));
			logger.info("Parse id : " + idFormation);
			logger.info("Echec suppression");
		}
		
		logger.exiting(this.getClass().getName(), "supprimerFormation()");
	}

	/**
	 * Modification d'une formation et actualisation de la liste
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modifierFormation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "modifierFormation()");
	
			Formation newFormation = new Formation();
			
			newFormation.setIdentifiant(Integer.parseInt(request.getParameter("id")));
			newFormation.setLibelle(request.getParameter("libelle"));
			newFormation.setDescription(request.getParameter("description"));
			newFormation.setCompetences(request.getParameter("competences"));
			
			logger.info("Formation modifiée : " + newFormation.getLibelle());

			boolean updateOk = new FormationDao().update(newFormation);
			
			if(updateOk){
				listerFormations(request,response);
			}else{
				logger.info("Problème lors de la modification de la formation");
			}
				
		logger.exiting(this.getClass().getName(), "modifierFormation()");
	}

	/**
	 * Ajout d'une formation et actualisation de la liste
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ajouterFormation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "ajouterFormation()");

		String libelle = request.getParameter("libelle");
		String description = request.getParameter("description");
		String competences = request.getParameter("competences");
		
		logger.info("nb des" + description.length());
		
		Formation formationAjouter = new Formation();
		formationAjouter.setLibelle(libelle);
		formationAjouter.setDescription(description);
		formationAjouter.setCompetences(competences);
		
		logger.info("ajout de la formation : " + formationAjouter.getLibelle());
		
		boolean ajoutOk = new FormationDao().create(formationAjouter);
		
		if(ajoutOk){
			listerFormations(request,response);	
		}else{
			logger.info("Echec de l'ajout d'une formation");
		}
			
		logger.exiting(this.getClass().getName(), "ajouterFormation()");	
	}

	/**
	 * Récupération de la liste de formations de la bdd
	 * et envoi à la jsp appelante
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listerFormations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "listerFormations()");

		// on récupère la liste de formations de la bdd
		ArrayList<Formation> formations = new ArrayList<>();
		formations = new FormationDao().get();
		
		// création de l'attribut contenant la liste de formations
		request.setAttribute("liste", formations);
		
		// détermine quelle jsp à fait l'appel (depuis l'accueil ou depuis une section utilisateur
		if("listeFormations".equals(request.getParameter("action"))){
			rd = getServletContext().getRequestDispatcher("/formations.jsp");
		}else {
			rd = getServletContext().getRequestDispatcher("/sectionAnimateur/listeFormations.jsp");
		}
		
		// on renvoie la liste à la jsp qui s'occupe de l'afficher
		rd.forward(request, response);
		
		logger.exiting(this.getClass().getName(), "listerFormations()");
	}

}
