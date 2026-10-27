package waffles.utils.dacs.transit;

import waffles.utils.dacs.utilities.Transport;
import waffles.utils.sets.CountableSet;
import waffles.utils.tools.patterns.properties.counters.Indexed;
import waffles.utils.tools.patterns.properties.counters.data.Persistible;

/**
 * A {@code Transit} defines a generic loader of data.
 * Its {@link #Count()} is assumed to be one more than
 * the index of the highest loaded data object.
 * 
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 *
 *
 * @param <D>  a data type
 * @see CountableSet
 */
public interface Transit<D> extends CountableSet
{		
	/**
	 * A {@code Transit.Index} defines a {@code Transit} with an {@link #Index()}.
	 * The index is assumed to point to where the next load will take place.
	 *
	 * @author Waffles
	 * @since 02 Dec 2023
	 * @version 1.1
	 *
	 *
	 * @param <D>  a data type
	 * @see Indexed
	 * @see Transit
	 */
	public static interface Index<D> extends Indexed.Mutable<Long>, Transit<D>, Transport<D>
	{
		/**
		 * Pushes a {@code Persistible} into the {@code Transit}.
		 * 
		 * @param p    a persistible
		 * @param idx  a transit index
		 * 
		 * 
		 * @see Persistible
		 */
		public default void push(Persistible<? extends D> p, long idx)
		{
			push(p.Data(), idx);
		}

		
		@Override
		public default void push(D dat)
		{
			push(dat, Index());
		}
	}
	
	/**
	 * A {@code Transit.Dual} defines an unloader of data.
	 * Its {@link #Count()} is assumed to be one more than
	 * the index of the highest loaded data object.
	 * 
	 * @author Waffles
	 * @since 13 Nov 2023
	 * @version 1.1
	 *
	 *
	 * @param <D>  a data type
	 * @see CountableSet
	 */
	public interface Dual<D> extends CountableSet
	{		
		/**
		 * A {@code Transit.Dual.Index} is a dual transit with an {@link #Index()}.
		 * The index is assumed to point to where the next unload will take place.
		 *
		 * @author Waffles
		 * @since 02 Dec 2023
		 * @version 1.1
		 *
		 *
		 * @param <D>  a data type
		 * @see Transport
		 * @see Indexed
		 */
		public static interface Index<D> extends Indexed.Mutable<Long>, Transit.Dual<D>, Transport.Dual<D>
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
			public default void pull(Persistible<? extends D> p, long idx)
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
	
	
	/**
	 * Pushes a {@code Persistible} into the {@code Transit}.
	 * 
	 * @param p  a persistible
	 * 
	 * 
	 * @see Persistible
	 */
	public default void push(Persistible<? extends D> p)
	{
		push(p.Data());
	}
	
	/**
	 * Pushes data into the {@code Transit}.
	 * 
	 * @param dat  a data object to push
	 */
	public abstract void push(D dat);
}