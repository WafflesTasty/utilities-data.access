package waffles.utils.dacs.transit.array;

import java.nio.FloatBuffer;

import waffles.utils.dacs.transit.ArrayTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.indexed.array.like.FloatArray;
import waffles.utils.sets.indexed.array.set.FloatSet;

/**
 * A {@code FloatTransit} loads and unloads {@code FloatArray} data.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayTransit
 * @see FloatArray
 */
public interface FloatTransit extends ArrayTransit<FloatArray>
{
	/**
	 * A {@code Base} implements an {@code FloatTransit} as a {@code Volatile}.
	 * 
	 * @author Waffles
	 * @since Jun 22, 2026
	 * @version 1.1
	 *
	 *
	 * @see ArrayTransit
	 * @see FloatTransit
	 * @see FloatArray
	 * @see Volatile
	 */
	public abstract class Base extends ArrayTransit.Base<FloatArray> implements FloatTransit, Volatile
	{
		@Override
		public abstract FloatBuffer Delegate();
		
		
		@Override
		public void pull(FloatArray dat, int ofs, int cnt, long idx)
		{
			Delegate().get((int) idx, dat.Array(), ofs, cnt);
		}
		
		@Override
		public void push(FloatArray dat, int ofs, int cnt, long idx)
		{
			Delegate().put((int) idx, dat.Array(), ofs, cnt);
			setIndex(idx + cnt);
		}
		
		
		@Override
		public void push(float dat, long idx)
		{
			Delegate().put((int) idx, dat);
			setIndex(idx + 1);
		}
		
		@Override
		public float pull(long idx)
		{
			return Delegate().get((int) idx);
		}
	}
	
	
	/**
	 * Pulls a float from the {@code FloatTransit}.
	 * 
	 * @param idx  a data index
	 * @return     a data float
	 */
	public default float pull(long idx)
	{
		return pull(1, idx)[0];
	}
	
	/**
	 * Pulls a float set from the {@code FloatTransit}.
	 * 
	 * @param cnt  a data count
	 * @param idx  a data index
	 * @return     a data array
	 */
	public default float[] pull(int cnt, long idx)
	{
		float[] dat = new float[cnt];
		pull(FloatSet.of(dat), idx);
		return dat;
	}

	/**
	 * Pushes a float set into the {@code FloatTransit}.
	 * 
	 * @param dat  a data array
	 * @param idx  a data index
	 */
	public default void push(float[] dat, long idx)
	{
		push(FloatSet.of(dat), idx);
	}
	
	/**
	 * Pushes a float into the {@code FloatTransit}.
	 * 
	 * @param dat  a data float
	 * @param idx  a data index
	 */
	public default void push(float dat, long idx)
	{
		push(new float[]{dat}, idx);
	}
	
	/**
	 * Pushes a float into the {@code FloatTransit}.
	 * 
	 * @param dat  a data float
	 */
	public default void push(float dat)
	{
		push(dat, Index());
	}
}