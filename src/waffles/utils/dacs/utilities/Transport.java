package waffles.utils.dacs.utilities;

/**
 * A {@code Transport} can transport objects in two directions.
 *
 * @author Waffles
 * @since Jun 22, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 */
public interface Transport<O>
{
	/**
	 * Pulls data from the {@code Transport}.
	 * 
	 * @param dat  a data object
	 * @param idx  a data index
	 */
	public abstract void pull(O dat, int idx);
	
	/**
	 * Pushes data into the {@code Transport}.
	 * 
	 * @param dat  a data object
	 * @param idx  a data index
	 */
	public abstract void push(O dat, int idx);
}
