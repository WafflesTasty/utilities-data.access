package waffles.utils.dacs.db.third;

import java.sql.DriverManager;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.entities.DBEntity;

/**
 * The {@code H2Engine} class defines a {@code Database} for {@code H2}.
 *
 * @author Waffles
 * @since 31 Oct 2025
 * @version 1.1
 *
 * 
 * @param <E>  an entity type
 * @see Database
 * @see DBEntity
 */
public class H2Engine<E extends DBEntity<?>> extends Database<E>
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

	@Override
	public String Prefix()
	{
		return "jdbc:h2://";
	}
}