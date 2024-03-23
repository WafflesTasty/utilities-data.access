package waffles.utils.dacs.db.sql;

/**
 * An {@code SQLAccess} defines the parameters to make an {@code SQLConnection}.
 *
 * @author Waffles
 * @since 03 Dec 2023
 * @version 1.1
 */
@FunctionalInterface
public interface SQLAccess
{
	/**
	 * Returns the host of the {@code SQLAccess}.
	 * 
	 * @return  a host url
	 */
	public abstract String Host();
	
	/**
	 * Returns the username of the {@code SQLAccess}.
	 * 
	 * @return  a user name
	 */
	public default String Username()
	{
		return "";
	}
	
	/**
	 * Returns the password of the {@code SQLAccess}.
	 * 
	 * @return  a password
	 */
	public default String Password()
	{
		return "";
	}
}