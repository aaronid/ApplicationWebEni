package fr.eni.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dao.FormationDao;
import fr.eni.dao.StagiaireDao;
import fr.eni.model.Formation;
import fr.eni.model.Stagiaire;
import fr.eni.utils.MonLogger;

/**
 * Servlet implementation class ManagerStagiaireServlet
 */
public class ManagerStagiaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = MonLogger.getLogger(this.getClass().getName());
	RequestDispatcher rd = null; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerStagiaireServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "listerStagiaires()");
		
		// contrôle des paramètres de la requête
		String action = request.getParameter("action");
		logger.info("action à effectuer : " + action);
		String element = request.getParameter("element");
		logger.info("element : " + element);
		
		// appel de la méthode de génération de la liste stagiaires
		if("listeStagiaires".equals(action)){
			
			logger.info("cas liste stagiaires");
			listerStagiaires(request, response);
		
		// appel de la méthode d'affichage du formulaire
		}else if("affichageFormulaire".equals(element)){	
			
			logger.info("cas affichage formulaire stagiaire");
			affichageFormulaireStagiaire(request,response);
			
		}else if("supprimer".equals(action)){
			
			logger.info("cas suppression stagiaire");
			supprimerStagiaire(request, response);
			
		}else if("modifier".equals(action)){
			
			logger.info("cas modification stagiaire");
			modifierStagiaire(request, response);
			
		}else if("ajouter".equals(action)){
			
			logger.info("cas ajout stagiaire");
			ajouterStagiaire(request,response);
		}
		
		logger.exiting(this.getClass().getName(), "listerStagiaires()");
	}

	/**
	 * Affichage du formulaire de modification ou d'ajout d'un stagiaire en fonction
	 * du paramètre "action" de la requete
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void affichageFormulaireStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "affichageFormulaire()");
		
		ArrayList<Formation> listeFormations = new FormationDao().get();
		request.setAttribute("formations", listeFormations);
		
		if("ajouter".equals(request.getParameter("action"))){
			logger.info("cas affichage formulaire ajout stagiaire");			
		}else if("modifier".equals(request.getParameter("action")) && request.getParameter("id") != null){
			logger.info("cas modification de la fiche d'un stagiaire");
			Stagiaire stagiaireAModifier = new Stagiaire();
			stagiaireAModifier = new StagiaireDao().get(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("stagiaireAModifier", stagiaireAModifier);				
		}
		rd = getServletContext().getRequestDispatcher("/sectionAnimateur/gestionStagiaires.jsp");
		rd.forward(request, response);
		
		logger.exiting(this.getClass().getName(), "affichageFormulaire()");
		
	}

	/**
	 * Ajout d'un stagiaire en bdd et actualisation de la liste
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ajouterStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.entering(this.getClass().getName(), "ajouterStagiaire()");
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		Formation formation = new FormationDao().getByLibelle(request.getParameter("formations"));
		
		Stagiaire stagiaireAAjouter = new Stagiaire();
		stagiaireAAjouter.setEmail(email);
		stagiaireAAjouter.setFormation(formation);
		stagiaireAAjouter.setNom(nom);
		stagiaireAAjouter.setPrenom(prenom);
		
		logger.info(stagiaireAAjouter.toString());
		
		boolean ajoutOk = new StagiaireDao().create(stagiaireAAjouter);
		
		if(ajoutOk){
			listerStagiaires(request, response);
		}else{
			logger.info("Problème lors de l'ajout du stagiaire");
		}
		
		logger.exiting(this.getClass().getName(), "ajouterStagiaire()");
		
	}

	/**
	 * Suppression d'un stagiaire en bdd et actualisation de la liste
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supprimerStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "supprimerStagiaire()");
		
		int idStagiaire = Integer.parseInt(request.getParameter("id"));
		
		boolean suppressionOk = new FormationDao().delete(idStagiaire);		
		
		if(suppressionOk){
			listerStagiaires(request, response);
		}else{
			logger.info("id récupéré : " + request.getParameter("id"));
			logger.info("Parse id : " + idStagiaire);
			logger.info("Echec suppression");
		}
		
		logger.exiting(this.getClass().getName(), "supprimerStagiaire()");
		
	}

	/**
	 * Modification d'une ligne stagiaire en bdd et actualisation de la liste
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modifierStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "modifierStagiaire()");
	
			Stagiaire newStagiaire = new Stagiaire();
			
			newStagiaire.setIdentifiant(Integer.parseInt(request.getParameter("id")));
			newStagiaire.setNom(request.getParameter("nom"));
			newStagiaire.setPrenom(request.getParameter("prenom"));
			newStagiaire.setEmail(request.getParameter("email"));
			
			// récupération de la formation choisie par l'identifiant		
			logger.info("comboc box formation : " + request.getParameter("formations"));
			Formation formation = new FormationDao().getByLibelle(request.getParameter("formations"));
			newStagiaire.setFormation(formation);
			
			logger.info("Stagiaire modifié : " + newStagiaire.getNom());

			boolean updateOk = new StagiaireDao().update(newStagiaire);
			
			if(updateOk){
				listerStagiaires(request, response);
			}else{
				logger.info("Problème lors de la modification de la fiche stagiaire");
			}
				
		logger.exiting(this.getClass().getName(), "modifierStagiaire()");
		
	}

	/**
	 * Générer la liste des stagiaires
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void listerStagiaires(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.entering(this.getClass().getName(), "listerStagiaires()");
		
		List<Stagiaire> stagiaires = new ArrayList<>();
		stagiaires = new StagiaireDao().get();
		
		request.setAttribute("liste", stagiaires);
		rd = getServletContext().getRequestDispatcher("/sectionAnimateur/listeStagiaires.jsp");
		rd.forward(request, response);
		
		logger.exiting(this.getClass().getName(), "listerStagiaires()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
