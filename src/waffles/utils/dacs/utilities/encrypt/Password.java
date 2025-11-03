package waffles.utils.dacs.utilities.encrypt;

/**
 * An {@code Password} defines a password {@code String}.
 *
 * @author Waffles
 * @since 01 Nov 2025
 * @version 1.1
 */
@FunctionalInterface
public interface Password
{
	/**
	 * Returns the {@code Password}.
	 * 
	 * @return  a password
	 */
	public abstract String Pass();
}