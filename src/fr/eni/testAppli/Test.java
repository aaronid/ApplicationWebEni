package fr.eni.testAppli;

import java.util.ArrayList;
import java.util.List;

import fr.eni.dao.FormationDao;
import fr.eni.dao.StagiaireDao;
import fr.eni.model.Stagiaire;

public class Test {

	public static void main(String[] args) {
		
		// Test de StagiaireDao

		// GET ALL
		/*List<Stagiaire> liste = new ArrayList<>();
		liste = new StagiaireDao().get();
		
		for (Stagiaire stagiaire : liste) {
			System.out.println(stagiaire.toString());
		}*/
		
		// GET BY ID
		/*Stagiaire stagGetById = new StagiaireDao().get(2);
		System.out.println("Stagiaire récupéré par son id : " + stagGetById.toString());*/
		
		
		// CREATE
		/*Stagiaire stagAAjouter = new Stagiaire();
		stagAAjouter.setNom("BRINDACIER");
		stagAAjouter.setPrenom("Fifi");
		stagAAjouter.setEmail("ff@yahoo.fr");
		stagAAjouter.setFormation(new FormationDao().get(1));
		
		boolean createOk = new StagiaireDao().create(stagAAjouter);
		if(createOk){
			System.out.println("Ajout ok");
		}else{
			System.out.println("Pb ajout");
		}*/
		
		
		// UPDATE
		/*stagGetById.setNom("PORHEL");
		stagGetById.setPrenom("Yohann");
		stagGetById.setEmail("yoyo@gmail.com");
		boolean modifOk = new StagiaireDao().update(stagGetById);
		if(modifOk){
			System.out.println("Modif ok");
		}else{
			System.out.println("nope");
		}*/
		

		
		// DELETE
		
		boolean supprOk = new StagiaireDao().delete(3);
		if(supprOk){
			System.out.println("Suppression ok");
		}else{
			System.out.println("Pb suppression");
		}
	
	}

}
