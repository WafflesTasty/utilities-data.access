package waffles.utils.dacs.transit.primitive;

import java.nio.LongBuffer;

import waffles.utils.dacs.transit.MultiTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.arrays.set.LongSet;

/**
 * A {@code LongTransit} loads and unloads long arrays into a {@code LongBuffer}.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see LongSet
 * @see MultiTransit
 * @see Volatile
 */
@FunctionalInterface
public interface LongTransit extends MultiTransit<LongSet>, Volatile
{	
	/**
	 * Unloads a value from the {@code LongTransit}.
	 * 
	 * @param iTgt  a buffer index
	 * @return  a loaded value
	 */
	public default long unload(int iTgt)
	{
		return Delegate().get(iTgt);
	}
	
	/**
	 * Loads a value into the {@code LongTransit}.
	 * 
	 * @param val   a value to load
	 * @param iTgt  a buffer index
	 */
	public default void load(long val, int iTgt)
	{
		Delegate().put(iTgt, val);
	}
	
	
	@Override
	public default LongSet unload(LongSet dat, int iSrc, int iTgt, int count)
	{
		Delegate().get(iTgt, dat.Array(), iSrc, count);
		return dat;
	}

	@Override
	public default void load(LongSet dat, int iSrc, int iTgt, int count)
	{
		Delegate().put(iTgt, dat.Array(), iSrc, count);
	}
	
	@Override
	public abstract LongBuffer Delegate();
}