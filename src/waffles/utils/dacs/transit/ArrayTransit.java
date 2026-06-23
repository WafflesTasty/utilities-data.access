package waffles.utils.dacs.transit;

import waffles.utils.sets.indexed.array.ArrayLike;

/**
 * An {@code ArrayTransit} loads and unloads {@code ArrayLike} data.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 *
 * 
 * @param <A>  an array type
 * @see DualTransit
 * @see ArrayLike
 */
public interface ArrayTransit<A extends ArrayLike<?,?>> extends DualTransit.Index<A>
{
	/**
	 * An {@code ArrayTransit.Base} implements a basic {@code ArrayTransit}.
	 * 
	 * @author Waffles
	 * @since Jun 22, 2026
	 * @version 1.1
	 *
	 *
	 * @param <A>  an array type
	 * @see ArrayTransit
	 * @see ArrayLike
	 */
	public abstract class Base<A extends ArrayLike<?,?>> implements ArrayTransit<A>
	{
		private int index, count;

		@Override
		public void setIndex(Integer idx)
		{
			index = idx;
			if(count < index)
			{
				count = index;
			}
		}

		@Override
		public Integer Index()
		{
			return index;
		}
		
		@Override
		public void clear()
		{
			count = 0;
			index = 0;
		}

		@Override
		public int Count()
		{
			return count;
		}
	}
	
	
	/**
	 * Pulls an {@code ArrayLike} from the {@code Transit}.
	 * 
	 * @param dat  a data array
	 * @param ofs  a data offset
	 * @param cnt  a data count
	 * @param idx  a data index
	 */
	public abstract void pull(A dat, int ofs, int cnt, int idx);
	
	/**
	 * Pushes an {@code ArrayLike} into the {@code Transit}.
	 * 
	 * @param dat  a data array
	 * @param ofs  a data offset
	 * @param cnt  a data count
	 * @param idx  a data index
	 */
	public abstract void push(A dat, int ofs, int cnt, int idx);
	
	
	@Override
	public default void pull(A dat, int idx)
	{
		pull(dat, 0, dat.Count(), idx);
	}

	@Override
	public default void push(A dat, int idx)
	{
		push(dat, 0, dat.Count(), idx);
	}
}