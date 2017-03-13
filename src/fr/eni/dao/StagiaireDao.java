package fr.eni.dao;

import static fr.eni.dao.contract.IStagiaireContract.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.dao.utils.BddUtils;
import fr.eni.dao.utils.ICrud;
import fr.eni.model.Formation;
import fr.eni.model.Stagiaire;
import fr.eni.utils.MonLogger;

/**
 * Manipulations des donn�es Stagiaires en bdd
 * @author gferre2016
 *
 */
public class StagiaireDao implements ICrud<Stagiaire> {

	private Logger monLogger = MonLogger.getLogger(this.getClass().getSimpleName());

	/**
	 * Cr�ation d'une ligne stagiaire dans la base de donn�es
	 */
	@Override
	public boolean create(Stagiaire item) {
		monLogger.info("Debut Create() Stagiaire � partir de l'item : " + item.toString());
		int nbLignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()){
			CallableStatement call = cnx.prepareCall(INSERT);
			call.setString(1,item.getNom());
			call.setString(2,item.getPrenom());
			call.setString(3,item.getEmail());
			call.setInt(4,item.getFormation().getIdentifiant());
			nbLignesAffectees = call.executeUpdate();
		}catch(Exception ex){
			monLogger.severe(ex.getMessage());
		}
		
		return nbLignesAffectees > 0;
	}

	/**
	 * R�cup�re la liste des stagiaires enregistr�s en bdd
	 */
	@Override
	public List<Stagiaire> get() {
		ArrayList<Stagiaire> stagiaires = new ArrayList<>();
		monLogger.info("Debut get()");
		
		try(Connection cnx = BddUtils.getConnexion()) {
			CallableStatement call = cnx.prepareCall(SELECT_ALL);
			ResultSet rs = call.executeQuery();							
			while(rs.next()){
				stagiaires.add(itemBuilder(rs));
			}
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}		
		return stagiaires;
	}

	/**
	 * R�cup�re un stagiaire en Bdd � partir d'un id pass�
	 * en param�tre
	 */
	@Override
	public Stagiaire get(int id) {
		
		monLogger.info("Debut get() avec id : " + id);
		Stagiaire stagiaire = new Stagiaire();
		
		try(Connection cnx = BddUtils.getConnexion()) {
			CallableStatement call = cnx.prepareCall(SELECT_BY_ID);
			call.setInt(1, id);
			ResultSet rs = call.executeQuery();
			if(rs.next()){
				stagiaire = itemBuilder(rs);
			}
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}			
		return stagiaire;
	}

	/**
	 * Mise � jour d'une ligne stagiaire dans la base de donn�es
	 */
	@Override
	public boolean update(Stagiaire item) {
		monLogger.info("Debut Update() Stagiaire � partir de l'item : " + item.toString());
		int nbLignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()){
			CallableStatement call = cnx.prepareCall(UPDATE);
			call.setString(1,item.getNom());
			call.setString(2,item.getPrenom());
			call.setString(3,item.getEmail());
			call.setInt(4,item.getFormation().getIdentifiant());
			call.setInt(5, item.getIdentifiant());
			nbLignesAffectees = call.executeUpdate();
		}catch(Exception ex){
			monLogger.severe(ex.getMessage());
		}
		
		return nbLignesAffectees > 0;
	}

	/**
	 * Supprime un Stagiaire dans la bdd � partir d'un id pass� en param�tre
	 */
	@Override
	public boolean delete(int id) {
		monLogger.info("Debut delete() Stagiaire � partir de l'id : " + id);
		int lignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()) {
			
			CallableStatement call = cnx.prepareCall(DELETE_BY_ID);			
			call.setInt(1, id);
			lignesAffectees = call.executeUpdate();							
			
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}	
		
		return lignesAffectees > 0;
	}

	/**
	 * Supprime un stagiaire de la bdd � partir d'une instance
	 * stagiaire pass�e en param�tre
	 */
	@Override
	public boolean delete(Stagiaire item) {
		
		monLogger.info("Debut delete() � partir d'un objet Stagiaire : " + item);
		int lignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()) {
			
			CallableStatement call = cnx.prepareCall(DELETE_BY_ITEM);			
			call.setInt(1, item.getIdentifiant());
			lignesAffectees = call.executeUpdate();							
			
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}	
		
		return lignesAffectees > 0;
	}

	/**
	 * Cr�ation d'un objet Stagiaire � partir d'un ResultSet
	 */
	@Override
	public Stagiaire itemBuilder(ResultSet rs) throws SQLException {

		Stagiaire stagiaire = new Stagiaire();
		
		stagiaire.setIdentifiant(rs.getInt(STAGIAIRES_COL_IDENTIFIANT));
		stagiaire.setNom(rs.getString(STAGIAIRES_COL_NOM));
		stagiaire.setPrenom(rs.getString(STAGIAIRES_COL_PRENOM));
		stagiaire.setEmail(rs.getString(STAGIAIRES_COL_EMAIL));
		stagiaire.setFormation(new FormationDao().get(rs.getInt(STAGIAIRES_COL_ID_FORMATION)));
		
		return stagiaire;
	}
	
	
	
	
	
	
}
