package waffles.utils.dacs.transit.array;

import java.nio.IntBuffer;

import waffles.utils.dacs.transit.ArrayTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.indexed.array.like.IntegerArray;
import waffles.utils.sets.indexed.array.set.IntegerSet;

/**
 * An {@code IntegerTransit} loads and unloads {@code IntegerArray} data.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayTransit
 * @see IntegerArray
 */
public interface IntegerTransit extends ArrayTransit<IntegerArray>
{
	/**
	 * A {@code Base} implements an {@code IntegerTransit} as a {@code Volatile}.
	 * 
	 * @author Waffles
	 * @since Jun 22, 2026
	 * @version 1.1
	 *
	 *
	 * @see ArrayTransit
	 * @see IntegerTransit
	 * @see IntegerSet
	 * @see Volatile
	 */
	public abstract class Base extends ArrayTransit.Base<IntegerArray> implements IntegerTransit, Volatile
	{
		@Override
		public abstract IntBuffer Delegate();
		
		
		@Override
		public void pull(IntegerArray dat, int ofs, int cnt, long idx)
		{
			Delegate().get((int) idx, dat.Array(), ofs, cnt);
		}
		
		@Override
		public void push(IntegerArray dat, int ofs, int cnt, long idx)
		{
			Delegate().put((int) idx, dat.Array(), ofs, cnt);
			setIndex(idx + cnt);
		}
		
		
		@Override
		public void push(int dat, long idx)
		{
			Delegate().put((int) idx, dat);
		}
		
		@Override
		public int pull(long idx)
		{
			return Delegate().get((int) idx);
		}
	}
	
	
	/**
	 * Pulls an integer from the {@code IntegerTransit}.
	 * 
	 * @param idx  a data index
	 * @return     a data integer
	 */
	public default int pull(long idx)
	{
		return pull(1, idx)[0];
	}
	
	/**
	 * Pulls an integer set from the {@code IntegerTransit}.
	 * 
	 * @param cnt  a data count
	 * @param idx  a data index
	 * @return     a data array
	 */
	public default int[] pull(int cnt, long idx)
	{
		int[] dat = new int[cnt];
		pull(IntegerSet.of(dat), idx);
		return dat;
	}

	/**
	 * Pushes an integer set into the {@code IntegerTransit}.
	 * 
	 * @param dat  a data array
	 * @param idx  a data index
	 */
	public default void push(int[] dat, long idx)
	{
		push(IntegerSet.of(dat), idx);
	}
	
	/**
	 * Pushes an integer into the {@code IntegerTransit}.
	 * 
	 * @param dat  a data integer
	 * @param idx  a data index
	 */
	public default void push(int dat, long idx)
	{
		push(new int[]{dat}, idx);
	}
	
	/**
	 * Pushes an integer into the {@code IntegerTransit}.
	 * 
	 * @param dat  a data integer
	 */
	public default void push(int dat)
	{
		push(dat, Index());
	}
}