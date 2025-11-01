package waffles.utils.dacs.utilities.encrypt;

/**
 * An {@code Encryptable} defines a password {@code String}.
 *
 * @author Waffles
 * @since 01 Nov 2025
 * @version 1.1
 */
@FunctionalInterface
public interface Encryptable
{
	/**
	 * Returns the password of the {@code Encryptable}.
	 * 
	 * @return  a password
	 */
	public abstract String Password();
}