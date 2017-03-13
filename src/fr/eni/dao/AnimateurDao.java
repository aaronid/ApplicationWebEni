package fr.eni.dao;

import static fr.eni.dao.contract.IAnimateurContract.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;
import fr.eni.dao.utils.BddUtils;
import fr.eni.dao.utils.ICrud;
import fr.eni.model.Animateur;
import fr.eni.utils.MonLogger;

/**
 * Manipulation des données Animateurs en Bdd
 * @author gferre2016
 *
 */
public class AnimateurDao implements ICrud<Animateur>{

	private Logger monLogger = MonLogger.getLogger(this.getClass().getSimpleName());
	
	@Override
	public boolean create(Animateur item) {
		
		monLogger.info("Create avec " + item);
		int nbLigneAffectee = 0;
		try(Connection cnx = BddUtils.getConnexion())
		{
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			pStmt.setString(1, item.getEmail());
			pStmt.setString(2, item.getMotDePasse());
			pStmt.setString(3, item.getNom());
			pStmt.setString(4, item.getPrenom());
			nbLigneAffectee = pStmt.executeUpdate();			
		}
		catch(Exception ex)
		{
			monLogger.severe(ex.getMessage());
		}
		
		return (nbLigneAffectee > 0);
	}

	@Override
	public List<Animateur> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animateur get(int id) {

		monLogger.info("Debut get() avec id : " + id);
		Animateur animateur = new Animateur();
		
		try(Connection cnx = BddUtils.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()){
				animateur = itemBuilder(rs);
			}
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}			
		return animateur;
	}
	
	public Animateur get(Animateur item) {

		monLogger.info("Debut get() avec objet Animateur : " + item);
		Animateur animateur = new Animateur();
		
		try(Connection cnx = BddUtils.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ITEM);
			pStmt.setString(1, item.getEmail());
			pStmt.setString(2, item.getMotDePasse());
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()){
				animateur = itemBuilder(rs);
			}
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}			
		return animateur;
	}

	@Override
	public boolean update(Animateur item) {
		
		monLogger.info("Debut Update()");
		int lignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()){
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);			
			pStmt.setString(1, item.getMotDePasse());
			pStmt.setString(2, item.getEmail());
			pStmt.setString(3, item.getNom());
			pStmt.setString(4, item.getPrenom());
			pStmt.setInt(5, item.getNumero());
			lignesAffectees = pStmt.executeUpdate();
		}
		catch(Exception ex){
			monLogger.severe(ex.getMessage());
		}
		return lignesAffectees > 0;
	}

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

	@Override
	public boolean delete(Animateur item) {
		
		monLogger.info("Debut delete() à partir d'un objet Animateur : " + item);
		int lignesAffectees = 0;
		
		try(Connection cnx = BddUtils.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);			
			pStmt.setInt(1, item.getNumero());		
			lignesAffectees = pStmt.executeUpdate();							
			
		} catch (Exception ex) {
			monLogger.severe(ex.getMessage());
		}	
		
		return lignesAffectees > 0;

	}

	@Override
	public Animateur itemBuilder(ResultSet rs) throws SQLException {
		
		Animateur animateur = new Animateur();
		
		animateur.setNumero(rs.getInt(ANIMATEURS_COL_NUMERO));
		animateur.setMotDePasse(rs.getString(ANIMATEURS_COL_MDP));
		animateur.setEmail(rs.getString(ANIMATEURS_COL_EMAIL));
		animateur.setNom(rs.getString(ANIMATEURS_COL_NOM));
		animateur.setPrenom(rs.getString(ANIMATEURS_COL_PRENOM));
		
		return animateur;		
	}

}

