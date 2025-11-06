package waffles.utils.dacs.utilities.login;

/**
 * A {@code Profile} defines a username/password combination.
 *
 * @author Waffles
 * @since 01 Nov 2025
 * @version 1.1
 *
 * 
 * @see Password
 */
@FunctionalInterface
public interface Profile extends Password
{
	/**
	 * Returns the user of the {@code Profile}.
	 * 
	 * @return  a username
	 */
	public abstract String User();
	
	@Override
	public default String Pass()
	{
		return "";
	}
}
