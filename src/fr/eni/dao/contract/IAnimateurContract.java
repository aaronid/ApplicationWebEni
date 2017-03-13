package fr.eni.dao.contract;

public interface IAnimateurContract {

	// TABLE ET COLONNES
	public static final String TABLE_ANIMATEURS = "Animateurs";
	public static final String ANIMATEURS_COL_EMAIL = "email";
	public static final String ANIMATEURS_COL_NUMERO = "numAnimateur";
	public static final String ANIMATEURS_COL_MDP = "motDePasse";
	public static final String ANIMATEURS_COL_NOM = "nom";
	public static final String ANIMATEURS_COL_PRENOM = "prenom";
	
	// REQUETES
	public static final String SELECT_ALL = "SELECT * FROM " + TABLE_ANIMATEURS; 
	
	public static final String SELECT_BY_ID = SELECT_ALL + " WHERE " + ANIMATEURS_COL_NUMERO + "=?";
	
	public static final String SELECT_BY_ITEM = SELECT_ALL + " WHERE " +
												ANIMATEURS_COL_EMAIL + "=? AND " +
												ANIMATEURS_COL_MDP + "=?";

	
	public static final String DELETE = "DELETE FROM " + TABLE_ANIMATEURS +
            					  		" WHERE " + ANIMATEURS_COL_NUMERO + "=?";
	
	public static final String INSERT = "INSERT INTO " + TABLE_ANIMATEURS + "(" +
										ANIMATEURS_COL_EMAIL + "," +
										ANIMATEURS_COL_MDP + "," +
										ANIMATEURS_COL_NOM + "," +
										ANIMATEURS_COL_PRENOM +
										" VALUES (?,?,?,?)";
	
	public static final String UPDATE = "UPDATE " + TABLE_ANIMATEURS + " SET " +
								        ANIMATEURS_COL_MDP + "=?," +
								        ANIMATEURS_COL_EMAIL + "=?" +
										ANIMATEURS_COL_NOM +  "=?" +
										ANIMATEURS_COL_PRENOM + "=?" +
			  					        " WHERE " + ANIMATEURS_COL_NUMERO + "=?" ;
	
	
	
	
	
}
