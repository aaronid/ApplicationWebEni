package fr.eni.model;

/**
 * Représente une formation
 * 
 * @author gferre2016
 *
 */
public class Formation implements Cloneable, Comparable<Formation>{

	//----------------------------------------
	// VARIABLES MEMBRES
	//----------------------------------------
	
	private int identifiant;
	private String libelle;
	private String description;
	private String competences;
	
	//----------------------------------------
	// GETTERS ET SETTERS
	//----------------------------------------
	
	/**
	 * @return the competences
	 */
	public String getCompetences() {
		return competences;
	}
	/**
	 * @param competences the competences to set
	 */
	public void setCompetences(String competences) {
		this.competences = competences;
	}
	/**
	 * @return the codeFormation
	 */
	public int getIdentifiant() {
		return identifiant;
	}
	/**
	 * @param codeFormation the codeFormation to set
	 */
	public void setIdentifiant(int id) {
		this.identifiant = id;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	//----------------------------------------
	// CONSTRUCTEURS
	//----------------------------------------

	public Formation(){
		super();
	}
	
	/**
	 * @param codeFormation
	 * @param libelle
	 * @param description
	 * @param diplome
	 */
	public Formation(int id, String libelle, String description, String competences) {
		this();
		this.setIdentifiant(id);
		this.setLibelle(libelle);
		this.setDescription(description);
		this.setCompetences(competences);
	}
	
	//----------------------------------------
	// METHODES REDEFINIES
	//----------------------------------------
	
	/**
	 * toString
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Formation [id=");
		buffer.append(identifiant);
		buffer.append(", libelle=");
		buffer.append(libelle);
		buffer.append(", description=");
		buffer.append(description);
		buffer.append(", competences=");
		buffer.append(competences);
		buffer.append("]");
		return buffer.toString();
	}
	
	/**
	 * Clonage de l'instance actuelle
	 */
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * compareTo
	 * @param o
	 * @return
	 */
	public int compareTo(Formation o) {
		return this.getLibelle().compareTo(o.getLibelle());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competences == null) ? 0 : competences.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + identifiant;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formation other = (Formation) obj;
		if (competences == null) {
			if (other.competences != null)
				return false;
		} else if (!competences.equals(other.competences))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (identifiant != other.identifiant)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}
		
	
	
	
}
