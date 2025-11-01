package waffles.utils.dacs.db.sql.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import waffles.utils.dacs.db.sql.SQLAccess;
import waffles.utils.dacs.db.sql.SQLConnection;
import waffles.utils.dacs.utilities.errors.SQLError;

/**
 * A {@code H2Connection} provides a connection to an H2 database.
 *
 * @author Waffles
 * @since 03 Dec 2023
 * @version 1.1
 * 
 * 
 * @see <a href="https://www.h2database.com/html/main.html">H2 Database</a>
 * @see SQLConnection
 */
public class H2Connection extends SQLConnection
{
	static
	{
		try
		{
			Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new {@code H2Connection}.
	 * 
	 * @param acs  an sql access token
	 * 
	 * 
	 * @see SQLAccess
	 */
	public H2Connection(SQLAccess acs)
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
			
			return DriverManager.getConnection("jdbc:h2:" + host, user, pass);			
		}
		catch(SQLException e)
		{
			throw new SQLError(acs);
		}
	}
}