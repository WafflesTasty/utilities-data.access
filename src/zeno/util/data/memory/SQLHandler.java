package zeno.util.data.memory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The {@code SQLHandler} interface defines a connection to an {@code SQL} database.
 *
 * @author Waffles
 * @since 12 Sep 2021
 * @version 1.0
 */
public interface SQLHandler
{
	/**
	 * Creates a new {@code Statement}.
	 * 
	 * @return  a new statement
	 * 
	 * 
	 * @see Statement
	 */
	public default Statement Statement()
	{
		try
		{
			Connection conn = Connection();
			if(conn != null)
			{
				return conn.createStatement();
			}
		}
		catch(SQLException e){}
		return null;
	}
	
	
	/**
	 * Returns the connection of the {@code SQLHandler}.
	 * 
	 * @return  an sql connection
	 * 
	 * 
	 * @see Connection
	 */
	public abstract Connection Connection();
	
	/**
	 * Disconnects from the {@code SQLHandler}.
	 * 
	 * @return  {@code true} if successful
	 */
	public abstract boolean disconnect();
	
	/**
	 * Connects from the {@code SQLHandler}.
	 * 
	 * @return  {@code true} if successful
	 */
	public abstract boolean connect();
}