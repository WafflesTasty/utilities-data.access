package waffles.utils.dacs.transit.primitive;

import java.nio.ByteBuffer;

import waffles.utils.dacs.transit.MultiTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.arrays.set.ByteSet;

/**
 * A {@code ByteTransit} loads and unloads byte arrays into a {@code ByteBuffer}.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ByteSet
 * @see MultiTransit
 * @see Volatile
 */
@FunctionalInterface
public interface ByteTransit extends MultiTransit<ByteSet>, Volatile
{
	/**
	 * Unloads a value from the {@code ByteTransit}.
	 * 
	 * @param iTgt  a buffer index
	 * @return  a loaded value
	 */
	public default byte unload(int iTgt)
	{
		return Delegate().get(iTgt);
	}
	
	/**
	 * Loads a value into the {@code ByteTransit}.
	 * 
	 * @param val   a value to load
	 * @param iTgt  a buffer index
	 */
	public default void load(byte val, int iTgt)
	{
		Delegate().put(iTgt, val);
	}
	
	
	@Override
	public default ByteSet unload(ByteSet dat, int iSrc, int iTgt, int count)
	{
		Delegate().get(iTgt, dat.Array(), iSrc, count);
		return dat;
	}

	@Override
	public default void load(ByteSet dat, int iSrc, int iTgt, int count)
	{
		Delegate().put(iTgt, dat.Array(), iSrc, count);
	}
	
	@Override
	public abstract ByteBuffer Delegate();
}