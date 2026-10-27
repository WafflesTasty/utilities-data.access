package waffles.utils.dacs.transit.array;

import java.nio.LongBuffer;

import waffles.utils.dacs.transit.ArrayTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.indexed.array.like.LongArray;
import waffles.utils.sets.indexed.array.set.LongSet;

/**
 * A {@code LongTransit} loads and unloads {@code LongArray} data.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayTransit
 * @see LongArray
 */
public interface LongTransit extends ArrayTransit<LongArray>
{
	/**
	 * A {@code Base} implements a {@code LongTransit} as a {@code Volatile}.
	 * 
	 * @author Waffles
	 * @since Jun 22, 2026
	 * @version 1.1
	 *
	 *
	 * @see ArrayTransit
	 * @see LongTransit
	 * @see LongArray
	 * @see Volatile
	 */
	public abstract class Base extends ArrayTransit.Base<LongArray> implements LongTransit, Volatile
	{
		@Override
		public abstract LongBuffer Delegate();
		
		
		@Override
		public void pull(LongArray dat, int ofs, int cnt, long idx)
		{
			Delegate().get((int) idx, dat.Array(), ofs, cnt);
		}
		
		@Override
		public void push(LongArray dat, int ofs, int cnt, long idx)
		{
			Delegate().put((int) idx, dat.Array(), ofs, cnt);
			setIndex(idx + cnt);
		}
		
		
		@Override
		public void push(long dat, long idx)
		{
			Delegate().put((int) idx, dat);
		}
		
		@Override
		public long pull(long idx)
		{
			return Delegate().get((int) idx);
		}
	}
	
	
	/**
	 * Pulls a long from the {@code LongTransit}.
	 * 
	 * @param idx  a data index
	 * @return     a data integer
	 */
	public default long pull(long idx)
	{
		return pull(1, (int) idx)[0];
	}
	
	/**
	 * Pulls a long set from the {@code LongTransit}.
	 * 
	 * @param cnt  a data count
	 * @param idx  a data index
	 * @return     a data array
	 */
	public default long[] pull(int cnt, long idx)
	{
		long[] dat = new long[cnt];
		pull(LongSet.of(dat), idx);
		return dat;
	}

	/**
	 * Pushes a long set into the {@code LongTransit}.
	 * 
	 * @param dat  a data array
	 * @param idx  a data index
	 */
	public default void push(long[] dat, long idx)
	{
		push(() -> dat, idx);
	}
	
	/**
	 * Pushes a long into the {@code LongTransit}.
	 * 
	 * @param dat  a data long
	 * @param idx  a data index
	 */
	public default void push(long dat, long idx)
	{
		push(new long[]{dat}, idx);
	}
	
	/**
	 * Pushes a long into the {@code LongTransit}.
	 * 
	 * @param dat  a data long
	 */
	public default void push(long dat)
	{
		push(dat, Index());
	}
}