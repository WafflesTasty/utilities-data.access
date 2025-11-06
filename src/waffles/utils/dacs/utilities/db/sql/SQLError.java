package waffles.utils.dacs.utilities.db.sql;

import waffles.utils.dacs.utilities.login.Login;
import waffles.utils.lang.tokens.values.primitive.LiteralToken;

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
	 * @param tkn  a table column
	 * @param tbl  a table name
	 * 
	 * 
	 * @see LiteralToken
	 */
	public SQLError(LiteralToken tkn, String tbl)
	{
		super("Could not access column " + tkn.Value() + " on table " + tbl + ".");
	}

	/**
	 * Creates a new {@code SQLError}.
	 * 
	 * @param log  an sql login
	 * 
	 * 
	 * @see Login
	 */
	public SQLError(Login log)
	{
		super("Could not access database " + log.Host() + " with user " + log.User() + ".");
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