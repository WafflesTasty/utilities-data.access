package waffles.utils.dacs.db;

import waffles.utils.dacs.utilities.login.Login;

/**
 * A {@code DBLogin} provides access to an SQL database.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 * 
 * @see Login
 */
public interface DBLogin extends Login
{
	/**
	 * Returns the database of the {@code DBLogin}.
	 * 
	 * @return  a database name
	 */
	public abstract String Database();
	
	@Override
	public default String Host()
	{
		return "127.0.0.1:3306";
	}
}