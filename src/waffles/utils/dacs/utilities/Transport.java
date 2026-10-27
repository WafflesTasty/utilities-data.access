package waffles.utils.dacs.utilities;

/**
 * A {@code Transport} can transport objects in one direction.
 *
 * @author Waffles
 * @since Jun 22, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 */
@FunctionalInterface
public interface Transport<O>
{
	/**
	 * A {@code Transport.Dual} can transport objects in another direction.
	 *
	 * @author Waffles
	 * @since Jun 22, 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 */
	public static interface Dual<O>
	{
		/**
		 * Pulls data from the {@code Transport}.
		 * 
		 * @param dat  a data object
		 * @param idx  a data index
		 */
		public abstract void pull(O dat, long idx);
	}


	/**
	 * Pushes data into the {@code Transport}.
	 * 
	 * @param dat  a data object
	 * @param idx  a data index
	 */
	public abstract void push(O dat, long idx);
}
