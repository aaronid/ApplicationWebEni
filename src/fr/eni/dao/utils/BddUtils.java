package fr.eni.dao.utils;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BddUtils 
{
	/*private static String url = null;
	
	static{
		
		try {
			// on charge les données du fichier de conf
			String className = Settings.getSetting("class");
			String databaseUrl = Settings.getSetting("url");
			String user = Settings.getSetting("user");
			String pwd = Settings.getSetting("pwd");
			
			// on charge le driver
			Class.forName(className);
			url = databaseUrl + ";user=" + user + ";password=" + pwd;
		} catch (ClassNotFoundException e) {
			System.err.println("Erreur lors du chargement du driver : " + e.getMessage());
		}
	}
		
	public static Connection getConnexion() throws SQLException
	{
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		return  DriverManager.getConnection(url);
	}*/
	
	/**
	 * Création d'une PoolConnection
	 * cf Servers/Tomcat/server.xml et web.xml
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
	public static Connection getConnexion() throws SQLException, NamingException{
		
		InitialContext jndi = new InitialContext();
		// recherche de la référence dans web.xml
		DataSource poolConnection = (DataSource)jndi.lookup("java:comp/env/jdbc/monPool");
		
		return poolConnection.getConnection();				
	}
}