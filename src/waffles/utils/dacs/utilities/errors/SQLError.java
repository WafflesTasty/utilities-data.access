package waffles.utils.dacs.utilities.errors;

import waffles.utils.dacs.db.sql.SQLAccess;

/**
 * An {@code SQLError} is thrown whenever a database cannot be accessed.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see RuntimeException
 */
public class SQLError extends RuntimeException
{
	private static final long serialVersionUID = -6362191960875407040L;


	/**
	 * Creates a new {@code SQLError}.
	 * 
	 * @param acs  an sql access
	 * 
	 * 
	 * @see SQLAccess
	 */
	public SQLError(SQLAccess acs)
	{
		super("Could not access database " + acs.Host() + " with username " + acs.Username() + ".");
	}
}