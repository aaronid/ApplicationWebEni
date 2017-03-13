package fr.eni.dao.contract;

public interface IFormationContract {

	
	// TABLE ET COLONNES FORMATIONS
	public static final String TABLE_FORMATIONS= "Formations";
	public static final String FORMATIONS_COL_ID = "identifiant";
	public static final String FORMATIONS_COL_LIBELLE = "libelle";
	public static final String FORMATIONS_COL_DESCRIPTION = "description";
	public static final String FORMATIONS_COL_COMPETENCES = "competences";
	
	
	// REQUETES	
	public static final String SELECT_ALL = "{call FORMATIONS_GET}";
	public static final String SELECT_BY_ID = "{call FORMATIONS_GET_BY_ID(?)}";
	public static final String SELECT_BY_LIBELLE = "{call FORMATIONS_GET_BY_LIBELLE(?)}";
	
		
	public static final String DELETE = "DELETE FROM " + TABLE_FORMATIONS +
            					  " WHERE " + FORMATIONS_COL_ID + "=?";
	
	public static final String INSERT = "INSERT INTO " + TABLE_FORMATIONS + "(" +
            					  FORMATIONS_COL_LIBELLE + "," + 
            					  FORMATIONS_COL_DESCRIPTION + "," + 
            					  FORMATIONS_COL_COMPETENCES + ")" +
								  " VALUES (?,?,?)";
	
	public static final String UPDATE = "UPDATE " + TABLE_FORMATIONS + " SET " +
			  					  FORMATIONS_COL_LIBELLE + "=?," +
			  					  FORMATIONS_COL_DESCRIPTION + "=?," +
			  					  FORMATIONS_COL_COMPETENCES + "=?" +
			  					  " WHERE " + FORMATIONS_COL_ID + "=?" ;
	
}
