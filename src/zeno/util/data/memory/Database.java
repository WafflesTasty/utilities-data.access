package zeno.util.data.memory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import zeno.util.data.system.File;

/**
 * The {@code Database} class allows access to an SQL database.
 * <br> The database driver used is the {@code H2 database engine}.
 * 
 * @author Zeno
 * @since Nov 13, 2014
 * @version 1.0
 * 
 * 
 * @see <a href="http://www.h2database.com/html/main.html">H2 Database Engine</a>
 */
public class Database
{
	static
	{
		try
		{
			Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
		}
		catch(Exception e)
		{
			// Not Applicable
		}
	}
	
	
	private File file;
	private Connection conn;
	private String user, pass;
	
	/**
	 * Creates a new {@code Database}.
	 * 
	 * @param url  a file url
	 * @param usr  a database username
	 * @param pwd  a database password
	 * 
	 * 
	 * @see String
	 */
	public Database(String url, String usr, String pwd)
	{
		this(url);
		user = usr;
		pass = pwd;
	}
	
	/**
	 * Creates a new {@code Database}.
	 * 
	 * @param url  a file url
	 * 
	 * 
	 * @see String
	 */
	public Database(String url)
	{
		file = new File(url);
	}
	
	
	/**
	 * Creates a new {@code Statement}.
	 * 
	 * @return  a new statement
	 * 
	 * 
	 * @see Statement
	 */
	public Statement statement()
	{			
		try
		{
			return conn.createStatement();
		}
		catch(SQLException e)
		{
			return null;
		}
	}
	
	/**
	 * Disconnects from the {@code Database}.
	 * 
	 * @return  {@code true} if successful
	 */
	public boolean disconnect()
	{
		try
		{
			if(!conn.isClosed())
			{
				conn.close();
			}
			
			conn = null;
		}
		catch(SQLException e)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Connects to the {@code Database}.
	 * 
	 * @return  {@code true} if successful
	 */
	public boolean connect()
	{
		try
		{
			if(conn == null || !conn.isValid(0))
			{
				conn = DriverManager.getConnection("jdbc:h2:" + file.Path(), user, pass);
			}
		}
		catch(SQLException e)
		{
			return false;
		}
		
		return true;
	}
}