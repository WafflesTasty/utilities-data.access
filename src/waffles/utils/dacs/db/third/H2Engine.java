package waffles.utils.dacs.db.third;

import java.sql.DriverManager;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.access.DBAccess;

/**
 * The {@code H2Engine} class defines a {@code Database} for {@code H2}.
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
public class H2Engine<A extends DBAccess<?>> extends Database<A>
{
	static
	{
		try
		{
			Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
			DriverManager.setLoginTimeout(3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * Creates a new {@code H2Engine}.
	 */
	public H2Engine()
	{
		super("jdbc:h2://");
	}
}