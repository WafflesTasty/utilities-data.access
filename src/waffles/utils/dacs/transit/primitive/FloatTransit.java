package waffles.utils.dacs.transit.primitive;

import java.nio.FloatBuffer;

import waffles.utils.dacs.transit.MultiTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.arrays.like.FloatArray;

/**
 * A {@code FloatTransit} loads and unloads float arrays into a {@code FloatBuffer}.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MultiTransit
 * @see FloatArray
 * @see Volatile
 */
@FunctionalInterface
public interface FloatTransit extends MultiTransit<FloatArray>, Volatile
{	
	/**
	 * Unloads a value from the {@code FloatTransit}.
	 * 
	 * @param iTgt  a buffer index
	 * @return  a loaded value
	 */
	public default float unload(int iTgt)
	{
		return Data().get(iTgt);
	}
	
	/**
	 * Loads a value into the {@code FloatTransit}.
	 * 
	 * @param val   a value to load
	 * @param iTgt  a buffer index
	 */
	public default void load(float val, int iTgt)
	{
		Data().put(iTgt, val);
	}
	
	
	@Override
	public default FloatArray unload(FloatArray dat, int iTgt)
	{
		Data().get(iTgt, dat.Array());
		return dat;
	}
	
	@Override
	public default FloatArray unload(FloatArray dat, int iSrc, int iTgt, int count)
	{
		Data().get(iTgt, dat.Array(), iSrc, count);
		return dat;
	}

	@Override
	public default void load(FloatArray dat, int iSrc, int iTgt, int count)
	{
		Data().put(iTgt, dat.Array(), iSrc, count);
	}

	@Override
	public default void load(FloatArray dat, int iTgt)
	{
		Data().put(iTgt, dat.Array());
	}
	
	@Override
	public abstract FloatBuffer Data();
}