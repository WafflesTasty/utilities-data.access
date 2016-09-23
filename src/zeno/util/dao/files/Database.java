package zeno.util.dao.files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import zeno.util.dao.File;

/**
 * The {@code Database} class defines a file handled as an SQL database.
 * <br> The database driver used for the files is the {@code H2 database engine}.
 * 
 * @since Nov 13, 2014
 * @author Zeno
 * 
 * @see <a href="http://www.h2database.com/html/main.html">H2 Database Engine</a>
 */
public class Database extends File
{
	static
	{
		try
		{
			Class.forName("org.h2.Driver").newInstance();
		}
		catch(Exception e)
		{
			// Not Applicable
		}
	}
	
	
	private Connection conn;
	private String user, pass;
	
	/**
	 * Creates a new {@code Database}.
	 * 
	 * @param url  a file url
	 * @param usr  a database username
	 * @param pwd  a database password
	 * @see String
	 */
	public Database(String url, String usr, String pwd)
	{
		super(url);
		user = usr;
		pass = pwd;
	}
	
	/**
	 * Creates a new {@code Database}.
	 * 
	 * @param url  a file url
	 * @see String
	 */
	public Database(String url)
	{
		super(url);
	}
	
	
	/**
	 * Creates a new {@code Statement}.
	 * 
	 * @return  a new statement
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
				conn = DriverManager.getConnection("jdbc:h2:" + Path(), user, pass);
			}
		}
		catch(SQLException e)
		{
			return false;
		}
		
		return true;
	}
}