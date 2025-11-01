package waffles.utils.dacs.utilities.encrypt;

/**
 * A {@code Profilable} defines a username/password combination.
 *
 * @author Waffles
 * @since 01 Nov 2025
 * @version 1.1
 *
 * 
 * @see Encryptable
 */
@FunctionalInterface
public interface Profilable extends Encryptable
{
	/**
	 * Returns the user of the {@code Profilable}.
	 * 
	 * @return  a username
	 */
	public abstract String User();
	
	@Override
	public default String Password()
	{
		return "";
	}
}
