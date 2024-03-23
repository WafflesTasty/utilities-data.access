package waffles.utils.dacs.db;

/**
 * A {@code Connection} is used to connect to remote {@code Data}.
 *
 * @author Waffles
 * @since 19 Nov 2023
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @param <B>  a return type
 */
public interface Connection<A, B>
{
	/**
	 * Opens the {@code Connection}.
	 * 
	 * @param acs  access data
	 * @return  a connected object
	 */
	public abstract B connect(A acs);
	
	/**
	 * Closes the {@code Connection}.
	 * 
	 * @param acs  access data
	 * @return  {@code true} if successful
	 */
	public abstract boolean disconnect(A acs);

	/**
	 * Closes the {@code Connection}.
	 * 
	 * @return  {@code true} if successful
	 */
	public default boolean disconnect()
	{
		return disconnect(null);
	}
}