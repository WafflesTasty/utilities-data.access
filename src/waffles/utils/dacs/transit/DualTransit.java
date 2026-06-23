package waffles.utils.dacs.transit;

import waffles.utils.dacs.utilities.Transport;
import waffles.utils.tools.patterns.properties.counters.data.Persistible;

/**
 * A {@code DualTransit} defines a loader and unloader of data.
 * Its {@link #Count()} is assumed to be one more than
 * the index of the highest loaded data object.
 * 
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 *
 *
 * @param <D>  a data type
 * @see Transit
 */
public interface DualTransit<D> extends Transit<D>
{		
	/**
	 * A {@code DualTransit.Index} defines a {@code DualTransit} with an {@link #Index()}.
	 * The index is assumed to point to where the next load will take place.
	 *
	 * @author Waffles
	 * @since 02 Dec 2023
	 * @version 1.1
	 *
	 *
	 * @param <D>  a data type
	 * @see DualTransit
	 * @see Transport
	 * @see Transit
	 */
	public static interface Index<D> extends Transit.Index<D>, DualTransit<D>, Transport.Dual<D>
	{
		/**
		 * Pulls a {@code Persistible} from the {@code Transit}.
		 * 
		 * @param p    a persistible
		 * @param idx  a transit index
		 * 
		 * 
		 * @see Persistible
		 */
		public default void pull(Persistible<? extends D> p, int idx)
		{
			pull(p.Data(), idx);
		}

		
		@Override
		public default void pull(D dat)
		{
			pull(dat, Index());
		}
	}
	
	
	/**
	 * Pulls a {@code Persistible} from the {@code Transit}.
	 * 
	 * @param p  a persistible
	 * 
	 * 
	 * @see Persistible
	 */
	public default void pull(Persistible<? extends D> p)
	{
		pull(p.Data());
	}

	/**
	 * Pulls data from the {@code Transit}.
	 * 
	 * @param dat  a data object to pull
	 */
	public abstract void pull(D dat);
}