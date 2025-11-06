package waffles.utils.dacs.transit.primitive;

import java.nio.IntBuffer;

import waffles.utils.dacs.transit.MultiTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.indexed.array.set.IntegerSet;

/**
 * An {@code IntegerTransit} loads and unloads integer arrays into an {@code IntBuffer}.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see IntegerSet
 * @see MultiTransit
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
		return Delegate().get(iTgt);
	}
	
	/**
	 * Loads a value into the {@code IntegerTransit}.
	 * 
	 * @param val   a value to load
	 * @param iTgt  a buffer index
	 */
	public default void load(int val, int iTgt)
	{
		Delegate().put(iTgt, val);
	}
	
	
	@Override
	public default IntegerSet unload(IntegerSet dat, int iSrc, int iTgt, int count)
	{
		Delegate().get(iTgt, dat.Array(), iSrc, count);
		return dat;
	}

	@Override
	public default void load(IntegerSet dat, int iSrc, int iTgt, int count)
	{
		Delegate().put(iTgt, dat.Array(), iSrc, count);
	}
	
	@Override
	public abstract IntBuffer Delegate();
}