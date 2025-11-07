package waffles.utils.dacs.db.third;

import java.sql.DriverManager;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.access.DBAccess;

/**
 * The {@code MariaDB} class defines a {@code Database} for {@code MariaDB}.
 *
 * @author Waffles
 * @since 31 Oct 2025
 * @version 1.1
 *
 * 
 * @param <A>  an access type
 * @see Database
 * @see DBAccess
 */
public class MariaDB<A extends DBAccess<?>> extends Database<A>
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
	 * Creates a new {@code MariaDB}.
	 */
	public MariaDB()
	{
		super("jdbc:mariadb://");
	}
}