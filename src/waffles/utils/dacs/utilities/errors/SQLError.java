package waffles.utils.dacs.utilities.errors;

import waffles.utils.dacs.utilities.encrypt.Accessible;

/**
 * An {@code SQLError} is thrown whenever an error occurs during {@code Database} access.
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
	 * @see Accessible
	 */
	public SQLError(Accessible acs)
	{
		super("Could not access database " + acs.Host() + " with username " + acs.User() + ".");
	}
	
	/**
	 * Creates a new {@code SQLError}.
	 * 
	 * @param sql  an sql string
	 */
	public SQLError(String sql)
	{
		super("Invalid SQL statement: " + sql);
	}
}