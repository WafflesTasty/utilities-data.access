package waffles.utils.dacs.transit.primitive;

import java.nio.IntBuffer;

import waffles.utils.dacs.transit.MultiTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.arrays.set.IntegerSet;

/**
 * An {@code IntegerTransit} loads and unloads integer arrays into an {@code IntBuffer}.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MultiTransit
 * @see IntegerSet
 * @see Volatile
 */
@FunctionalInterface
public interface IntegerTransit extends MultiTransit<IntegerSet>, Volatile
{
	/**
	 * Unloads a value from the {@code IntegerTransit}.
	 * 
	 * @param iTgt  a buffer index
	 * @return  a loaded value
	 */
	public default int unload(int iTgt)
	{
		return Data().get(iTgt);
	}
	
	/**
	 * Loads a value into the {@code IntegerTransit}.
	 * 
	 * @param val   a value to load
	 * @param iTgt  a buffer index
	 */
	public default void load(int val, int iTgt)
	{
		Data().put(iTgt, val);
	}
	
	
	@Override
	public default IntegerSet unload(IntegerSet dat, int iSrc, int iTgt, int count)
	{
		Data().get(iTgt, dat.Array(), iSrc, count);
		return dat;
	}

	@Override
	public default void load(IntegerSet dat, int iSrc, int iTgt, int count)
	{
		Data().put(iTgt, dat.Array(), iSrc, count);
	}
	
	@Override
	public abstract IntBuffer Data();
}