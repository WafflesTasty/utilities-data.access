package zeno.util.data.memory.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import zeno.util.data.memory.SQLHandler;

/**
 * The {@code MariaDBHandler} class accesses a MariaDB SQL database.
 * 
 * @author Waffles
 * @since Nov 13, 2014
 * @version 1.0
 * 
 * 
 * @see <a href="https://mariadb.com/">MariaDB Engine</a>
 * @see SQLHandler
 */
public class MariaDBHandler implements SQLHandler
{
	static
	{
		try
		{
			Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();
		}
		catch(Exception e)
		{
			// Not Applicable
		}
	}
	
	
	private String host;
	private Connection conn;
	private String user, pass;
	
	/**
	 * Creates a new {@code H2Handler}.
	 * 
	 * @param url  a host url
	 * @param usr  a database username
	 * @param pwd  a database password
	 * 
	 * 
	 * @see String
	 */
	public MariaDBHandler(String url, String usr, String pwd)
	{
		host = url;
		user = usr;
		pass = pwd;
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
				conn = DriverManager.getConnection("jdbc:mariadb:" + host, user, pass);
			}
		}
		catch(SQLException e)
		{
			return false;
		}
		
		return true;
	}
}