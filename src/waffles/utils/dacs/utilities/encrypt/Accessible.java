package waffles.utils.dacs.utilities.encrypt;

/**
 * An {@code Accessible} provides access to a remote host.
 *
 * @author Waffles
 * @since 01 Nov 2025
 * @version 1.1
 *
 * 
 * @see Profilable
 */
@FunctionalInterface
public interface Accessible extends Profilable
{
	/**
	 * Returns the host of the {@code Accessible}.
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