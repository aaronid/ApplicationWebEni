package fr.eni.dao.contract;

public interface IStagiaireContract {

	// TABLE ET COLONNES STAGIAIRES
	public static final String TABLE_STAGIAIRES = "Stagiaires";
	public static final String STAGIAIRES_COL_IDENTIFIANT = "identifiant";
	public static final String STAGIAIRES_COL_NOM = "nom";
	public static final String STAGIAIRES_COL_PRENOM = "prenom";
	public static final String STAGIAIRES_COL_EMAIL = "email";
	public static final String STAGIAIRES_COL_ID_FORMATION = "idFormation";
	
	// APPELS PROCEDURES STOCKEES	
	public static final String SELECT_ALL = "{call STAGIAIRES_GET}";
	public static final String SELECT_BY_ID = "{call STAGIAIRES_GET_BY_ID(?)}";
	public static final String INSERT = "{call STAGIAIRES_INSERT(?,?,?,?)}";
	public static final String UPDATE = "{call STAGIAIRES_UPDATE(?,?,?,?,?)}";
	public static final String DELETE_BY_ID = "{call STAGIAIRES_DELETE_BY_ID(?)}";
	public static final String DELETE_BY_ITEM = "{call STAGIAIRES_DELETE_BY_ITEM(?,?,?)}";
	
	
	
	
	
}
