package waffles.utils.dacs.transit.primitive;

import java.nio.DoubleBuffer;

import waffles.utils.dacs.transit.MultiTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.arrays.set.DoubleSet;

/**
 * A {@code DoubleTransit} loads and unloads double arrays into a {@code DoubleBuffer}.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MultiTransit
 * @see DoubleSet
 * @see Volatile
 */
@FunctionalInterface
public interface DoubleTransit extends MultiTransit<DoubleSet>, Volatile
{
	/**
	 * Unloads a value from the {@code DoubleTransit}.
	 * 
	 * @param iTgt  a buffer index
	 * @return  a loaded value
	 */
	public default double unload(int iTgt)
	{
		return Data().get(iTgt);
	}
	
	/**
	 * Loads a value into the {@code DoubleTransit}.
	 * 
	 * @param val   a value to load
	 * @param iTgt  a buffer index
	 */
	public default void load(double val, int iTgt)
	{
		Data().put(iTgt, val);
	}
	
	
	@Override
	public default DoubleSet unload(DoubleSet dat, int iSrc, int iTgt, int count)
	{
		Data().get(iTgt, dat.Array(), iSrc, count);
		return dat;
	}

	@Override
	public default void load(DoubleSet dat, int iSrc, int iTgt, int count)
	{
		Data().put(iTgt, dat.Array(), iSrc, count);
	}
	
	@Override
	public abstract DoubleBuffer Data();
}