package waffles.utils.dacs.db.sql.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import waffles.utils.dacs.db.sql.SQLAccess;
import waffles.utils.dacs.db.sql.SQLConnection;
import waffles.utils.dacs.utilities.errors.SQLError;

/**
 * A {@code MariaDBConnection} provides a connection to a MariaDB database.
 *
 * @author Waffles
 * @since 03 Dec 2023
 * @version 1.1
 * 
 * 
 * @see <a href="https://mariadb.org/">MariaDB</a>
 * @see SQLConnection
 */
public class MariaDBConnection extends SQLConnection
{
	static
	{
		try
		{
			Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();
			DriverManager.setLoginTimeout(3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new {@code MariaDBConnection}.
	 * 
	 * @param acs  an sql access token
	 * 
	 * 
	 * @see SQLAccess
	 */
	public MariaDBConnection(SQLAccess acs)
	{
		super(acs);
	}

	
	@Override
	public Connection connect(SQLAccess acs)
	{
		try
		{
			String host = acs.Host();
			String user = acs.Username();
			String pass = acs.Password();
			
			return DriverManager.getConnection("jdbc:mariadb://" + host, user, pass);			
		}
		catch(SQLException e)
		{
			throw new SQLError(acs);
		}
	}
}