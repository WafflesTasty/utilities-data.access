package waffles.utils.dacs.transit.primitive;

import java.nio.ShortBuffer;

import waffles.utils.dacs.transit.MultiTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.indexed.array.set.ShortSet;

/**
 * A {@code ShortTransit} loads and unloads short arrays into a {@code ShortBuffer}.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ShortSet
 * @see MultiTransit
 * @see Volatile
 */
@FunctionalInterface
public interface ShortTransit extends MultiTransit<ShortSet>, Volatile
{
	/**
	 * Unloads a value from the {@code ShortTransit}.
	 * 
	 * @param iTgt  a buffer index
	 * @return  a loaded value
	 */
	public default short unload(int iTgt)
	{
		return Delegate().get(iTgt);
	}
	
	/**
	 * Loads a value into the {@code ShortTransit}.
	 * 
	 * @param val   a value to load
	 * @param iTgt  a buffer index
	 */
	public default void load(short val, int iTgt)
	{
		Delegate().put(iTgt, val);
	}
	
	
	@Override
	public default ShortSet unload(ShortSet dat, int iSrc, int iTgt, int count)
	{
		Delegate().get(iTgt, dat.Array(), iSrc, count);
		return dat;
	}

	@Override
	public default void load(ShortSet dat, int iSrc, int iTgt, int count)
	{
		Delegate().put(iTgt, dat.Array(), iSrc, count);
	}
	
	@Override
	public abstract ShortBuffer Delegate();
}