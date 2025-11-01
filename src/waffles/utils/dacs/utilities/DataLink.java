package waffles.utils.dacs.utilities;

/**
 * A {@code DataLink} is used to connect to remote {@code Data}.
 *
 * @author Waffles
 * @since 19 Nov 2023
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @param <B>  a connection type
 */
public interface DataLink<A, B>
{
	/**
	 * Opens the {@code DataLink}.
	 * 
	 * @param acs  access data
	 * @return  a connected object
	 */
	public abstract B connect(A acs);
	
	/**
	 * Closes the {@code DataLink}.
	 * 
	 * @param acs  access data
	 * @return  {@code true} if successful
	 */
	public abstract boolean disconnect(A acs);

	/**
	 * Closes the {@code DataLink}.
	 * 
	 * @return  {@code true} if successful
	 */
	public default boolean disconnect()
	{
		return disconnect(null);
	}
	
	/**
	 * Opens the {@code DataLink}.
	 * 
	 * @return  a connected object
	 */
	public default B connect()
	{
		return connect(null);
	}
}