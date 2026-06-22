package waffles.utils.dacs.transit.array;

import java.nio.DoubleBuffer;

import waffles.utils.dacs.transit.ArrayTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.indexed.array.like.DoubleArray;
import waffles.utils.sets.indexed.array.set.DoubleSet;

/**
 * A {@code DoubleTransit} loads and unloads {@code DoubleArray} data.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayTransit
 * @see DoubleArray
 */
public interface DoubleTransit extends ArrayTransit<DoubleArray>
{
	/**
	 * A {@code Base} implements an {@code DoubleTransit} as a {@code Volatile}.
	 * 
	 * @author Waffles
	 * @since Jun 22, 2026
	 * @version 1.1
	 *
	 *
	 * @see ArrayTransit
	 * @see DoubleTransit
	 * @see DoubleArray
	 * @see Volatile
	 */
	public abstract class Base extends ArrayTransit.Base<DoubleArray> implements DoubleTransit, Volatile
	{
		@Override
		public abstract DoubleBuffer Delegate();
		
		
		@Override
		public void pull(DoubleArray dat, int ofs, int cnt, int idx)
		{
			Delegate().get(idx, dat.Array(), ofs, cnt);
		}
		
		@Override
		public void push(DoubleArray dat, int ofs, int cnt, int idx)
		{
			Delegate().put(idx, dat.Array(), ofs, cnt);
			setIndex(idx + cnt);
		}
		
		
		@Override
		public void push(double dat, int idx)
		{
			Delegate().put(idx, dat);
			setIndex(idx + 1);
		}
		
		@Override
		public double pull(int idx)
		{
			return Delegate().get(idx);
		}
	}
	
	
	/**
	 * Pulls a double from the {@code DoubleTransit}.
	 * 
	 * @param idx  a data index
	 * @return     a data double
	 */
	public default double pull(int idx)
	{
		return pull(1, idx)[0];
	}
	
	/**
	 * Pulls a double set from the {@code DoubleTransit}.
	 * 
	 * @param cnt  a data count
	 * @param idx  a data index
	 * @return     a data array
	 */
	public default double[] pull(int cnt, int idx)
	{
		double[] dat = new double[cnt];
		pull(DoubleSet.of(dat), idx);
		return dat;
	}

	/**
	 * Pushes a double set into the {@code DoubleTransit}.
	 * 
	 * @param dat  a data array
	 * @param idx  a data index
	 */
	public default void push(double[] dat, int idx)
	{
		push(DoubleSet.of(dat), idx);
	}
	
	/**
	 * Pushes a double into the {@code DoubleTransit}.
	 * 
	 * @param dat  a data double
	 * @param idx  a data index
	 */
	public default void push(double dat, int idx)
	{
		push(new double[]{dat}, idx);
	}
	
	/**
	 * Pushes a double into the {@code DoubleTransit}.
	 * 
	 * @param dat  a data double
	 */
	public default void push(double dat)
	{
		push(dat, Index());
	}
}