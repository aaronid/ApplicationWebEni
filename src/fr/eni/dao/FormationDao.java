package fr.eni.dao;

import static fr.eni.dao.contract.IFormationContract.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;
import fr.eni.dao.utils.*;
import fr.eni.dao.utils.ICrud;
import fr.eni.model.Formation;
import fr.eni.utils.MonLogger;

/**
 * Manipulation des données Formations en Bdd
 * @author gferre2016
 *
 */
public class FormationDao implements ICrud<Formation>{

	private Logger monLogger = MonLogger.getLogger(this.getClass().getSimpleName());
	

	/**
	 * Création d'une ligne dans la table formation
	 */
	@Override
	public boolean create(Formation item) {
		
		monLogger.info("Create avec " + item);
		int nbLigneAffectee = 0;
		try(Connection cnx = BddUtils.getConnexion())
		{
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			pStmt.setString(1, item.getLibelle());
			pStmt.setString(2, item.getDescription());
			pStmt.setString(3, item.getCompetences());
			nbLigneAffectee = pStmt.executeUpdate();
			
		}
		catch(Exception ex)
		{
			monLogger.severe(ex.getMessage());
		}
		
		return (nbLigneAffectee > 0);
	}

	/**
	 * Récupère une formation en Bdd à partir d'un id passé
	 * en paramètre
	 */
	@Override
	public Formation get(int id) {

		monLogger.info("Debut get() avec id : " + id);
		Formation formation = new Formation();
		
		try(Connection cnx = BddUtils.getConnexion()) {
			CallableStatement call = cnx.prepareCall(SELECT_BY_ID);
			call.setInt(1, id);
			ResultSet rs = call.executeQuery();
			if(rs.next()){
				formation = itemBuilder(rs);
			}
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}			
		return formation;
	}

	/**
	 * Mise à jour d'une ligne formation dans la Bdd
	 */
	@Override
	public boolean update(Formation item) {
		
		monLogger.info("Debut Update()");
		int lignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()){
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setString(1, item.getLibelle());
			pStmt.setString(2, item.getDescription());
			pStmt.setString(3, item.getCompetences());
			pStmt.setInt(4, item.getIdentifiant());
			lignesAffectees = pStmt.executeUpdate();
		}
		catch(Exception ex){
			monLogger.severe(ex.getMessage());
		}
		return lignesAffectees > 0;
	}

	/**
	 * Suppression d'une formation à partir d'un id passé en paramètre
	 */
	@Override
	public boolean delete(int id) {
		
		monLogger.info("Debut delete() avec id : " + id);
		int lignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);			
			pStmt.setInt(1, id);		
			lignesAffectees = pStmt.executeUpdate();							
			
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}	
		
		return lignesAffectees > 0;
	}

	/**
	 * Suppression d'une formation à partir d'un objet Formation passé
	 * en paramètre
	 */
	@Override
	public boolean delete(Formation item) {
		
		monLogger.info("Debut delete() à partir d'un objet Formation : " + item);
		int lignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);			
			pStmt.setInt(1, item.getIdentifiant());		
			lignesAffectees = pStmt.executeUpdate();							
			
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}	
		
		return lignesAffectees > 0;
	}

	
	/**
	 * Récupère la liste des formations de la Bdd
	 */
	public ArrayList<Formation> get() {
	
	ArrayList<Formation> formations = new ArrayList<>();
	monLogger.info("Debut get()");
	
	try(Connection cnx = BddUtils.getConnexion()) {
		CallableStatement call = cnx.prepareCall(SELECT_ALL);
		ResultSet rs = call.executeQuery();							
		while(rs.next()){
			formations.add(itemBuilder(rs));
		}
	} catch (Exception ex) {
		monLogger.severe(ex.getMessage());
	}		
	return formations;
	}
	
	public Formation getByLibelle(String libelle){
		
		Formation formation = new Formation();
		monLogger.info("Debut getByLibelle() : " + libelle);
		
		try(Connection cnx = BddUtils.getConnexion()) {
			CallableStatement call = cnx.prepareCall(SELECT_BY_LIBELLE);
			call.setString(1, libelle);
			ResultSet rs = call.executeQuery();							
			while(rs.next()){
				formation = itemBuilder(rs);
			}
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}		
		return formation;
		
	}

	/**
	 * Création d'un objet Formation à partir de la BDD
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Formation itemBuilder(ResultSet rs) throws SQLException {
		
		Formation uneFormation = new Formation();
		
		uneFormation.setIdentifiant(rs.getInt(FORMATIONS_COL_ID));
		uneFormation.setLibelle(rs.getString(FORMATIONS_COL_LIBELLE));
		uneFormation.setDescription(rs.getString(FORMATIONS_COL_DESCRIPTION));
		uneFormation.setCompetences(rs.getString(FORMATIONS_COL_COMPETENCES));
		
		return uneFormation;
	}
	
}
