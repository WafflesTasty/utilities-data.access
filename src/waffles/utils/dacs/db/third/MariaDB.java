package waffles.utils.dacs.db.third;

import java.sql.DriverManager;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.handlers.DBHandleable;

/**
 * The {@code MariaDB} class defines a {@code Database} for {@code MariaDB}.
 *
 * @author Waffles
 * @since 31 Oct 2025
 * @version 1.1
 *
 * 
 * @param <H>  a handler type
 * @see DBHandleable
 * @see Database
 */
public class MariaDB<H extends DBHandleable<?>> extends Database<H>
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

	@Override
	public String Prefix()
	{
		return "jdbc:mariadb://";
	}
}