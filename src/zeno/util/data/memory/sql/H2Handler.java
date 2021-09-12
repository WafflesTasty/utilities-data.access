package zeno.util.data.memory.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import zeno.util.data.memory.SQLHandler;
import zeno.util.data.system.File;

/**
 * The {@code H2Handler} class accesses an H2 SQL database.
 * 
 * @author Waffles
 * @since Nov 13, 2014
 * @version 1.0
 * 
 * 
 * @see <a href="http://www.h2database.com/html/main.html">H2 SQLH2 Engine</a>
 * @see SQLHandler
 */
public class H2Handler implements SQLHandler
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
	 * Creates a new {@code H2Handler}.
	 * 
	 * @param url  a file url
	 * @param usr  a database username
	 * @param pwd  a database password
	 * 
	 * 
	 * @see String
	 */
	public H2Handler(String url, String usr, String pwd)
	{
		this(url);
		user = usr;
		pass = pwd;
	}
	
	/**
	 * Creates a new {@code H2Handler}.
	 * 
	 * @param url  a file url
	 * 
	 * 
	 * @see String
	 */
	public H2Handler(String url)
	{
		file = new File(url);
	}
	

	@Override
	public Connection Connection()
	{
		return conn;
	}
	
	@Override
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
	
	@Override
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