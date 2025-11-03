package waffles.utils.dacs.utilities.encrypt;

/**
 * A {@code Login} provides encrypted access to a remote host.
 *
 * @author Waffles
 * @since 01 Nov 2025
 * @version 1.1
 *
 * 
 * @see Profile
 */
@FunctionalInterface
public interface Login extends Profile
{
	/**
	 * Returns the host of the {@code Login}.
	 * 
	 * @return  a host string
	 */
	public abstract String Host();
	
	@Override
	public default String User()
	{
		return "root";
	}
}